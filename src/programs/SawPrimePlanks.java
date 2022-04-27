package programs;
import java.util.*;

public class SawPrimePlanks{

    public static List<Integer> getPlankLengths(int longPlankLength){
        List<Integer> plankList = new ArrayList<Integer>();
        int biggestPlank = sawPlank(longPlankLength);
        int numberOfPlanks = longPlankLength / biggestPlank;
        while (numberOfPlanks != 0){
            plankList.add(biggestPlank);
            numberOfPlanks = numberOfPlanks - 1;
        }
        return plankList;
    }

    public static int sawPlank(int plankLength){
        int cut = 0;
        boolean isPrime = true;
        for (int i = 2; i <= plankLength / 2; ++i) {
            if (plankLength % i == 0) {
                //if i can cut plankLength, plankLength is not prime and should be cut by the first smallest prime
                cut = i;
                isPrime = false;
                break;
            }
        }
        if (isPrime) {
            //plankLength is prime, in which case it is the largest cut that can be made on that plank
            return plankLength;
        }
        else if (!isPrime){
            //If plankLength isn't prime, make the first smallest cuts and try again
            return sawPlank(plankLength / cut);
        }
        //The program should never make it down here, but java isn't too fond of having all the
        //return statements locked up in conditionals
        return plankLength;
    }

}