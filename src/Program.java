import java.util.*;

/**
 * Created by Kristian Andre Aspevik on 17/06/2017.
 */
class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input Coordinates (in the format x,y)");
        String input = scan.next();
        System.out.println("Please input a seed number (maximum 2^32-1)");
        int seedNumber = scan.nextInt();

        EventProximityHandler eventProximityHandler = new EventProximityHandler(input, seedNumber);
        final long startTime = System.currentTimeMillis();
        int[][] events = eventProximityHandler.generateEvents();
        int[][] result = eventProximityHandler.calculateClosestEvents(events);

        eventProximityHandler.printOutResults(result);

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }







}





