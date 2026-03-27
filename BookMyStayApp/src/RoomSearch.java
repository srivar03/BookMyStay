import java.util.ArrayList;
import java.util.List;

public class RoomSearch {

    public static void main(String[] args) {

        System.out.println("Welcome to Book My Stay - Version 4.1");
        System.out.println("--------------------------------------");

        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        RoomInventory inventory = new RoomInventory();

        RoomSearchService searchService = new RoomSearchService(inventory);

        searchService.searchAvailableRooms(rooms);

        System.out.println("\nThank you for using Book My Stay!");
    }
}