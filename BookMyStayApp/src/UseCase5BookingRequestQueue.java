public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 5.1");
        System.out.println("--------------------------------------");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Abhishek", "Single Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Double Room"));
        bookingQueue.addRequest(new Reservation("Priya", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Kiran", "Single Room"));

        bookingQueue.displayQueue();

        System.out.println("\nAll requests are stored in arrival order.");
        System.out.println("No booking has been processed yet.");

        System.out.println("\nThank you for using Book My Stay!");
    }
}