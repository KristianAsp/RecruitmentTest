import java.util.*;

/**
 * Created by Kristian Andre Aspevik on 17/06/2017.
 */
class Program {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please input Coordinates (in the format x,y)");
        String inputRegex = "^-?(10|[0-9]),-?(10|[0-9])$"; //Use regex to match the input with the expected format.
        String input = captureInput(inputRegex);

        System.out.println("Please input a seed number (maximum 2^32-1 and minimum -2^32+1)");
        inputRegex = "^-?[0-9]*[1-9][0-9]*$";
        int seedNumber = Integer.parseInt(captureInput(inputRegex));

        EventProximityHandler eventProximityHandler = new EventProximityHandler(input, seedNumber);
        final long startTime = System.currentTimeMillis();
        int[][] events = eventProximityHandler.generateEvents();
        int[][] result = eventProximityHandler.calculateClosestEvents(events);

        eventProximityHandler.printOutResults(result);

        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }

    public static String captureInput(String regex){
        String input = scan.next();
        String result;
        if(input.matches(regex)){
            result = input;
        }
        else{
            System.out.println("This input is not valid. Please try again");
            result = captureInput(regex);
        }
        return result;
    }






}





