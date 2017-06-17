import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by Kristian on 17/06/2017.
 */
public class EventProximityHandler {
    int maxRange = 10;
    int minRange = -10;
    int xCoordinate;
    int yCoordinate;
    Random seedDataGenerator;
    Location location;

    public EventProximityHandler(String input, int seed){
        seedDataGenerator = new Random(seed);
        location = new Location(input);
    }

    public int[][] generateEvents(){
        int numberOfEvents = seedDataGenerator.nextInt(20) + 1; //Create a random number of events between 1 and 30;
        int[][] events = new int[numberOfEvents][5];

        for(int i = 0; i < numberOfEvents; i++){
            xCoordinate = seedDataGenerator.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for x between minRange and maxRange
            yCoordinate = seedDataGenerator.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for y between minRange and maxRange
            int priceInDollars = seedDataGenerator.nextInt(40);
            events[i] = new int[]{i+1, xCoordinate, yCoordinate, priceInDollars, 0}; //Add the new event along with the details e.g. position and price.

            int distance = calculateManhattanDistance(events[i], location); //Set the correct distance between the location of the input and the current event.
            events[i][4] = distance;
        }

        return events;
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

        result[0] = events[0];
        result[1] = events[1];
        result[2] = events[2];
        result[3] = events[3];
        result[4] = events[4];

        return result;
    }

    private int[][] sortArray(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return(Integer.valueOf(o1[4]).compareTo(o2[4]));
            }
        });
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
}

class Location{
    private int x;
    private int y;

    public Location(String input){
        String[] coordinates = input.split(",");

        this.x = Integer.parseInt(coordinates[0]);
        this.y = Integer.parseInt(coordinates[1]);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
