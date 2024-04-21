public class rekursivRunner {

    private static int l;
    private static int r;
    private static int maxscore;

    public static void main(String[] args) {

        int[] testcase = {5, -2, 5, -2, 1, -9, 12, -2, 24, -5, 13, -12, 3, -13, 5, -3, 2, -1, 2};
        long startTime = System.nanoTime();
        recursive(testcase, testcase.length);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        System.out.println("Eingabe: " + arrayStr(testcase));
        System.out.println("Ausgabe: " + "[" + l + "," + r + "] mit Score " + maxscore);
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    public static void recursive(int[] a, int n) {
        maxscore = 0;
        l = 1;
        r = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int s = calculateScore(i, j, a);
                if (s >= maxscore) {
                    maxscore = s;
                    l = i;
                    r = j;
                }
            }
        }
    }

    public static int calculateScore(int i, int j, int[] arr) {
        if (i > j) return 0;
        else if (i == j) {
            return arr[i];
        } else {
            int k = (i + j) / 2;
            return calculateScore(i, k, arr) + calculateScore(k+1, j, arr);
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