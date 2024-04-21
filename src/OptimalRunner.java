import Model.MSSFinder;
import Model.Subscore;

public class OptimalRunner implements MSSFinder {
    public static void main(String[] args) {
        int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
        long startTime = System.nanoTime();
        OptimalRunner runner = new OptimalRunner();
        Subscore result = runner.findMSS(testcase, testcase.length );
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        System.out.println("Eingabe: " + arrayStr(testcase));
        System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    public Subscore findMSS(int[] arr, int n) {
        int max = 0, l = 1, r = 0, rmax = 0, rstart = 1;
        for (int i=1; i<n;i++) {
           if (rmax > 0) {
                rmax += arr[i];
              } else {
                rmax = arr[i];
                rstart = i;
           }
           if (rmax > max) {
                max = rmax;
                l = rstart;
                r = i;
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
