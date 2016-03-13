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

    public static void main(String[] args) {
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
            //stmt.executeUpdate("INSERT INTO test (ID, finnerkul) VALUES (1,2)");
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
