package blatt2;

import blatt2.structs.Graph;

import static blatt2.structs.Graph.createGraphWithTiming;

public class Aufgabe1 {
    public static void main(String[] args) {


        Graph g = createGraphWithTiming("src/blatt2/data/cities.250.tsv");
        System.out.println(g.getDistance(g.getCities().get(1576), g.getCities().get(790)));
    }
}
