import java.util.*;

public class UseCase6RoomAllocationService {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static Queue<String[]> queue = new LinkedList<>();
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private static int roomCounter = 1;

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 6.1");
        System.out.println("--------------------------------------");

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);

        addRequest("Abhishek", "Single Room");
        addRequest("Rahul", "Double Room");
        addRequest("Priya", "Suite Room");
        addRequest("Kiran", "Single Room");
        addRequest("Amit", "Suite Room");

        processBookings();

        displayInventory();

        System.out.println("\nThank you for using Book My Stay!");
    }

    private static void addRequest(String guest, String roomType) {
        queue.offer(new String[]{guest, roomType});
    }

    private static void processBookings() {

        while (!queue.isEmpty()) {

            String[] request = queue.poll();
            String guest = request[0];
            String roomType = request[1];

            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {

                String roomId = generateRoomId(roomType);

                allocatedRooms
                        .computeIfAbsent(roomType, k -> new HashSet<>())
                        .add(roomId);

                inventory.put(roomType, available - 1);

                System.out.println("Booking Confirmed:");
                System.out.println("Guest: " + guest);
                System.out.println("Room Type: " + roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println("--------------------------------");

            } else {
                System.out.println("Booking Failed for " + guest + " (No availability)");
            }
        }
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