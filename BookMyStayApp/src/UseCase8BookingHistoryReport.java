import java.util.*;

public class UseCase8BookingHistoryReport {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static Queue<String[]> queue = new LinkedList<>();
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private static List<String[]> bookingHistory = new ArrayList<>();
    private static int roomCounter = 1;

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 8.1");
        System.out.println("--------------------------------------");

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);

        addRequest("Abhishek", "Single Room");
        addRequest("Rahul", "Double Room");
        addRequest("Priya", "Suite Room");
        addRequest("Kiran", "Single Room");

        processBookings();

        showBookingHistory();

        generateReport();

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

                bookingHistory.add(new String[]{guest, roomType, roomId});

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

    private static void showBookingHistory() {

        System.out.println("\nBooking History:");

        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (String[] record : bookingHistory) {
            System.out.println("Guest: " + record[0] + " | Room: " + record[1] + " | ID: " + record[2]);
        }
    }

    private static void generateReport() {

        System.out.println("\nBooking Summary Report:");

        Map<String, Integer> report = new HashMap<>();

        for (String[] record : bookingHistory) {
            String roomType = record[1];
            report.put(roomType, report.getOrDefault(roomType, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : report.entrySet()) {
            System.out.println(entry.getKey() + " → Booked: " + entry.getValue());
        }
    }
}