package blatt1;

import blatt1.Model.MultiMSSFinder;
import blatt1.Model.Subscore;

import java.util.ArrayList;

public class ShortestMSS extends MultiMSSFinder {

    /*
    Hier wird der optimale Algorithmus aus der Vorlesung implementiert, mit dem Unterschied, dass zusätzlich eine ArrayList verwaltet wird, sodass alle MSS gespeichert werden.
    Im Vergleich zu AllMSS muss hier noch für jede MSS die Länge untersucht werden, dementsprechend kommen ein paar Elementaroperationen hinzu.
    Die Komplexität ist ebenfalls O(n).
     */

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
                        if (r - l < list.get(0).getPair().getR() - list.get(0).getPair().getL()) {
                            list.clear();
                            list.add(new Subscore(l, r, max));
                        } else if (r - l == list.get(0).getPair().getR() - list.get(0).getPair().getL()) {
                            list.add(new Subscore(l, r, max));
                        }
                    }
                }
            }
        }
        return list;
    }
}
