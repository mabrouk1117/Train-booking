import java.sql.*;
import java.util.Scanner;

public class SignUp {
    private Connection connection;

    public SignUp() {
        String url = "jdbc:mysql://localhost:3306/my_schema";
        String username = "root";
        String password = "1234";
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the ID:");
            int id = scanner.nextInt();
            scanner.nextLine();

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
            System.out.println("Customer Or Admain :");
            String userType = scanner.nextLine();

            String sql = "INSERT INTO user (userID, firstName, lastName,phoneNumber, Email, password,userType) VALUES (?, ?, ?, ?, ?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, phoneNumber);
            statement.setString(5, email);
            statement.setString(6, passwordInput);
            statement.setString(7, userType);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }

            statement.close();
            scanner.close();

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
