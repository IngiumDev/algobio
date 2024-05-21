package blatt2;

import blatt2.structs.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Aufgabe3 {

    private static Graph g;
    private static String outFilePath = "cities.250.mst.edgelist";

    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    public static void main(String[] args) throws IOException {
        g = new Graph("src/cities.250.tsv");
        long startTime = System.nanoTime();
        CityPair[] res = kruskal(g);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double milliSeconds = (double) duration / 1000_000;

        double sum = 0;
        for (CityPair c : res) {
            sum += utils.calculateDistance(c.getCity1(), c.getCity2());
        }

        writeMSTStepsToFile(outFilePath, res);

        System.out.println("done creating mst with " + res.length + " edges in " + milliSeconds + " ms");
        System.out.println("sum of edge weights: " + sum);
    }

    public static CityPair[] kruskal(Graph g) {
        int j = 0;
        int noOfEdges = 0;
        int V = g.getCities().size();
        List<Map.Entry<CityPair, Double>> edges = getSortedEdges(g.getDistances());

        // Allocate memory for creating V subsets
        HashMap<Integer, Subset> subsets = new HashMap();
        for (Integer i : g.getCities().keySet()) {
            subsets.put(i, new Subset(i, 0));
        }

        // Allocate memory for results
        CityPair results[] = new CityPair[V - 1];

        while (noOfEdges < V-1) {

            // Pick the smallest edge. And increment
            // the index for next iteration
            CityPair nextEdge = edges.get(j).getKey();

            int x = findRoot(subsets, nextEdge.getCity1().getId());
            int y = findRoot(subsets, nextEdge.getCity2().getId());

            // If including this edge doesn't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                /*System.out.println(nextEdge.getCity1().getId());
                System.out.println(nextEdge.getCity2().getId());
                System.out.println(utils.calculateDistance(nextEdge.getCity1(), nextEdge.getCity2()));
                System.out.println("\n---------\n");*/
                results[noOfEdges] = nextEdge;
                union(subsets, x, y);
                noOfEdges++;
            }

            j++;
        }
        return results;
    }

    public static List<Map.Entry<CityPair, Double>> getSortedEdges(Map<CityPair, Double> distances) {
        // Convert the map entries to a list
        List<Map.Entry<CityPair, Double>> edgeList = new ArrayList<>(distances.entrySet());

        // Sort the list based on the distance value
        edgeList.sort(Map.Entry.comparingByValue());

        return edgeList;
    }

    private static void union(HashMap<Integer, Subset> subsets, int x, int y) {
        int rootX = findRoot(subsets, x);
        int rootY = findRoot(subsets, y);

        if (subsets.get(rootY).rank < subsets.get(rootX).rank) {
            subsets.get(rootY).parent = rootX;
        } else if (subsets.get(rootX).rank < subsets.get(rootY).rank) {
            subsets.get(rootX).parent = rootY;
        } else {
            subsets.get(rootY).parent = rootX;
            subsets.get(rootX).rank++;
        }
    }

    private static int findRoot(HashMap<Integer, Subset> subsets, int i) {
        if (subsets.get(i).parent == i)
            return subsets.get(i).parent;

        subsets.get(i).parent = findRoot(subsets, subsets.get(i).parent);
        return subsets.get(i).parent;
    }

    private static void writeMSTStepsToFile(String filePath, CityPair[] res) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (CityPair c : res) {
                String distance = String.format("%.2f", utils.calculateDistance(c.getCity1(), c.getCity2()));
                writer.write(c.getCity1().getId() + "\t" + c.getCity2().getId() + "\t" + distance);
                writer.newLine();
            }
        }
    }
}
