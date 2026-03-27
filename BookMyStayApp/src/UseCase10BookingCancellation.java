import java.util.*;

public class UseCase10BookingCancellation {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static Map<String, String[]> bookingMap = new HashMap<>();
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private static Stack<String> rollbackStack = new Stack<>();
    private static int roomCounter = 1;

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 10.1");
        System.out.println("--------------------------------------");

        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);

        String id1 = book("Abhishek", "Single Room");
        String id2 = book("Rahul", "Double Room");

        cancel(id1);
        cancel("INVALID-ID");

        displayInventory();

        System.out.println("\nThank you for using Book My Stay!");
    }

    private static String book(String guest, String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available <= 0) {
            System.out.println("Booking Failed for " + guest);
            return null;
        }

        String roomId = generateRoomId(roomType);

        allocatedRooms
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);

        inventory.put(roomType, available - 1);

        bookingMap.put(roomId, new String[]{guest, roomType});

        System.out.println("Booked: " + guest + " | " + roomType + " | ID: " + roomId);

        return roomId;
    }

    private static void cancel(String roomId) {

        if (roomId == null || !bookingMap.containsKey(roomId)) {
            System.out.println("Cancellation Failed: Invalid reservation ID");
            return;
        }

        String[] data = bookingMap.remove(roomId);
        String roomType = data[1];

        rollbackStack.push(roomId);

        allocatedRooms.getOrDefault(roomType, new HashSet<>()).remove(roomId);

        inventory.put(roomType, inventory.get(roomType) + 1);

        System.out.println("Cancelled Booking ID: " + roomId);
    }

    private static String generateRoomId(String roomType) {
        return roomType.replace(" ", "").toUpperCase() + "-" + (roomCounter++);
    }

    private static void displayInventory() {
        System.out.println("\nFinal Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}