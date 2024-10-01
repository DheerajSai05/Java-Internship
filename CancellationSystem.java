import java.util.Scanner;

class CancellationSystem {
    public void cancelTicket() {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter PNR Number: ");
        String pnr = sc.nextLine();

        // Assume valid PNR check for simplicity
        System.out.println("Ticket with PNR " + pnr + " is cancelled successfully!");
    }
}