import java.sql.*;
import java.util.Scanner;
public class customer {
    private Connection connection;
    customer(Connection connection){
        this.connection = connection;
    }

    public void viewTrips(int customerID){
        String sql = "SELECT TripID, originStation, DestinationStation, DepartureDate, DepratureTime , arrivialTime, availableSeats, trainName";
        sql += " FROM Trip join Train  on Trip.TrainID = Train.trainID where availableSeats != 0  ";
        try {


            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            System.out.println("Trip ID   Origin Station    Destination Station     Departure Date   Deprature Time     Arrival Time   available Seats    Train name" );
            while (resultSet.next()) {
                int tripId = resultSet.getInt("TripID");
                String originStation = resultSet.getString("originStation");
                String destinationStation = resultSet.getString("DestinationStation");
                String departureDate = resultSet.getString("DepartureDate");
                String arrivalTime = resultSet.getString("arrivialTime");
                int availableSeats = resultSet.getInt("availableSeats");
                String trainName = resultSet.getString("trainName");
                String departureTime = resultSet.getString("DepratureTime");

                // Process the retrieved data as needed
                System.out.println(tripId + "           " + originStation + "              " + destinationStation + "             " + departureDate + "          " + departureTime + "            " + arrivalTime + "           " + availableSeats + "           " + trainName);
            }
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Booking(int customerID , Scanner scanner ){
        System.out.println("enter the trip id to book");
        int tripID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("hello world");
        try {

            String sql = "SELECT availableSeats FROM Trip WHERE tripID = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, tripID);
            ResultSet resultSet = statement.executeQuery();

            if ( !(resultSet.next()) ) {
                System.out.println("the trip you're trying to book is not available ");
                statement.close();
                resultSet.close();
                return;
            }
            int availableSeats = resultSet.getInt("availableSeats");
            if (availableSeats == 0){
                System.out.println("the trip you're trying to book is not available ");
                statement.close();
                resultSet.close();
                return;
            }

            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int bookingID  , seatNumber;
        try {
            String sql = "SELECT COUNT(BookingID) as nxtID FROM Booking";
            PreparedStatement statement = connection.prepareStatement(sql) ;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bookingID = resultSet.getInt("nxtID");
            }
            else
                bookingID = 0;
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        try {
            String sql = "SELECT COUNT(BookingID) as nxtID FROM Booking where tripId = ?";
            PreparedStatement statement = connection.prepareStatement(sql) ;
            statement.setInt(1, tripID) ;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                seatNumber = resultSet.getInt("nxtID") + 1;
            }
            else {
                seatNumber = 1 ;
            }
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return ;
        }
        try {
            String sql = "insert into Booking(BookingID , CustomerID , tripID) values(? , ? , ?)" ;
            PreparedStatement statement = connection.prepareStatement(sql) ;
            statement.setInt(1, bookingID) ;
            statement.setInt(2, customerID) ;
            statement.setInt(3, tripID) ;
            int resultSet = statement.executeUpdate();
            System.out.println("you have booked successfully");
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            String sql = "insert into BookingDetails(BookingID , seatNumber) values(? , ? )" ;
            PreparedStatement statement = connection.prepareStatement(sql) ;
            statement.setInt(1, bookingID) ;
            statement.setInt(2, seatNumber) ;
            int resultSet = statement.executeUpdate();
            System.out.println("your seat number is " + seatNumber );
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            String sql = "update trip set availableSeats = availableSeats - 1 where tripID = ?" ;
            PreparedStatement statement = connection.prepareStatement(sql) ;
            statement.setInt(1, tripID) ;

            int resultSet = statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void cancel(int customerId , Scanner scanner){
        System.out.println("enter your trip id ");
        int tripId = scanner.nextInt() ;
        scanner.nextLine() ;
        int numberOfBooks  ;
        try {
            String sql = "delete from Booking where CustomerID = ? and tripID = ?  ";
            PreparedStatement statement = connection.prepareStatement(sql) ;
            statement.setInt(1, customerId) ;
            statement.setInt(2, tripId) ;
            numberOfBooks =  statement.executeUpdate() ;
            if (numberOfBooks == 0 ){
                System.out.println("you don't have any records to cancel") ;
                statement.close() ;
                return;
            }
            statement.close() ;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        try {
            String sql = "update trip set availableSeats = (availableSeats + ?) where tripID = ?" ;
            PreparedStatement statement = connection.prepareStatement(sql) ;
            statement.setInt(1, numberOfBooks) ;
            statement.setInt(2, tripId ) ;

            int resultSet = statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




}
