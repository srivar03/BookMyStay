import java.util.*;

public class UseCase7AddOnServiceSelection {

    private static Map<String, List<String>> reservationServices = new HashMap<>();
    private static Map<String, Integer> servicePrices = new HashMap<>();

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 7.1");
        System.out.println("--------------------------------------");

        servicePrices.put("Breakfast", 200);
        servicePrices.put("Airport Pickup", 500);
        servicePrices.put("Extra Bed", 300);
        servicePrices.put("Spa Access", 800);

        String reservationId1 = "SINGLE-1";
        String reservationId2 = "DOUBLE-1";

        addService(reservationId1, "Breakfast");
        addService(reservationId1, "Spa Access");

        addService(reservationId2, "Airport Pickup");
        addService(reservationId2, "Extra Bed");

        displayServices(reservationId1);
        displayServices(reservationId2);

        System.out.println("\nThank you for using Book My Stay!");
    }

    private static void addService(String reservationId, String service) {

        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    private static void displayServices(String reservationId) {

        System.out.println("\nReservation ID: " + reservationId);

        List<String> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());

        if (services.isEmpty()) {
            System.out.println("No add-on services selected.");
            return;
        }

        int total = 0;

        for (String service : services) {
            int price = servicePrices.getOrDefault(service, 0);
            System.out.println(service + " - ₹" + price);
            total += price;
        }

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}