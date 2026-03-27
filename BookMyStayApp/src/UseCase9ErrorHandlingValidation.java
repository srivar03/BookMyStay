import java.util.*;

public class UseCase9ErrorHandlingValidation {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static Queue<String[]> queue = new LinkedList<>();
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private static int roomCounter = 1;

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 9.1");
        System.out.println("--------------------------------------");

        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 0);

        addRequest("Abhishek", "Single Room");
        addRequest("Rahul", "Double Room");
        addRequest("Priya", "Suite Room");
        addRequest("Kiran", "Invalid Room");

        processBookings();

        System.out.println("\nFinal Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

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

            try {

                validateRoomType(roomType);
                validateAvailability(roomType);

                int available = inventory.get(roomType);

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

            } catch (InvalidBookingException e) {
                System.out.println("Booking Failed for " + guest + ": " + e.getMessage());
            }
        }
    }

    private static void validateRoomType(String roomType) throws InvalidBookingException {
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type");
        }
    }

    private static void validateAvailability(String roomType) throws InvalidBookingException {
        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available");
        }
    }

    private static String generateRoomId(String roomType) {
        return roomType.replace(" ", "").toUpperCase() + "-" + (roomCounter++);
    }

    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }
}