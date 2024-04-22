import Model.MSSFinder;

import java.util.Random;

public class MSSUtils {
    public final static int[] TEST_CASE = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
    public final static int CUT_OFF_SECOND = 100;
    public final static int[] SIZES_TO_BENCHMARK = {
            (int)Math.pow(10, 1),
            (int)Math.pow(10, 2),
            (int)Math.pow(10, 3),
            (int)Math.pow(10, 4),
            (int)Math.pow(10, 5),
            (int)Math.pow(10, 6),
            (int)Math.pow(10, 7),
            (int)Math.pow(10, 8),
            (int)Math.pow(10, 9)
    };    public static String arrayStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    public static int[] createRandomArray(int n) {
    int[] arr = new int[n];
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
        arr[i] = rand.nextInt(201) - 100; // Generates a random integer between -100 and 100
    }
    return arr;
}

    public static MSSFinder[] createMSSFinderInstances() {
        MSSFinder[] instances = new MSSFinder[5];
        instances[1] = new rekursivRunner();
        instances[0] = new naivRunner();
        instances[3] = new DivideAndConquer();
        instances[2] = new DPRunner();
        instances[4] = new OptimalRunner();
        return instances;
    }
}
