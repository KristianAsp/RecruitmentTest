import java.util.*;

/**
 * Created by Kristian on 17/06/2017.
 */
public class EventProximityHandler {
    private int maxRange = 10;
    private int minRange = -10;
    private int xCoordinate;
    private int yCoordinate;
    private int minPrice = 10;
    private int maxPrice = 40;
    private int numberOfEventsBoundary = 100;
    private Random seedDataGenerator;
    private Location location;
    private ArrayList<String> eventLocationLookupTable = new ArrayList<>();

    public EventProximityHandler(String input, int seed){
        seedDataGenerator = new Random(seed);
        location = new Location(input);
    }

    public int[][] generateEvents(){
        int numberOfEventsToCreate = seedDataGenerator.nextInt(numberOfEventsBoundary) + 1; //Create a random number of events between 1 and numberOfEventsBoundary;
        int[][] events = new int[numberOfEventsToCreate][5];

        for(int i = 0; i < numberOfEventsToCreate; i++){
            xCoordinate = seedDataGenerator.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for x between minRange and maxRange
            yCoordinate = seedDataGenerator.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for y between minRange and maxRange
            boolean isValidLocation = checkIfEventAtLocationExists(xCoordinate, yCoordinate);
            if(!isValidLocation){
                System.out.println("An event at this location already exists. Trying again. x: " + xCoordinate + " - y: " + yCoordinate);
                i--;
                continue;
            }
            int priceInDollars = seedDataGenerator.nextInt(maxPrice + 1 - minPrice) + minPrice;
            events[i] = new int[]{i+1, xCoordinate, yCoordinate, priceInDollars, 0}; //Add the new event along with the details e.g. position and price.

            int distance = calculateManhattanDistance(events[i], location); //Set the correct distance between the location of the input and the current event.
            events[i][4] = distance;
            eventLocationLookupTable.add(xCoordinate + "," + yCoordinate);
        }

        return events;
    }

    private boolean checkIfEventAtLocationExists(int xCoordinate, int yCoordinate) {
        boolean result = true;

        if(eventLocationLookupTable.contains(xCoordinate + "," + yCoordinate)){
            result = false;
        }

        return result;
    }

    private int calculateManhattanDistance(int[] a, Location b){
        int distance = Math.abs(a[1] - b.getX()) + Math.abs(a[2] - b.getY());
        return distance;
    }

    public int[][] calculateClosestEvents(int[][] events){
        if(events.length < 6){
            return events;
        }

        events = sortArray(events);
        int[][] result = new int[5][5];

        System.arraycopy(events, 0, result, 0, 5); //Copy the 5 first elements of the now sorted array to the result array.

        return result;
    }

    private int[][] sortArray(int[][] events) {
        Arrays.sort(events, (o1, o2) -> (Integer.valueOf(o1[4]).compareTo(o2[4])));
        return events;
    }

    public void printOutResults(int[][] result) {
        System.out.println("Closest Events to {" + location.getX() + "," + location.getY() + "}");
        for(int[] subArray : result){
            String eventIDFormatted = String.format("%03d", subArray[0]); //Add some leading zeros to make it look pretty
            int price = subArray[3];
            int distance = subArray[4];
            System.out.println("Event " + eventIDFormatted + " - Price: $" + price + " - Distance: " + distance);
        }
    }

    class Location{
        private int x;
        private int y;

        private Location(String input){
            String[] coordinates = input.split(",");

            this.x = Integer.parseInt(coordinates[0]);
            this.y = Integer.parseInt(coordinates[1]);
        }

        int getX(){
            return x;
        }

        int getY(){
            return y;
        }
    }
}


