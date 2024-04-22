import java.util.Random;

public class MSSUtils {
    final int[] TEST_CASE = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
    public static String arrayStr(int[] arr) {
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
            arr[i] = rand.nextInt();

        }
        return arr;
    }
}
