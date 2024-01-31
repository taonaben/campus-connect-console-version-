//THIS CLASS HANDLES ADDING HOUSES TO THE DATABASE

import java.sql.*;
import java.util.Scanner;

public class apartments {

    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        System.out.print("How many listings do you want to add: ");
        int count = scanner.nextInt();
        scanner.nextLine();

        String apartmentId = "";
        String location = "";
        double price = 0;
        double distance = 0;
        int roommates = 0;
        for (int x = 0; x < count; x++) {

            System.out.println("Enter Apartment ID: ");
            apartmentId = scanner.nextLine();

            System.out.println("Enter location of apartment: ");
            location = scanner.nextLine();


            System.out.println("Enter apartment price: ");
            price = scanner.nextDouble();

            System.out.println("Enter the distance to the campus: ");
            distance = scanner.nextDouble();

            System.out.println("Enter number of roommates: ");
            roommates = scanner.nextInt();
            scanner.nextLine();


            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/roomingapp", "root", "!Projectbaby09");


                String sql = "INSERT INTO apartments_table (apartmentId, location, price, distanceToCampus, numberOfRoomates) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1, apartmentId); // Example apartmentId
                preparedStatement.setString(2, location); // Example location
                preparedStatement.setDouble(3, price); // Example price
                preparedStatement.setDouble(4, distance); // Example distanceToCampus
                preparedStatement.setInt(5, roommates); // Example numberOfRoommates


                System.out.println("Listing added successfully");



            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

    }
        scanner.close();
}
}