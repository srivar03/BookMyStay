import java.util.*;

public class UseCase11ConcurrentBookingSimulation {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static Queue<String[]> queue = new LinkedList<>();
    private static Set<String> allocatedRooms = new HashSet<>();
    private static int roomCounter = 1;

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 11.1");
        System.out.println("--------------------------------------");

        inventory.put("Single Room", 2);

        addRequest("Abhishek", "Single Room");
        addRequest("Rahul", "Single Room");
        addRequest("Priya", "Single Room");
        addRequest("Kiran", "Single Room");

        Thread t1 = new Thread(() -> processBookings());
        Thread t2 = new Thread(() -> processBookings());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        displayInventory();

        System.out.println("\nThank you for using Book My Stay!");
    }

    private static synchronized void addRequest(String guest, String roomType) {
        queue.offer(new String[]{guest, roomType});
    }

    private static void processBookings() {

        while (true) {

            String[] request;

            synchronized (UseCase11ConcurrentBookingSimulation.class) {
                if (queue.isEmpty()) break;
                request = queue.poll();
            }

            String guest = request[0];
            String roomType = request[1];

            synchronized (UseCase11ConcurrentBookingSimulation.class) {

                int available = inventory.getOrDefault(roomType, 0);

                if (available > 0) {

                    String roomId = generateRoomId(roomType);

                    if (!allocatedRooms.contains(roomId)) {
                        allocatedRooms.add(roomId);
                        inventory.put(roomType, available - 1);

                        System.out.println(Thread.currentThread().getName() + " → Booking Confirmed:");
                        System.out.println("Guest: " + guest);
                        System.out.println("Room ID: " + roomId);
                        System.out.println("--------------------------------");
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " → Booking Failed for " + guest);
                }
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