import java.util.Scanner;

public class userInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Student Student = new Student();
        apartments Apartments = new apartments();
        System.out.println("\t\t\tWELCOME TO CAMPUS CONNECT\t\n");

        System.out.println("Campus connect is a revolutionary app designed to simplify\n" +
                "the proscess of " +
                "" +
                "finding accommodation for students around campus. \nIts the " +
                "one-stop solution that connects students with a wide range\nof housing options " +
                "tailored specifically to their needs. With\ncampus connect, students can effortlessly" +
                " browse through detailed\nproperty listings and schedule appointments with just a few taps\n");

        boolean exit = false;

        while(!exit){
            System.out.print("Press [1] to continue: ");
            int exitBtn= scanner.nextInt();
            if (exitBtn == 1) {
                exit = true;
            } else {
                System.out.print("Invalid input\n");

            }
            if (exit){
                break;
            }

            System.out.print("Try again: \n");
        }

        System.out.print("Use 1 or 2 to select\n");
        System.out.print("[1] Add listing: \t[2] Look for an apartment: \t[3] Search for an apartment: ");
        int add_search = scanner.nextInt();

        switch (add_search){
            case 1 ->{Apartments.main(args);}
            case 2 ->{
                System.out.print("[1] Login: \t[2] Register: ");
                int login_reg = scanner.nextInt();

                switch (login_reg){
                    case 1 -> {login.main(args);}
                    case 2 -> {Student.main((args));
                    }
                    default -> {System.out.println("Invalid input");}
                }
            }
            case 3->{
                Search.main(args);
            }
            default -> {System.out.println("Invalid input");}
        }

        scanner.close();
    }
}
