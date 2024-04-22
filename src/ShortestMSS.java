import Model.Subscore;

import java.util.ArrayList;

public class ShortestMSS {

    public ArrayList<Subscore> findMSS(int[] arr, int n) {
        int max = 0, rmax = 0, rstart = 1;
        int l = 1;
        int r = 0;
        ArrayList<Subscore> list = new ArrayList<>();
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
                list.clear();
                list.add(new Subscore(l, r, max));

            } else if (rmax == max) {
                if (!(l == rstart)) {
                    l = rstart;
                    r = i;
                    if (!list.isEmpty()) {
                        if (r-l < list.get(0).getPair().getR() - list.get(0).getPair().getL()){
                            list.clear();
                            list.add(new Subscore(l, r, max));
                        }
                        else if (r-l == list.get(0).getPair().getR() - list.get(0).getPair().getL()){
                            list.add(new Subscore(l, r, max));
                        }
                    }
                }
            }
        }
        return list;
    }
}
