package com.database.manager;

import java.sql.*;

public class DBM {

    private static String connUsername;
    private static String connPassword;
    private static String connURL;
    public static Connection conn = null;
    public static Statement stat = null;
    public static ResultSet reSet = null;

    public static void main(String[] args) {
        try {
            conn = getMySQLConnection();
            
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            reSet = stat.executeQuery("SELECT * FROM HFG.new_table");
            
            reSet.last();
            
            System.out.print("printing resaultset current row data : " + reSet.getRow() +" \n");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password, String URL) {
        this.connUsername = username;
        this.connPassword = password;
        this.connURL = URL;
        // TODO code application logic here
        try {
            conn = getMySQLConnection();
            System.out.println("Login was successful!");
        } catch (Exception e) {
            System.err.println("Login was not successful!");
            // TODO: make sure to print the stack trace
            e.printStackTrace();
        }
        return true;
    }

    public static Connection getMySQLConnection() throws SQLException {
//      String connUsername = "root";
//      String connPassword = "root#s.am";
//      String connURL = "jdbc:mysql://localhost:3306/management?useSSL=true";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conn = DriverManager.getConnection(connURL, connUsername, connPassword);
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HFG?useSSL=true", "management", "dev@man");
        conn.setAutoCommit(false);
//      System.out.println(conn);
        return conn;
    }
}
