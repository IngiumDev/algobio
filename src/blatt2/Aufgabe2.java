package blatt2;

import blatt2.structs.*;

import java.util.*;

public class Aufgabe2 {

    private static HashMap<Integer, Boolean> visited;
    private static HashMap<Integer, ArrayList<Integer>> adjacent;

    public static void main(String[] args) {
        visited = new HashMap<>();
        adjacent = new HashMap<>();
        Graph g = new Graph("/Users/luis/Downloads/cities.250.tsv");
        startDfs(g);
    }

    private static void startDfs(Graph g) {
        /*for (CityPair pair : g.getDistances()
                .keySet()) {
            int city1 = pair.getCity1();
            int city2 = pair.getCity2();

            // Add city2 to the adjacency list of city1
            adjacent.computeIfAbsent(city1, k -> new ArrayList<>()).add(city2);

            // Add city1 to the adjacency list of city2
            adjacent.computeIfAbsent(city2, k -> new ArrayList<>()).add(city1);
        }*/
        int minKey = g.getCities().keySet().stream().min(Comparator.naturalOrder()).get();
        dfs(minKey);
    }

    private static void dfs(int at) {
        if (visited.get(at)) {
            return;
        }
        visited.put(at, true);

    }
}
