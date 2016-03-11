import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Database {
    // JDBC driver name and database URL
    static final String deiteibeise= "";

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // Autoreconnect=true is very convenient while useSSL=false is often necessary
    static final String DB_URL = "jdbc:mysql://localhost:3306/db?autoReconnect=true&useSSL=false";

    //  Database credentials
    //  My firewall stops you from logging in
    static final String USER = "user";
    static final String PASS = "hunter2";
    static final String filePath = "src/createdb.sql";

    private static String statementLoader(String filePath){
        try{File f = new File(filePath);
        FileInputStream fin = new FileInputStream(f);
        byte[] buffer = new byte[(int) f.length()];
        new DataInputStream(fin).readFully(buffer);
        fin.close();
        String s = new String(buffer, "ASCII");
        return s;
        }
        catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            ScriptRunner runner = new ScriptRunner(conn, false, false);
            String file = filePath;
            runner.runScript(new BufferedReader(new FileReader(file)));
            System.out.println("Database created successfully...");
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
        System.out.println("Goodbye!");
    }
}
