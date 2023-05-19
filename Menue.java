
import java.sql.*;
import java.util.Scanner;

public class Menue {
    private Scanner scanner;
    private Connection connection;
    private logIn login;
    private SignUp signUp;

    private Trip trip;
    private Train train;

    public Menue(Connection connection) {
        this.connection = connection;
        scanner = new Scanner(System.in);
        login = new logIn(connection);
        signUp = new SignUp(connection);
        train = new Train(connection);
        trip = new Trip(connection);
    }

    public void displayMenu() {
        try {
            while (true) {
                System.out.println("Welcome to the Booking Train System!");
                System.out.println("Please choose an option:");
                System.out.println("1. Sign Up");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Please choose user type:");
                        System.out.println("1. Sign up as a customer");
                        System.out.println("2. Sign up as an admin");
                        int userTypeChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (userTypeChoice == 1) {
                            signUp.insertCustomer(scanner);
                        } else if (userTypeChoice == 2) {
                            signUp.insertAdmin(scanner);
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                        break;
                    case 2:
                        System.out.println("Please choose login type:");
                        System.out.println("1. Login as a user");
                        System.out.println("2. Login as an admin");
                        int loginTypeChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (loginTypeChoice == 1) {
                            login.loginCustomer(scanner);
                            //User menu
                        } else if (loginTypeChoice == 2) {
                            //Admin Menu
                            login.loginAdmin(scanner);
                            System.out.println("1 if you want to Edit Train");
                            System.out.println("2 if you want to Add Train");
                            System.out.println("3 if You want to Edit trip");
                            System.out.println("4 if You want to Add trip");
                            System.out.println("4 if You want to delete a trip");
                            int c = scanner.nextInt();
                            switch (c){
                                case 1:
                                    train.Edittrain(scanner);
                                    break;
                                case 2:
                                    train.AddTrain(scanner);
                                    break;
                                case 3:
                                    trip.Edit_a_Trip(scanner);
                                    break;
                                case 4:
                                    trip.Add_a_Trip(scanner);
                                    break;
                                case 5:
                                    trip.delete_a_trip(scanner);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                        } else {
                            System.out.println("Invalid choice. Please try again.");
                        }
                        break;
                    case 3:
                        System.out.println("Thank you for using the Booking Train System. Goodbye!");
                        signUp.closeConnection();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
