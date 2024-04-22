import Model.MSSFinder;
import Model.Subscore;

public class DPRunner implements MSSFinder {
    public static void main(String[] args) {
        //int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
        int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2, -8, 7, 3, -7, -4, -1, -2, -4, -3, -2, 4, 10, -5, 9, -1, -1, 8, -2, 8, -4, 2, -10, -7, -3, 4, 7, -7, -2, 5, 4, -8, -2, 10, 10, 10, 10, 10, 10, 10, -5};

        long startTime = System.nanoTime();
        OptimalRunner runner = new OptimalRunner();
        Subscore result = runner.findMSS(testcase, testcase.length);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        System.out.println("Eingabe: " + arrayStr(testcase));
        System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    public Subscore findMSS(int[] arr, int n) {
        int[][] S = new int[n][n];
        for (int x = 0; x < n; x++){
            S[x][x] = arr[x];
        }
        int max = 0, l = 1, r = 0;
        for (int i = 0; i < n; i++){
            for (int j = i+1; j < n; j++){
                S[i][j] = S[i][j-1] + arr[j];
                if (S[i][j] >= max){
                    max = S[i][j]; l = i; r = j;
                }
            }
        }
        return new Subscore(l, r, max);
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
    }}
