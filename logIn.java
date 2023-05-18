package org.example;
import java.sql.*;
import java.util.Scanner;
public class logIn {
    private Connection connection;
    public logIn(Connection connection) {
        this.connection = connection;
    }
    public void loginCustomer(Scanner scanner) {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        try {
            String sql = "SELECT * FROM Customer WHERE Email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid email or password. Please try again.");
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loginAdmin(Scanner scanner) {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        try {
            String sql = "SELECT * FROM Admin WHERE Email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid email or password. Please try again.");
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}