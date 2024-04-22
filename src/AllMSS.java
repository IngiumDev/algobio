import Model.MSSFinder;
import Model.Subscore;

import java.util.ArrayList;

public class AllMSS implements MSSFinder {

    public Subscore findMSS(int[] arr, int n) {
        int max = 0, l = 1, r = 0, rmax = 0, rstart = 1;
        //ArrayList<Subscore>
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
