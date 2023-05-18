package org.example;

import java.sql.*;
import java.util.Scanner;

public class Train {
    private Connection connection;

    public Train(Connection connection) {
        this.connection = connection;
    }
    private boolean isValidAdmin(int AdminID) throws SQLException{
            String sql = "SELECT adminID FROM admin Where adminID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,AdminID);
            ResultSet resultSet = statement.executeQuery();
            boolean res = resultSet.next();
            return res;
    }

    private boolean isValidTrain(int TrainID) throws SQLException{
            String sql = "SELECT trainID FROM train Where trainID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,TrainID);
            ResultSet resultSet = statement.executeQuery();
            boolean res = resultSet.next();
            return res;
    }

    public void AddTrain(Scanner scanner) {
        try {
            System.out.println("Enter train ID");
            int id = scanner.nextInt();
            while (isValidTrain(id)){
                System.out.println("Train Id id already used");
                id = scanner.nextInt();

            }
            scanner.nextLine();
            System.out.println("Enter train name");
            String name = scanner.nextLine();
            System.out.println("Enter train number");
            int num = scanner.nextInt();
            System.out.println("Enter admin ID");
            int adminID = scanner.nextInt();
            while (!isValidAdmin(adminID)){
                System.out.println("Invalid ID please try again");
                adminID = scanner.nextInt();
            }
            String sql = "INSERT INTO Train (trainID, trainName, trainNumber, adminID) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,num);
            statement.setInt(4,adminID);
            int rowsInserted = statement.executeUpdate();
            if(rowsInserted >= 1)
                System.out.println("Data inserted successfully.");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Edittrain(Scanner scanner) throws SQLException {
        System.out.println("Enter train ID");
        int id = scanner.nextInt();
        while (!isValidTrain(id)){
            System.out.println("Invalid ID please try again");
            id = scanner.nextInt();
        }

        System.out.print("please choose whether to: \n" +
                "1- Edit Train Number\n"+
                "2- Edit Train name\n");
        String sql;
        PreparedStatement statement;
        int subchoice = scanner.nextInt();
        switch (subchoice){
            case 1:
                System.out.println("Enter the new Number");
                int NewNumber = scanner.nextInt();
                sql = "UPDATE train set trainNumber = ? where trainID = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1,NewNumber);
                statement.setInt(2,id);
                statement.executeUpdate();
                break;

            case 2:
                System.out.println("Enter the new Name");
                scanner.nextLine();
                String name = scanner.nextLine();
                sql = "UPDATE train set trainName = ? where trainID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1,name);
                statement.setInt(2,id);
                statement.executeUpdate();
                break;
        }
    }
}
