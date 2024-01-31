//THIS CLASS HANDLES ADDING STUDENT INFORMATION TO THE DATABASE

import java.sql.*;
import java.util.Scanner;

public class Student {

    public static double distance;
    public static int roommates;
    public static  double budget;

    private static void databaseAdd(){
        Scanner scanner = new Scanner(System.in);

        //input student details
        System.out.print("Enter your Student ID: ");
        String studentId = scanner.nextLine();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your budget: ");
        budget = scanner.nextDouble();

        System.out.print("Enter your prefered distance(km): ");
        distance = scanner.nextDouble();

        System.out.print("Enter number of roommates you prefer: ");
        roommates = scanner.nextInt();


        Connection connection= null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/roomingapp", "root", "!Projectbaby09");

            String sql = "INSERT INTO student_table (studentId, name, desiredPriceRange, desiredDistanceToCampus, desiredNumberOfRoomates) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, studentId); // Example studId
            preparedStatement.setString(2, name); // Example name
            preparedStatement.setDouble(3, budget); // Example price range
            preparedStatement.setDouble(4, distance); // Example distanceToCampus
            preparedStatement.setInt(5, roommates); // Example numberOfRoommates

            System.out.println("Details recorded\n");
        }
        catch (Exception e) {
            System.out.println(e);}

        scanner.close();
    }


    public static void displayer(){
        //sorround w is state ent from authentication
        try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/roomingapp", "root", "!Projectbaby09")) {
            String selectQuery = "SELECT * FROM apartments_table WHERE price <= ? AND numberOfRoomates <= ?";

            PreparedStatement preparedStatement = connection1.prepareStatement(selectQuery); // Use connection1 instead of connection
            preparedStatement.setDouble(1, budget);
            preparedStatement.setInt(2, roommates);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Searching...\n\n");
            int i = 1;
            while (resultSet.next()) {

                System.out.print("["+ i +"]->");
                System.out.print(resultSet.getString("apartmentId")+ " ");
                System.out.print(resultSet.getString("location")+ " ");
                System.out.print("$"+ resultSet.getString("price")+ " ");
                System.out.print("Roommates:"+resultSet.getString("numberOfRoomates")+ " ");
                System.out.print("Distance: "+resultSet.getString("DistanceToCampus")+ "KM\n");
                i++;
            }
        } catch (SQLException x) {
            throw new RuntimeException(x);
        }
    }


    public void main(String[] args) {
        databaseAdd();
        displayer();
    }
}
