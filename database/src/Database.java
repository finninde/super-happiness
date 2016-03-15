import javax.xml.crypto.Data;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Database {
    // Remember to add JDBC DRIVER to classpath
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // Autoreconnect=true is very convenient while useSSL=false is often necessary
    static final String DB_URL = "jdbc:mysql://localhost:3306/db?autoReconnect=true&useSSL=false";

    //  Database credentials
    //  My firewall stops you from logging in
    static final String USER = "user";
    static final String PASS = "hunter2";
    static final String filePath = "src/createdb.sql";
    Connection conn = null;

    public Database(){
        try {
            this.conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        catch (SQLException sql){
            sql.printStackTrace();
        }
        try {
            ScriptRunner runner = new ScriptRunner(conn, false, false);
            runner.runScript(new BufferedReader(new FileReader(filePath)));
        } catch (IOException e) {
            System.out.println("Database already initialized");
            //e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getExercises(){
        ResultSet rs;
        Statement stmt = null;
        String exercises = "";
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM exercise");
            while (rs.next()) {
                String res = rs.getString("name");
                exercises = exercises + ", " + res;
            }

            return exercises;
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return "NAN";
    }
    public void compareResult(){
        ResultSet rs;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            rs = stmt.executeQuery("SELECT isCardio, length_KM, weight FROM exerciseresult");
            double highestWeight = 0;
            double longestLength = 0;
            double lastWeight = 0;
            double lastLength = 0;
            boolean lastResult = false;


            while (rs.next()) {
                boolean isCardio = rs.getBoolean("isCardio");
                double length_KM = rs.getDouble("length_KM");
                double weight = rs.getInt("weight");
                lastWeight = weight;
                lastLength = length_KM;
                lastResult = isCardio;

                if (isCardio) {
                    if (length_KM > longestLength) {
                        longestLength = length_KM;
                    }
                } else {
                    if (weight > highestWeight) {
                        highestWeight = weight;
                    }
                }
            }

            if (lastResult) {
                double differenceLength = longestLength - lastLength;
                System.out.println("Longest in KM: " + longestLength);
                System.out.println("Latest result: " + lastLength);
                System.out.println("Difference: " + differenceLength);

            } else {
                double differenceWeight = highestWeight - lastWeight;
                System.out.println("Highest weight for exercise: " + highestWeight);
                System.out.println("Latest result: " + lastWeight);
                System.out.println("Difference: " + differenceWeight);
            }

        }catch (SQLException sql){
            sql.printStackTrace();
        }


    }



    public boolean createSessionFromTemplate(int templateID, int SessionID, String date, int form, int performance,
                                             int	temperature, String weather, int airQuality, int ventilation, int peopleWatchingMe){
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Creation of statement failed.");
            e.printStackTrace();
        }
        try {
            String query = "SELECT durationInMinutes, isOutDoor, exerciseID FROM template WHERE templateID= ;" + templateID;
            ResultSet rs = stmt.executeQuery(query);
            rs.first();
            int durationInMinutes = rs.getInt(1);
            boolean isOutDoor = rs.getBoolean(2);
            int exerciseID = rs.getInt(3);

            this.createSession(SessionID, date, durationInMinutes, form, performance, false, isOutDoor, temperature, weather, airQuality, ventilation, peopleWatchingMe);
            //TODO Implementer konstruksjonen av template, har kolonnene templateID (int),durationInMinutes isOutDoor(bool), exerciseID(int)

        }catch(Exception e){
            e.printStackTrace();
        }
        return true;

    }

    public boolean createSession(int SessionID, String date, int durationInMinutes, int form, int performance,
                                 boolean isTemplate, boolean isOutdoor, int temperature, String weather, int airQuality, int ventilation,
                                 int peopleWatchingMe){
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Creation of statement failed.");
            e.printStackTrace();
        }
        try {
            System.out.println( "INSERT INTO session (sessionID, date, durationInMinutes, form, performance, isTemplate, " +
                    "isOutDoor, temperature, weatherType, airQuality, ventilation,peopleWatchingMe) " +
                    "VALUES(" + SessionID + ","  + "\"" + date + "\"" + "," +  durationInMinutes + "," +  form + "," + performance + "," + isTemplate + "," +
                    isOutdoor + "," + temperature + "," + "\"" + weather + "\"" + "," + airQuality + "," + ventilation + "," + peopleWatchingMe + ")");
            stmt.executeUpdate( "INSERT INTO session (sessionID, date, durationInMinutes, form, performance, isTemplate, " +
                    "isOutDoor, temperature, weatherType, airQuality, ventilation,peopleWatchingMe) " +
                    "VALUES(" + SessionID + ","  + "\"" + date + "\"" + "," +  durationInMinutes + "," +  form + "," + performance + "," + isTemplate + "," +
                    isOutdoor + "," + temperature + "," + "\"" + weather + "\"" + "," + airQuality + "," + ventilation + "," + peopleWatchingMe + ")");
        } catch (SQLException e) {
            System.out.println("Key collision Already exists");
            e.printStackTrace();
            return false;
        }
        try {
            if(isTemplate==true){
                stmt.executeUpdate( "INSERT INTO template (templateID, durationInMinutes, isOutDoor) " +
                        "VALUES(" + SessionID + "," + durationInMinutes + "," + isOutdoor + ")");

                System.out.println("Template saved!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public String getTemplates(){
        ResultSet rs;
        Statement stmt = null;
        String templates = "";
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery("SELECT * FROM template");
            while (rs.next()) {
                int id = rs.getInt("templateID");
                int durationInMinutes = rs.getInt("durationInMinutes");
                boolean isOutDoor = rs.getBoolean("isOutDoor");
                String res;
                res = "templateID: " + id + ", Duration: "+ durationInMinutes + ", outDoor: " + isOutDoor;
                templates = templates+ "\n" + res;
            }

            return templates;
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return "NAN";

    }
    public static void main(String[] args) {
        // MAIN IS NOW ONLY FOR DOCUMENTATION AND TUTORIAL PURPOSES
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            //TODO: Generalize so that this is only done once.
            //TODO: Script does not initialize database db nor check for it
            // Uncomment these lines for db initialization, keep in mind CREATE DATABASE db; must be run manually
            /*ScriptRunner runner = new ScriptRunner(conn, false, false);
            String file = filePath;
            runner.runScript(new BufferedReader(new FileReader(file)));*/
            // Resultset is from the package SQL
            ResultSet rs;
            // This is how we insert data remember to toString any data inserted in the SQL Query
            // The test table is meant for testing so that I wouldn't need to write that much code.
            stmt.executeUpdate("INSERT INTO test (ID, finnerkul) VALUES (1,2)");
            // This is how we retrieve data, resultsets start as root and needs to be iterated to access the datafields
            rs = stmt.executeQuery("SELECT * FROM test");
            // We iterate the resultset and write out all the values in the finnerkul table
            while (rs.next()) {
                int finnerkul = rs.getInt("finnerkul");
                System.out.println("finnerkul: " + finnerkul);
            }

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{

            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
/*
INSERT INTO exerciseresult(exerciseresultID, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES (1, true, 2, 2, 2, 2, 2);
INSERT INTO exercise (name, description, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES ("Gainz ecercise", "SUPER STRENGTH Maximizes gains", FALSE , 0, 30, 420, 12, 4);
INSERT INTO exercise (name, description, isCardio, length_KM, length_minutes, weight, reps, sets) VALUES ("Super Cardio", "SUPER CARDIO Maximizes air intake", TRUE , 420, 9000, 0, 1, 1);
*/