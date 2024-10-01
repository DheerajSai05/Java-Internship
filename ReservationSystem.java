import java.util.Scanner;

class ReservationSystem {
    public void reserveTicket() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Train Number: ");
        String trainNumber = sc.nextLine();

        System.out.print("Enter Date of Journey: ");
        String date = sc.nextLine();

        System.out.print("Enter Starting Point: ");
        String from = sc.nextLine();

        System.out.print("Enter Destination: ");
        String to = sc.nextLine();

        System.out.println("Reservation Successful!");
        System.out.println("Train Number: " + trainNumber);
        System.out.println("Date: " + date);
        System.out.println("From: " + from);
        System.out.println("To: " + to);
    }
}