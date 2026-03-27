public class RoomInitialization
{

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 2.1");
        System.out.println("--------------------------------------");

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 5;
        int suiteRoomAvailable = 2;

        System.out.println("\nSingle Room Details:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable);

        System.out.println("\nDouble Room Details:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable);

        System.out.println("\nSuite Room Details:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable);

        System.out.println("\nThank you for using Book My Stay!");
    }
}