package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Operations2 {

    private static final String URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root@123";

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ParameterMetaData pmd = null;

        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Create a prepared statement with parameters
            String sql = "INSERT INTO student_table (`student_name`, `student_id`, `student_branch`, `student_address`, `student_phone`) VALUES (?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            // Get ParameterMetaData
            pmd = pstmt.getParameterMetaData();

            int paramCount = pmd.getParameterCount();
            System.out.println("Number of Parameters: " + paramCount);

            for (int i = 1; i <= paramCount; i++) {
                System.out.println("Parameter " + i + " Type: " + pmd.getParameterTypeName(i));
            }

            // Set values for the parameters
            pstmt.setString(1, "John");
            pstmt.setInt(2, 101);
            pstmt.setString(3, "CSE");
            pstmt.setString(4, "New York");
            pstmt.setLong(5, 9876543210L);

            // Execute the query
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted Rows: " + rowsAffected);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
