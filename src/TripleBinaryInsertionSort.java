import java.util.Arrays;
import java.util.Random;

public class TripleBinaryInsertionSort {

    static final int MAX_SIZE = 512000;
    static final int NUMBER_OF_ROUNDS = 4;

    public static void main(String[] args) {
        final double[] origArray = new double[MAX_SIZE];
        Random generator = new Random();
        for (int i = 0; i < MAX_SIZE; i++) {
            origArray[i] = generator.nextDouble()*1000.;
        }
        int rightLimit = MAX_SIZE / (int) Math.pow(2., NUMBER_OF_ROUNDS);

        // Start a competition
        for (int round = 0; round < NUMBER_OF_ROUNDS; round++) {
            double[] acopy;
            long stime, ftime, diff;
            rightLimit = 2 * rightLimit;
            System.out.println();
            System.out.println("Length: " + rightLimit);

            // My method
            acopy = Arrays.copyOf(origArray, rightLimit);
            stime = System.nanoTime();
            DoubleSorting.binaryInsertionSort(acopy);
            ftime = System.nanoTime();
            diff = ftime - stime;
            System.out.printf("%34s%11d%n", "Insertion sort: time (ms): ", diff / 1000000);
            DoubleSorting.checkOrder(acopy);

            // My partner's method
            acopy = Arrays.copyOf(origArray, rightLimit);
            stime = System.nanoTime();
            binaryInsertionSort(acopy);
            ftime = System.nanoTime();
            diff = ftime - stime;
            System.out.printf("%34s%11d%n", "Binary insertion sort: time (ms): ", diff / 1000000);
            DoubleSorting.checkOrder(acopy);

            // Third method we created together
            acopy = Arrays.copyOf(origArray, rightLimit);
            stime = System.nanoTime();
            JavaBinaryInsertionSort(acopy);
            ftime = System.nanoTime();
            diff = ftime - stime;
            System.out.printf("%34s%11d%n", "Merge sort: time (ms): ", diff / 1000000);
            DoubleSorting.checkOrder(acopy);
        }
    }

    private static void binaryInsertionSort(double[] a) {
        // https://www.youtube.com/watch?v=-OVB5pOZJug
        // https://www.geeksforgeeks.org/binary-insertion-sort/

        int i, loc, j;
        double selected;
        int n = a.length;
        for (i = 1; i < n; i++) {
            j = i - 1;
            selected = a[i];

            loc = binarySearch(a, selected, 0, j);

            System.arraycopy(a, loc, a, loc + 1, j - loc + 1);

            a[loc] = selected;
        }
    }

    private static int binarySearch(double[] a, double item, int low, int high){

        while (low <= high){
            int mid = (low + (high - low) / 2);
            if(item == a[mid]){
                return mid + 1;
            } else if (item > a[mid]){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void JavaBinaryInsertionSort(double[] a) {
        int i, loc;
        double selected;
        int n = a.length;
        for (i = 1; i < n; i++) {
            selected = a[i];

            loc = Arrays.binarySearch(a, 0, i, selected);

            if (loc < 0) {
                loc = -(loc + 1);
            }

            System.arraycopy(a, loc, a, loc + 1, i - loc);

            a[loc] = selected;
        }
    }
}