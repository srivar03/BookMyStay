public class InventorySetup {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 3.1");
        System.out.println("--------------------------------------");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        System.out.println("\nRoom Details:");
        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(single.getRoomType()));

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(doubleRoom.getRoomType()));

        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability(suite.getRoomType()));

        inventory.displayInventory();

        System.out.println("\nUpdating Single Room availability to 8...");
        inventory.updateAvailability("Single Room", 8);

        inventory.displayInventory();

        System.out.println("\nThank you for using Book My Stay!");
    }
}