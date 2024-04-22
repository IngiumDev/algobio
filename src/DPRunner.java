import Model.MSSFinder;
import Model.Subscore;

public class DPRunner extends MSSFinder {
    public static void main(String[] args) {
        int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
        //int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2, -8, 7, 3, -7, -4, -1, -2, -4, -3, -2, 4, 10, -5, 9, -1, -1, 8, -2, 8, -4, 2, -10, -7, -3, 4, 7, -7, -2, 5, 4, -8, -2, -4, 10, 8, -2, -10, 4, -5, -4, -1, -3, -5, -7, 4, -7, 3, -6, 4, -4, -7, -3, -6, 10, -9, -5, -6, -4, -8, 9, -6, -1, 5, 10, -3, -7, 10, 6, -6, 4, 10, -6, -3, 5, 6, -7, 3, -1, -7, 9, -7, 6, -10, 10, 10, 2, -2, 10, -10, -6, -9, -10, -3, 10, 6, 4, -4, 2, -4, -1, -5, 4, 4, -2, -3, -7, 5, -9, -3, 2, -5, -6, 5, -4, -2, -5, -6, 2, 4, -9, 4, 5, -2, -1, 7, 2, -1, -5, 9, -3, 10, -6, 1, -3, -10, -7, -10, 10, 5, -2, -9, -9, 10, -4, 5, -2, -3, -10, -1, 6, -6, -10, 9, -4, -6, -5, -9, 6, -7, -4, 10, 9, 4, 1, 10, -10, 2, -4, 6, -1, -2, -9, 10, -5, 6, 9, -4, -9, -3, 2, -6, -10, 9, -2, -9, 7, -3, 5, 5, -10, 2, -3, 4, 10, -10, -3, -10, -4, -10, -6, -2, 6, -4, -10, -3, -9, 8, 5, -10, -2, -9, -10, -10, 2, -10, -10, 7, 6, 10, -5, 8, -6, -4, -9, 6, -8, -4, 5, -10, -7, -5, -7, -5, 6, -10, -9, -10, -9, -9, -5, -8, -2, -5, -4, 10, -9, 9, -8, -10, -8, -10, -7, -7, 6, 9, -8, -9, -6, 9, -10, -5, 4, -7, -8, 2, -4, 10, -8, -8, -7, 4, 10, -3, 3, -7, -5, 3, -10, 7, -10, -10, -9, 9, -7, -5, 8, -6, 3, -10, -5, -5, 5, 8, 6, -2, 3, 2, -10, -10, -3, -9, 9, 2, -5, -4, -7, 8, -10, -2, -5, -5, -9, 2, -4, 6, -7, -10, 2, 5, 3, -6, 5, -7, -6, -6, -8, -4, -5, 8, -9, 7, -10, -8, -4, -8, -4, -9, 4, -8, 8, -5, -5, -7, -10, -7, 6, -7, -4, 5, -6, -9, -8, 9, 3, -8, 9, -3, 9, -5, -9, -9, -7, -4, 4, -4, -5, 2, -9, 7, -8, -9, 9, -6, -3, -5, -3, 2, -9, -6, -8, 4, -6, 9, -5, 7, -5, 3, -9, -10, 5, -4, -10, -6, 9, 4, -5, -6, -4, 3, -4, -8, -5, 9, -7, -8, 3, 10, 5, -10, -5, -9, -10, 7, -9, 3, -3, -4, 3, -6, -2, 3, 3, 9, -6, -8, -8, 3, 7, -6, -9, 5, -5, -8, -9, -7, -2, -8, 8, -6, -7, 5, -9, 2, 10, 9, -10, -2, 2, 2, -5, -9, -4, 4, -4, -4, -8, -2, -10, -6, 4, -2, 9, -6, -8, 10, -9, -4, -10, -5, 10, -3, -7, -8, -7, -10, -3, -8, 5, 10, -10, -3, -10, 7, 7, -4, 2, -4, -6, -9, -4, -9, -8, 10, -3, -4, -5, -3, -9, 4, 9, 7, 7, -5, -9, 10, 2, -5, 2, -4, -5, -8, -5, -6, -8, -6, -8, 5, -4, 9, -5, 7, -6, -9, -9, -5, 5, -3, -7, -7, -9, 4, -9, -9, 4, -3, -7, -9, 2, -8, -9, 7, 6, -7, -9, -4, -3, -10, -3, -3, 5, 8, -6, -8, -5, -7, 3, -8, -6, -4, -9, -6, -8, -7, -3, -5, 5, 10, -10, 5, 7, -3, -4, 9, -3, -6, -6, 6, -8, 3, -4, -4, -6, -6, 6, -7, 5, -8, -5, -9, -10, -9, -5, 8, -6, 3, -5, -6, 5, 5, 6, 8, -10, 8, -9, -5, 6, 6, -3, -3, -8, -9, -5, -9, 5, -7, 10, -10, -7, -5, -10, 6, 8, -3, -10, -6, -7, 5, -8, -9, -8, -4, 7, -7, -5, -9, 7, -8, -3, -10, -6, -7, -4, 8, 7, -7, -5, -8, 9, -6, -10, 8, 3, -6, -9, -6, 3, -5, -9, -10, -7, -8, -7, -8, -9, -4, -7, -10, -9, 5, -9, -5, -6, 10, -6, -10, 3, -8, 10, -3, -6, 3, -10, 9, 5, -8, 6, -9, 10, -9, -7, -9, 8, -6, -9, -6, -7, -7, -6, -5, -10, -7, -5, -4, -8, -6, -4, 3, -7, -8, -6, -5, 7, -8, -4, -10, -7, -9, -9, 8, -9, 4, -9, -10, -8, -6, 9, 4, 8, -9, -8, 6, 7, -8, -10, -8, -8, 6, -9, -5, -9, 5, -10, 9, -9, -7, -6, -6, -10, 7, -7, -5, -7, -4, -10, -10, -9, 7, -10, 4, -8, -6, -9, -5, -10, -5, -5, -5, -8, 6, -6, -9, -10, -8, -9, -10, 6, -7, -7, -6, -5, -5, -9, -9, -6, -7, 8, -7, -7, -9, -8, -5, -7, 9, -10, -8, -5, -9, -5, -5, -5, -6, 4, -8, 5, -10, -9, -6, -8, -5, -10, 9, -5, -9, -9, -8, -9, -5, -10, -9, -5, -6, -10, -6, -6, -6, -5, -9, -6, -9, -5, -10, -5, -9, -7, -8, -6, -5, -8, -7, -6, -8, -10, -10, -10, -9, -10, -10, -9, -9, -5, -10, -9, -9, -5, -5, -7, -8, -5, -6, -6, -5, -5, -6, -10, -6, -9, -6, -6, -6, -5, -10, -10, -10, -10, -9, -9, -9, -5, -10, -7, -10, -9, -6, -7, -10, -7, -10, -9, -6, -10, -10, -6, -7, -7, -7, -9, -5, -7, -5, -5, -9, -10, -10, -9, -10, -5, -9, -7, -5, -9, -5, -6, -7, -6, -6, -9, -9, -5, -10, -9, -7, -5, -10, -9, -9, -7, -6, -6, -10, -5, -9, -9, -5, -5, -5, -9, -5, -9, -6, -9, -6, -5, -9, -6, -7, -6, -10, -5, -10, -9, -9, -6, -5, -10, -10, -9, -10, -6, -10, -9, -5, -10, -6, -6, -9, -10, -5, -5, -5, -5, -10, -5, -10, -10, -6, -10, -10, -9, -9, -5, -5, -9, -9, -9, -10, -5, -9, -9, -9, -5, -5, -5, -5, -10, -6, -5, -9, -6, -6, -6, -5, -9, -10, -9, -10, -10, -5, -9, -10, -9, -6, -10, -6, -5, -6, -5, -10, -6, -10, -9, -6, -9, -9, -5, -9, -10, -5, -9, -9, -5, -5, -5, -5, -9, -5, -9, -6, -5, -6, -5, -9, -6, -10, -9, -5, -5, -10, -9, -5, -10, -9, -5, -5, -5, -9, -10, -6, -10, -9, -9, -6, -5, -9, -9, -9, -10, -5, -10, -6, -10, -6, -6, -9, -9, -6, -9, -9, -9, -10, -9, -9, -5, -9, -5, -10, -6, -5, -5, -10, -5, -9, -5, -5, -5, -9, -10, -10, -6, -9, -9, -5, -5, -9, -5, -10, -10, -6, -10, -9, -5, -10, -5, -9, -9, -10, -6, -6, -9, -9, -10, -6, -10, -6, -5, -9, -6, -6, -9, -5, -6, -9, -9, -10, -9, -6, -9, -9, -9, -5, -10, -10, -5, -5, -6, -9, -6, -9, -5, -9, -9, -9, -6, -6, -10, -6, -9, -9, -9, -6, -5, -9, -5, -10, -6, -5, -6, -10, -9, -9, -5, -5, -5, -5, -6, -9, -6, -10, -10, -9, -9, -5, -9, -9, -6, -9, -5, -5, -5, -10, -6, -6, -9, -6, -5, -5, -5, -9, -5, -10, -9, -9, -10, -9, -5, -6, -6, -9, -6, -9, -10, -9, -6, -6, -10, -5, -5, -6, -9, -9, -9, -9, -6, -6, -6, -9, -9, -10, -10, -6, -5, -5, -9, -5, -9, -5, -9, -6, -5, -9, -5, -6, -6, -9, -5, -6, -5, -5, -10, -9, -10, -9, -5, -5, -10, -5, -5, -5, -10, -6, -5, -6, -5, -10, -6, -9, -9, -5, -10, -5, -6, -10, -5, -10, -6, -5, -9, -9, -10, -6, -9, -9, -5, -5, -10, -9, -9, -10, -5, -9, -5, -6, -6, -10, -5, -5, -9, -9, -9, -6, -6, -5, -9, -5, -6, -5, -10, -6, -5, -10, -10, -5, -5, -6, -10, -6, -9, -6, -6, -5, -9, -6, -9, -6, -6, -10, -10, -5, -5, -6, -10, -6, -9, -6, -9, -9, -5, -5, -6, -6, -5, -9, -10, -5, -9, -6, -9, -5, -10, -5, -10, -5, -10, -10, -10, -10, -2, 11, 4, -2};
        long startTime = System.nanoTime();
        DPRunner runner = new DPRunner();
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
