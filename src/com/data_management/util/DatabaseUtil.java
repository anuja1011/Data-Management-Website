package com.data_management.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {
    public static Connection getConnection() {
        //String url = "jdbc:mysql://localhost:8889";
        String uId = "as88969";
        String password = "Anujaankita1";
        Connection con = null;
        String url="jdbc:oracle:thin:@MSB-MSITM.austin.utexas.edu:1521:ORCL";
//		String uId="dbuser";
//		String password="dbpassword";
//		Connection con=null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, uId, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResult(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
