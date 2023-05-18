package org.example;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/bookingtrain";
        String username = "root";
        String password = "123456";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Menue m = new Menue(connection);
            m.displayMenu();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

