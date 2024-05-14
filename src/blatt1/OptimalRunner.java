package blatt1;

import blatt1.Model.MSSFinder;
import blatt1.Model.Subscore;

public class OptimalRunner extends MSSFinder {

    public Subscore findMSS(int[] arr, int n) {
        int max = 0, l = 1, r = 0, rmax = 0, rstart = 1;
        for (int i = 0; i < n; i++) {
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
}
