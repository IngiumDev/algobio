import Model.MSSFinder;
import Model.Subscore;

public class OptimalRunner implements MSSFinder {

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
    }
