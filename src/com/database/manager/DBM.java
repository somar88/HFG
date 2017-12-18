package com.database.manager;

import java.sql.*;

public class DBM {

    private static String connUsername;
    private static String connPassword;
    private static String connURL;
    public static Connection conn = null;

    public static void main(String[] args) {
        try {
            conn = getMySQLConnection();
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
//            e.printStackTrace();
        }
        return true;
    }

    public static Connection getMySQLConnection() throws SQLException {
//      String connUsername = "root";
//      String connPassword = "root#s.am";
//      String connURL = "jdbc:mysql://localhost:3306/management?useSSL=true";
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection conn = DriverManager.getConnection(connURL, connUsername, connPassword);
        conn.setAutoCommit(false);
//      System.out.println(conn);
        return conn;
    }
}
