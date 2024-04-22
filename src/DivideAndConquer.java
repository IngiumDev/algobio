import Model.MSSFinder;
import Model.Subscore;

public class DivideAndConquer implements MSSFinder {
    public static void main(String[] args) {
        int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
        long startTime = System.nanoTime();
        DivideAndConquer runner = new DivideAndConquer();
        Subscore result = runner.findMSS(testcase, testcase.length);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        //System.out.println("Eingabe: " + arrayStr(testcase));
        System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    @Override
    public Subscore findMSS(int[] arr, int n) {
        return MSS_DC(arr, 1, n - 1);
    }

    public Subscore MSS_DC(int[] arr, int i, int j) {
        if (i == j) {
            if (arr[i] > 0) {
                return new Subscore(i, i, arr[i]);
            } else {
                return new Subscore(i, i - 1, 0);
            }
        } else {
            int m = (i + j - 1) / 2;
            Subscore left = MSS_DC(arr, i, m);
            int s1 = left.getScore();
            int i1 = left.getPair().getL();
            int j1 = left.getPair().getR();
            Subscore right = MSS_DC(arr, m + 1, j);
            int s2 = right.getScore();
            int i2 = right.getPair().getL();
            int j2 = right.getPair().getR();
            int i3 = m;
            int s = arr[i3];
            int simax = s;
            for (int k = i3 - 1; k >= i; k--) {
                s = s + arr[k];
                if (s > simax) {
                    simax = s;
                    i3 = k;
                }
            }
            int j3 = m + 1;
            s = arr[j3];
            int sjmax = s;

            for (int k = j3 + 1; k <= j; k++) {
                s = s + arr[k];
                if (s > sjmax) {
                    sjmax = s;
                    j3 = k;
                }
            }
            int s3 = simax + sjmax;
            if (s1 >= s2 && s1 >= s3) {
                return new Subscore(i1, j1, s1);
            } else if (s2 >= s1 && s2 >= s3) {
                return new Subscore(i2, j2, s2);
            } else {
                return new Subscore(i3, j3, s3);
            }
        }

    }
}
