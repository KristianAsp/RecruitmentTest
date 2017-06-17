import java.util.*;

/**
 * Created by Kristian on 17/06/2017.
 */
public class Program {

    public static void main(String[] args) {
        int maxRange = 10;
        int minRange = -10;
        int xCoordinate;
        int yCoordinate;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please input Coordinates (in the format x,y)");
        String input = scan.next();
        System.out.println("Please input a seed number (maximum 2^32-1)");
        int seedNumber = scan.nextInt();

        Random rnd1 = new Random(seedNumber);

        int numberOfEvents = rnd1.nextInt(30) + 1; //Create a random number of events between 1 and 30;

        for(int i = 0; i < numberOfEvents; i++){
            xCoordinate = rnd1.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for x between minRange and maxRange
            yCoordinate = rnd1.nextInt(maxRange + 1 - minRange) + minRange; //Generate a random value for y between minRange and maxRange
            double priceInDollars = rnd1.nextDouble();
            Event e = new Event(i+1, xCoordinate, yCoordinate, priceInDollars);

        }
    }
}

class Event{
    private int x;
    private int y;
    private double priceInDollars;
    private int id;

    public Event(int id, int x, int y, double priceInDollars){
        this.id = id;
        this.x = x;
        this.y = y;
        this.priceInDollars = priceInDollars;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public double getPriceInDollars(){
        return priceInDollars;
    }
}
