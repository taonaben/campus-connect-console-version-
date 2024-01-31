import java.sql.*;
import java.util.Scanner;

public class login {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/roomingapp";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "!Projectbaby09";

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            boolean loginSuccessful = LoginCheck(connection, studentId);

            if (loginSuccessful) {
                System.out.println("Login successful!\n");
                System.out.println("Searching...\n\n");

                String selectQuery = "SELECT * FROM student_table WHERE studentId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
                preparedStatement.setString(1, studentId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int priceFromFirstTable = resultSet.getInt("desiredPriceRange");
                    int roommatesFromFirstTable = resultSet.getInt("desiredNumberofRoomates");

                    System.out.println("Student ID: " + studentId);

                    System.out.println("\nOptions:");
                    System.out.println("[1]. Edit Details");
                    System.out.println("[2]. Continue");

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    if (option == 1) {
                        System.out.print("Enter name: ");
                        String newName = scanner.nextLine();

                        System.out.print("Enter new desired price range: ");
                        int newPriceRange = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline character

                        System.out.print("Enter new desired distance to campus: ");
                        double newDistanceToCampus = scanner.nextDouble();
                        scanner.nextLine(); // Consume the newline character

                        System.out.print("Enter new desired number of roommates: ");
                        int newNumRoommates = scanner.nextInt();
                        scanner.nextLine();

                        String updateQuery = "UPDATE student_table SET name = ?, desiredPriceRange = ?, desiredDistanceToCampus = ?, desiredNumberofRoomates = ? WHERE studentId = ?";
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setString(1, newName);
                        updateStatement.setInt(2, newPriceRange);
                        updateStatement.setDouble(3, newDistanceToCampus);
                        updateStatement.setInt(4, newNumRoommates);
                        updateStatement.setString(5, studentId);
                        int rowsAffected = updateStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Student details updated successfully.");
                        } else {
                            System.out.println("Failed to update student details.");
                        }
                    } else if (option == 2) {
                        // Step 2: Use the retrieved values to construct a new query
                        String selectQuery2 = "SELECT * FROM apartments_table WHERE price <= ? AND numberOfRoomates <= ?";

                        // Step 3: Execute the new query on the second table
                        PreparedStatement preparedStatement2 = connection.prepareStatement(selectQuery2);
                        preparedStatement2.setInt(1, priceFromFirstTable);
                        preparedStatement2.setInt(2, roommatesFromFirstTable);
                        ResultSet resultSet2 = preparedStatement2.executeQuery();

                        int i = 1;
                        while (resultSet2.next()) {
                            System.out.print("[" + i + "]->");
                            System.out.print(resultSet2.getString("apartmentId") + " ");
                            System.out.print(resultSet2.getString("location") + " ");
                            System.out.print("$" + resultSet2.getString("price") + " ");
                            System.out.print("Roommates:" + resultSet2.getString("numberOfRoomates") + " ");
                            System.out.print("Distance: " + resultSet2.getString("DistanceToCampus") + "KM\n");
                            i++;
                        }
                    } else {
                        System.out.println("Invalid option");
                    }
                } else {
                    System.out.println("Invalid student ID");
                }
            } else {
                System.out.println("Invalid student ID");
            }

            // Step 4: Close the database connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean LoginCheck(Connection connection, String studentId) throws SQLException {
        String query = "SELECT * FROM student_table WHERE studentId = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, studentId);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }
}