
import java.util.Scanner;

public class authentication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.print("press 1 to exit");
            int exitBtn= scanner.nextInt();
            if (exitBtn == 1) {
                exit = true;
            } else {
                System.out.print("Invalid input");
            }
            if (exit){
                break;
            }
        }


        scanner.close();
    }
}
