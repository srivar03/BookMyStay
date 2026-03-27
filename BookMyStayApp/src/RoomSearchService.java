import java.util.List;

class RoomSearchService {

    private RoomInventory inventory;

    // Constructor Injection
    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Read-only Search Method
    public void searchAvailableRooms(List<Room> rooms) {

        System.out.println("\nAvailable Rooms:");

        boolean found = false;

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available: " + available);
                System.out.println("--------------------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available at the moment.");
        }
    }
}