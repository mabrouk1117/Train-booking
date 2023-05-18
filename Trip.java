package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;

public class Trip
{
    public Trip(){}
    public void Add_a_Trip()
    {
        String url = "jdbc:mysql://localhost:3306/...";           //replace ... with the name of your DB
        String username = "...";            //replace ... with ur username on your MySQL
        String password = "...";            //replace ... with ur Password on your MySQL
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "INSERT INTO Trip (TripID, TrainID, originStation, DestinationStation, DepartureDate, arrivalDate, availableSeats) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);

            boolean check = true, check2 = true;
            while (check) {
                System.out.print("Please enter enter you trip's ID: ");
                int tripID = scanner.nextInt();
                scanner.nextLine();
                if (tripID > 0) {
                    statement.setInt(1, tripID);
                    check = false;
                } else {
                    System.out.print("Invalid ID; ID MUST be Positive,Please enter again your Trip ID: ");
                    continue;
                }
            }


            while (check2) {
                System.out.print("Please enter enter your Train's ID: ");
                int trainID = scanner.nextInt();
                scanner.nextLine();
                if (trainID > 0) {
                    if (!isTrainIDValid(trainID, connection)) {
                        System.out.print("Invalid ID; this Train ID does NOT belong to our system, please choose whether to: " +
                                "1- Save this Train ID in our system firstly\n" +
                                "2- Re-enter another ID\n");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            insertTrain(trainID, connection);
                            statement.setInt(2, trainID);
                            check2 = false;
                        } else if (choice == 2) {
                            continue;
                        } else {
                            System.out.print("please enter a valid choice\n");
                            continue;
                        }
                    } else {
                        statement.setInt(2, trainID);
                        check2 = false;
                    }
                } else {
                    System.out.print("Invalid ID; ID MUST be Positive,Please enter again your Train ID: ");
                    continue;
                }
            }

            System.out.print("Enter Origin Station: ");
            String origin = scanner.nextLine();
            statement.setString(3, origin);

            System.out.print("Enter Destination Station: ");
            String destination = scanner.nextLine();
            statement.setString(4, destination);


            while (true) {
                System.out.print("Enter Departure Date and Time (YYYY-MM-DD HH:mm:ss): ");
                String departureDateTime = scanner.nextLine();
                Timestamp departureTimestamp = Timestamp.valueOf(departureDateTime);
                statement.setTimestamp(5, departureTimestamp);

                System.out.print("Enter Arrival Date and Time (YYYY-MM-DD HH:mm:ss): ");
                String arrivalDateTime = scanner.nextLine();
                Timestamp arrivalTimestamp = Timestamp.valueOf(arrivalDateTime);

                LocalDate departureDate = departureTimestamp.toLocalDateTime().toLocalDate();
                LocalDate arrivalDate = arrivalTimestamp.toLocalDateTime().toLocalDate();

                if (arrivalDate.isBefore(departureDate)) {
                    System.out.println("Invalid arrival date. Arrival date MUST be on OR after the departure date. Please enter again.");
                    continue;
                }
                LocalTime departureTime = departureTimestamp.toLocalDateTime().toLocalTime();
                LocalTime arrivalTime = arrivalTimestamp.toLocalDateTime().toLocalTime();

                if (arrivalDate.isEqual(departureDate) && (arrivalTime.isBefore(departureTime) || arrivalTime.equals(departureTime))) {
                    System.out.println("Invalid arrival time. Arrival time MUST be after the departure time. Please enter again.");
                    continue;
                }

                statement.setTimestamp(6, arrivalTimestamp);
                break;
            }

            System.out.print("Enter Available Seats: ");
            int availableSeats = scanner.nextInt();
            statement.setInt(7, availableSeats);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Row inserted successfully!");
            } else {
                System.out.println("Failed to insert row.");
            }

            statement.close();
            connection.close();
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Edit_a_Trip()
    {
        String url = "jdbc:mysql://localhost:3306/...";             //replace ... with the name of your DB
        String username = "...";           //replace ... with ur username on your MySQL
        String password = "...";          //replace ... with ur Pass on your MySQL
        Scanner sc = new Scanner(System.in);
        try
        {
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT Trip (TripID, TrainID, originStation, DestinationStation, DepartureDate, arrivalDate, availableSeats) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the Trip ID you want to edit it");
            int DesiredTripID = scanner.nextInt();
            if (isTripIDValid(DesiredTripID,connection))
            {
                System.out.print("please choose whether to: " +
                        "1- Edit TrainID\n" +
                        "2- Edit Origin Station\n" +
                        "3- Edit Destination Station\n"+
                        "4- Edit DepartureDate or ArrivalDate\n"+
                        "5- Edit Number Of Available Seats\n");
                int subchoice=scanner.nextInt();
                scanner.nextLine();
                switch (subchoice)
                {
                    case 1:
                        boolean check2=true;
                        while (check2)
                        {
                            System.out.print("Please enter enter your Train's ID: ");
                            sql = "UPDATE Trip SET TrainID = ? WHERE TripID = ?";
                            int trainID = scanner.nextInt();
                            PreparedStatement statement1 = connection.prepareStatement(sql);
                            scanner.nextLine();
                            if (trainID>0)
                            {
                                if (!isTrainIDValid(trainID, connection))
                                {
                                    System.out.print("Invalid ID; this Train ID does NOT belong to our system, please choose whether to: " +
                                            "1- Save this Train ID in our system firstly\n" +
                                            "2- Re-enter another ID\n");
                                    int choice=scanner.nextInt();
                                    if (choice==1)
                                    {
                                        insertTrain(trainID, connection);
                                        statement.setInt(2, trainID);
                                        check2=false;
                                    }
                                    else if (choice==2)
                                    {
                                        continue;
                                    }
                                    else
                                    {
                                        System.out.print("please enter a valid choice\n");
                                        continue;
                                    }
                                }
                                else
                                {
                                    statement1.setInt(1, trainID);
                                    statement1.setInt(2, DesiredTripID);
                                    statement1.executeUpdate();
                                    statement1.close();
                                    check2=false;
                                }
                            }
                            else
                            {
                                System.out.print("Invalid ID; ID MUST be Positive,Please enter again your Trip ID: ");
                                continue;
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Enter new Origin Station: ");
                        String neworigin = scanner.nextLine();
                        sql = "UPDATE Trip SET originStation = ? WHERE TripID = ?";
                        PreparedStatement statement2 = connection.prepareStatement(sql);
                        statement2.setString(1, neworigin);
                        statement2.setInt(2, DesiredTripID);
                        statement2.executeUpdate();
                        statement2.close();
                        break;
                    case 3:
                        System.out.print("Enter new Destination Station: ");
                        String newDest = scanner.nextLine();
                        sql = "UPDATE Trip SET DestinationStation = ? WHERE TripID = ?";
                        PreparedStatement statement3 = connection.prepareStatement(sql);
                        statement3.setString(1, newDest);
                        statement3.setInt(2, DesiredTripID);
                        statement3.executeUpdate();
                        statement3.close();
                        break;
                    case 4:
                        while (true)
                        {
                            System.out.print("Enter Departure Date and Time (YYYY-MM-DD HH:mm:ss): ");
                            String departureDateTime = scanner.nextLine();
                            sql = "UPDATE Trip SET DepartureDate = ? WHERE TripID = ?";
                            PreparedStatement statement4 = connection.prepareStatement(sql);
                            Timestamp NewdepartureTimestamp = Timestamp.valueOf(departureDateTime);

                            System.out.print("Enter Arrival Date and Time (YYYY-MM-DD HH:mm:ss): ");
                            String arrivalDateTime = scanner.nextLine();
                            sql = "UPDATE Trip SET arrivalDate = ? WHERE TripID = ?";
                            PreparedStatement statement5 = connection.prepareStatement(sql);
                            Timestamp arrivalTimestamp = Timestamp.valueOf(arrivalDateTime);

                            LocalDate departureDate = NewdepartureTimestamp.toLocalDateTime().toLocalDate();
                            LocalDate arrivalDate = arrivalTimestamp.toLocalDateTime().toLocalDate();

                            if (arrivalDate.isBefore(departureDate))
                            {
                                System.out.println("Invalid arrival date. Arrival date MUST be on OR after the departure date. Please enter again.");
                                continue;
                            }
                            LocalTime departureTime = NewdepartureTimestamp.toLocalDateTime().toLocalTime();
                            LocalTime arrivalTime = arrivalTimestamp.toLocalDateTime().toLocalTime();

                            if (arrivalDate.isEqual(departureDate) && (arrivalTime.isBefore(departureTime) || arrivalTime.equals(departureTime))) {
                                System.out.println("Invalid arrival time. Arrival time MUST be after the departure time. Please enter again.");
                                continue;
                            }
                            statement4.setTimestamp(1, NewdepartureTimestamp);
                            statement4.setInt(2, DesiredTripID);
                            statement4.executeUpdate();
                            statement4.close();

                            statement5.setTimestamp(1, NewdepartureTimestamp);
                            statement5.setInt(2, DesiredTripID);
                            statement5.executeUpdate();
                            statement5.close();
                            break;
                        }
                        break;
                    case 5:
                        System.out.print("Enter New Available Seats: ");
                        int newavailableSeats = scanner.nextInt();
                        sql = "UPDATE Trip SET availableSeats = ? WHERE TripID = ?";
                        PreparedStatement statement4 = connection.prepareStatement(sql);
                        statement4.setInt(1, newavailableSeats);
                        statement4.setInt(2, DesiredTripID);
                        statement4.executeUpdate();
                        statement4.close();
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        break;
                }
            }
            else
            {
                System.out.println("Invalid TripID; This ID doesn't exist on our System");
            }
            statement.close();
            connection.close();
            scanner.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean isTripIDValid(int TripID, Connection connection) throws SQLException {
        String sql = "SELECT TripID FROM Trip WHERE TripID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, TripID);
        ResultSet resultSet = statement.executeQuery();
        boolean isValid = resultSet.next();
        resultSet.close();
        statement.close();
        return isValid;
    }

    private static boolean isTrainIDValid(int trainID, Connection connection) throws SQLException {
        String sql = "SELECT TrainID FROM Train WHERE TrainID = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, trainID);
        ResultSet resultSet = statement.executeQuery();
        boolean isValid = resultSet.next();
        resultSet.close();
        statement.close();
        return isValid;
    }

    private static void insertTrain(int trainID, Connection connection) throws SQLException {
        String sql = "INSERT INTO Train (TrainID) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, trainID);
        statement.executeUpdate();
        statement.close();
    }
}

