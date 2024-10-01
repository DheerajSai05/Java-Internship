
import java.util.Scanner;

public class OnlineReservationSystem {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        LoginSystem login = new LoginSystem();
        ReservationSystem reservation = new ReservationSystem();
        CancellationSystem cancellation = new CancellationSystem();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Online Reservation System");
        System.out.print("Enter Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (login.login(user, pass)) {
            System.out.println("Login Successful!");

            while (true) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Make a Reservation");
                System.out.println("2. Cancel a Reservation");
                System.out.println("3. Exit");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        reservation.reserveTicket();
                        break;
                    case 2:
                        cancellation.cancelTicket();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            }
        } else {
            System.out.println("Login Failed. Invalid credentials.");
        }
    }
}
