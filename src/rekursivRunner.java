import Model.MSSFinder;
import Model.Subscore;

public class rekursivRunner implements MSSFinder {

    public static void main(String[] args) {

        int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
        rekursivRunner runner = new rekursivRunner();
        long startTime = System.nanoTime();
        Subscore result = runner.findMSS(testcase, testcase.length);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        System.out.println("Eingabe: " + arrayStr(testcase));
        System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    public Subscore findMSS(int[] arr, int n) {
        int maxscore = 0;
        int l = 1;
        int r = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int s = calculateScore(i, j, arr);
                if (s >= maxscore) {
                    maxscore = s;
                    l = i;
                    r = j;
                }
            }
        }
        return new Subscore(l, r, maxscore);
    }

    public static int calculateScore(int i, int j, int[] arr) {
        if (i > j) return 0;
        else if (i == j) {
            return arr[i];
        } else {
            //int k = (i + j) / 2;
            int k = j-1;
            //return calculateScore(i, k, arr) + calculateScore(k+1, j, arr);
            return calculateScore(i, k, arr) + arr[j];
        }
    }

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
}