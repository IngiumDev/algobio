import Model.Subscore;

import java.util.ArrayList;

public class AllMSS {

    /*
    Hier wird der optimale Algorithmus aus der Vorlesung implementiert, mit dem Unterschied, dass zusätzlich eine ArrayList verwaltet wird, sodass alle MSS gespeichert werden.
    Somit kommen ein paar Elementaroperationen (Vergleiche, Löschen & Addieren von Listenelementen) hinzu, der Algorithmus läuft aber nach wie vor in linearer Zeit.
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
                    list.add(new Subscore(l, r, max));
                }
            }
        }
        return list;
    }
}
