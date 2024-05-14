package blatt1;

import blatt1.Model.MultiMSSFinder;
import blatt1.Model.Subscore;

import java.util.ArrayList;

public class OverlappingMSS extends MultiMSSFinder {

    /*
    Hier wird der optimale Algorithmus aus der Vorlesung implementiert, mit dem Unterschied, dass zusätzlich eine ArrayList verwaltet wird, sodass alle MSS gespeichert werden.
    Somit kommen ein paar Elementaroperationen (Vergleiche, Löschen & Addieren von Listenelementen) hinzu, der Algorithmus läuft aber nach wie vor in linearer Zeit.
     */

    public ArrayList<Subscore> findMSS(int[] arr, int n) {
        int max = 0, rmax = 0, rstart = 1;
        ArrayList<Integer> prefixList = new ArrayList<>();
        int r = 0;
        ArrayList<Subscore> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (rmax > 0) {
                rmax += arr[i];
            } else if (rmax == 0) {
                prefixList.add(rstart);
                rmax = arr[i];
                rstart = i;

            } else {
                rmax = arr[i];
                rstart = i;
                prefixList.clear();
            }
            if (rmax > max) {
                max = rmax;
                r = i;
                list.clear();
                list.add(new Subscore(rstart, r, max));

            } else if (rmax == max) {
                r = i;
                list.add(new Subscore(rstart, r, max));
                for (Integer k : prefixList) {
                    list.add(new Subscore(k, r, max));
                }
            }
        }
        return list;
    }
}
