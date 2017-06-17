import java.util.*;

/**
 * Created by Kristian Andre Aspevik on 17/06/2017.
 */
class Program {

    public static void main(String[] args) {

        int maxRange = 10;
        int minRange = -10;
        int xCoordinate;
        int yCoordinate;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please input Coordinates (in the format x,y)");
        String input = scan.next();
        Location location = new Location(input);
        System.out.println("Please input a seed number (maximum 2^32-1)");
        int seedNumber = scan.nextInt();
        final long startTime = System.currentTimeMillis();
        Random rnd1 = new Random(seedNumber);
        int numberOfEvents = rnd1.nextInt(20) + 1; //Create a random number of events between 1 and 30;
        int[][] events = new int[numberOfEvents][5];

        for(int i = 0; i < numberOfEvents; i++){
            xCoordinate = rnd1.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for x between minRange and maxRange
            yCoordinate = rnd1.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for y between minRange and maxRange
            int priceInDollars = rnd1.nextInt(40);
            events[i] = new int[]{i+1, xCoordinate, yCoordinate, priceInDollars, 0}; //Add the new event along with the details e.g. position and price.

            int distance = calculateManhattanDistance(events[i], location); //Set the correct distance between the location of the input and the current event.
            events[i][4] = distance;
        }

        int[][] result = calculateClosestEvents(events);
        printOutResults(result, location);

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

    private static void printOutResults(int[][] result, Location location) {
        System.out.println("Closest Events to {" + location.getX() + "," + location.getY() + "}");
        for(int[] subArray : result){
            String eventIDFormatted = String.format("%03d", subArray[0]); //Add some leading zeros to make it look pretty
            int price = subArray[3];
            int distance = subArray[4];
            System.out.println("Event " + eventIDFormatted + " - Price: $" + price + " - Distance: " + distance);
        }
    }

    private static int[][] calculateClosestEvents(int[][] events){
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

    private static int[][] sortArray(int[][] events) {
        Arrays.sort(events, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return(Integer.valueOf(o1[4]).compareTo(o2[4]));
            }
        });
        return events;
    }

    private static int calculateManhattanDistance(int[] a, Location b){
        int distance = Math.abs(a[1] - b.getX()) + Math.abs(a[2] - b.getY());

        //System.out.println("ID:" + a[0] + "  X: " + a[1] + "  Y: " + a[2] + "  Distance: " + distance);
        return distance;
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



