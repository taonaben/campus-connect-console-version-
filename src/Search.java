import java.sql.*;
import java.util.Scanner;


public class Search{

    public static double distance;
    public static int roommates;
    public static  double budget;

    public static void main(String[] args) {
        getDetails();
        displayer();
    }
    public static void getDetails(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your budget: ");
        budget = scanner.nextDouble();

        System.out.print("Enter number of roommates you prefer: ");
        roommates = scanner.nextInt();

        scanner.close();
    }
    public static void displayer(){


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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
}
}