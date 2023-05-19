import java.sql.*;
import java.util.Scanner;

public class Update {
    private Connection connection;
    int ID;
    public Update(Connection connection,int id) {
        this.connection = connection;
        ID=id;
    }
    public void edit_customer_info(Scanner scanner) throws SQLException {
        int choice = 0;
        while(choice!=6) {
            System.out.print("please choose whether to: \n" +
                    "1- Edit first name\n" +
                    "2- Edit last name\n" +
                    "3- Edit phone number\n" +
                    "4- Edit password\n" +
                    "5- Edit email\n"+
                    "6- Return to main menu\n");
            String sql;
            PreparedStatement statement;
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the new name");
                    String newname = scanner.nextLine();
                    sql = "UPDATE Customer set firstName = ? where CustomerID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, newname);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;

                case 2:
                    System.out.println("Enter the new last Name");
                    String name = scanner.nextLine();
                    sql = "UPDATE Customer set lastName = ? where CustomerID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
                case 3:
                    System.out.println("Enter the new phone number");
                    String phone = scanner.nextLine();
                    sql = "UPDATE Customer set phoneNumber = ? where CustomerID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, phone);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
                case 4:
                    System.out.println("Enter the new password");
                    String pass = scanner.nextLine();
                    sql = "UPDATE Customer set password = ? where CustomerID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, pass);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
                case 5:
                    System.out.println("Enter the new email");
                    String email = scanner.nextLine();
                    sql = "UPDATE Customer set Email = ? where CustomerID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, email);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
            }
        }
    }
    public void edit_admin_info(Scanner scanner) throws SQLException {
        int choice = 0;
        while(choice!=6) {
            System.out.print("please choose whether to: \n" +
                    "1- Edit first name\n" +
                    "2- Edit last name\n" +
                    "3- Edit phone number\n" +
                    "4- Edit password\n" +
                    "5- Edit email\n"+
                    "6- Return to main menu\n");
            String sql;
            PreparedStatement statement;
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the new name");
                    String newname = scanner.nextLine();
                    sql = "UPDATE Admin set firstName = ? where AdminID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, newname);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;

                case 2:
                    System.out.println("Enter the new last Name");
                    String name = scanner.nextLine();
                    sql = "UPDATE Admin set lastName = ? where AdminID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, name);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
                case 3:
                    System.out.println("Enter the new phone number");
                    String phone = scanner.nextLine();
                    sql = "UPDATE Admin set phoneNumber = ? where AdminID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, phone);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
                case 4:
                    System.out.println("Enter the new password");
                    String pass = scanner.nextLine();
                    sql = "UPDATE Admin set password = ? where AdminID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, pass);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
                case 5:
                    System.out.println("Enter the new email");
                    String email = scanner.nextLine();
                    sql = "UPDATE Admin set Email = ? where AdminID = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, email);
                    statement.setInt(2, ID);
                    statement.executeUpdate();
                    break;
            }
        }
    }
}
