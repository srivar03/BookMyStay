import java.io.*;
import java.util.*;

public class UseCase12DataPersistenceRecovery {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static List<String[]> bookingHistory = new ArrayList<>();
    private static int roomCounter = 1;
    private static final String FILE_NAME = "booking_data.ser";

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 12.1");
        System.out.println("--------------------------------------");

        loadState();

        if (inventory.isEmpty()) {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
        }

        book("Abhishek", "Single Room");
        book("Rahul", "Double Room");

        saveState();

        displayData();

        System.out.println("\nRestart the program to see recovery in action.");
        System.out.println("Thank you for using Book My Stay!");
    }

    private static void book(String guest, String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available <= 0) {
            System.out.println("Booking Failed for " + guest);
            return;
        }

        String roomId = generateRoomId(roomType);

        inventory.put(roomType, available - 1);

        bookingHistory.add(new String[]{guest, roomType, roomId});

        System.out.println("Booked: " + guest + " | " + roomType + " | ID: " + roomId);
    }

    private static String generateRoomId(String roomType) {
        return roomType.replace(" ", "").toUpperCase() + "-" + (roomCounter++);
    }

    private static void saveState() {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(inventory);
            out.writeObject(bookingHistory);
            out.writeInt(roomCounter);
            System.out.println("\nState saved successfully.");
        } catch (Exception e) {
            System.out.println("\nError saving state.");
        }
    }

    private static void loadState() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            inventory = (Map<String, Integer>) in.readObject();
            bookingHistory = (List<String[]>) in.readObject();
            roomCounter = in.readInt();
            System.out.println("State loaded successfully.");
        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    private static void displayData() {

        System.out.println("\nInventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        System.out.println("\nBooking History:");
        for (String[] record : bookingHistory) {
            System.out.println("Guest: " + record[0] + " | Room: " + record[1] + " | ID: " + record[2]);
        }
    }
}