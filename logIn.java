import java.sql.*;
import java.util.Scanner;
public class logIn {
    private Connection connection;
    public logIn(Connection connection) {
        this.connection = connection;
    }
    public int loginCustomer(Scanner scanner) {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        try {
            String sql = "SELECT CustomerID FROM Customer WHERE Email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful.");
                int id=resultSet.getInt("CustomerID");
                statement.close();
                resultSet.close();
                return id;
            } else {
                System.out.println("Invalid email or password. Please try again.");
                statement.close();
                resultSet.close();
                return -1;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public int loginAdmin(Scanner scanner) {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        try {
            String sql = "SELECT adminID FROM Admin WHERE Email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Login successful.");
                int id=resultSet.getInt("CustomerID");
                statement.close();
                resultSet.close();
                return id;
            } else {
                System.out.println("Invalid email or password. Please try again.");
                statement.close();
                resultSet.close();
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


}
