import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MergeSortedArrays {


    public static void main(String[] args) {
        int[] arrival = new int[]{900, 910, 950, 1100, 1500, 1800};
        int[] departure = new int[]{910, 1120, 1130, 1200, 1900, 2000};

        int result = mergeArrays(arrival, departure);
        System.out.println(result);


    }



    private static int mergeArrays(int[] arrival, int[] departure) {

        int lengthOfResultArray = arrival.length + departure.length;

        int indexOfResultArray = 0;
        int topOfArr1 = 0;
        int topOfArr2 = 0;
        int currentPlatformCount = 0;
        int maximumPlatformCount = 0;
        while(indexOfResultArray!=lengthOfResultArray && topOfArr1<arrival.length && topOfArr2<departure.length){

            if(arrival[topOfArr1]<departure[topOfArr2]){
                currentPlatformCount++;
                topOfArr1++;
            }
            else {
               currentPlatformCount--;
                topOfArr2++;
            }
            indexOfResultArray++;
            maximumPlatformCount = Math.max(maximumPlatformCount, currentPlatformCount);
        }
        return maximumPlatformCount;
    }
}
