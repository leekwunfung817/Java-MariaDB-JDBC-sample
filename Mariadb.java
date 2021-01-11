package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Mariadb {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DB_URL = "jdbc:mariadb://127.0.0.1/";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    public static ArrayList<TreeMap> query(String sql) {

        ArrayList<TreeMap> al = new ArrayList<TreeMap>();

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            //STEP 3: Open a connection
//            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Connected database successfully...");
            //STEP 4: Execute a query
//            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String lsql = sql.toLowerCase();
            if (lsql.contains("update ") || lsql.contains("insert ") || lsql.contains("delete ")) {
                System.out.println("Execute:" + sql);
                stmt.executeUpdate(sql);
            } else {
                stmt.execute(sql);
                System.out.println("Query:" + sql);
                ResultSet rs = stmt.getResultSet();
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    TreeMap tm = new TreeMap();
                    for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                        tm.put(rsmd.getColumnName(j), rs.getString(j));
                    }
                    al.add(tm);
                }
                System.out.println("Result:" + al);
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
//        System.out.println("Goodbye!");
        return al;
    }//end main

} //end JDBCExample
