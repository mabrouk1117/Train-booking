import java.sql.*;
import java.util.Scanner;


public class SignUp {
    private Connection connection;

    public SignUp(Connection connection) {
        this.connection = connection;
    }
    private boolean isValidcustomerID(int ID) throws SQLException{
        if(ID<1000){
            System.out.println("please enter long ID");
            return true;
        }
        String sql = "SELECT CustomerID FROM customer Where CustomerID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,ID);
        ResultSet resultSet = statement.executeQuery();
        boolean res = resultSet.next();
        if(res==true){
            System.out.println("id exists already");
        }
        return res;
    }
    private boolean isValidadminID(int ID) throws SQLException{
        if(ID<1000){
            System.out.println("please enter long ID");
            return true;
        }
        String sql = "SELECT adminID FROM Admin Where adminID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,ID);
        ResultSet resultSet = statement.executeQuery();
        boolean res = resultSet.next();
        if(res==true){
            System.out.println("id exists already");
        }
        return res;
    }

    public void insertCustomer(Scanner scanner) {
        try {
            System.out.println("Enter the ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            while(isValidcustomerID(id)) {
                System.out.println("Enter the ID:");
                id = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("Enter the first name:");
            String firstName = scanner.nextLine();

            System.out.println("Enter the last name:");
            String lastName = scanner.nextLine();

            System.out.println("Enter the PhoneNumber :");
            String phoneNumber = scanner.nextLine();

            System.out.println("Enter the email:");
            String email = scanner.nextLine();

            System.out.println("Enter the password:");
            String passwordInput = scanner.nextLine();

            String sql = "INSERT INTO Customer (CustomerID, firstName, lastName, phoneNumber, Email, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phoneNumber);
            statement.setString(5, email);
            statement.setString(6, passwordInput);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertAdmin(Scanner scanner) {
        try {
            System.out.println("Enter the ID:");
            int id = scanner.nextInt();
            scanner.nextLine();
            while(isValidadminID(id)) {
                System.out.println("Enter the ID:");
                id = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("Enter the first name:");
            String firstName = scanner.nextLine();

            System.out.println("Enter the last name:");
            String lastName = scanner.nextLine();

            System.out.println("Enter the PhoneNumber :");
            String phoneNumber = scanner.nextLine();

            System.out.println("Enter the email:");
            String email = scanner.nextLine();

            System.out.println("Enter the password:");
            String passwordInput = scanner.nextLine();

            String sql = "INSERT INTO Admin (AdminID, firstName, lastName, phoneNumber, Email, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phoneNumber);
            statement.setString(5, email);
            statement.setString(6, passwordInput);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}