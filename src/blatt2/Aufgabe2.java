package blatt2;

import blatt2.structs.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Aufgabe2 {

    private static HashMap<Integer, Boolean> visited;
    private static HashMap<Integer, ArrayList<Integer>> adjacent;
    private static ArrayList<String> tsv;
    private static Graph g;
    private static int stepCount = 0;
    private static String outFilePath = "cities.250.bfs.tsv";

    public static void main(String[] args) throws IOException {
        visited = new HashMap<>();
        adjacent = new HashMap<>();
        tsv = new ArrayList<>();

        g = new Graph("src/cities.250.tsv");
        g.getDistances().entrySet().removeIf(entry -> entry.getValue() >= 20.24);

        for (Integer cityId : g.getCities().keySet()) {
            visited.put(cityId, false);
        }

        long startTime = System.nanoTime();

        startDfs(g);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000_000;

        System.out.println("traversed graph in " + stepCount + " steps in " + microseconds + " ms");
        writeBfsStepsToFile(outFilePath);
    }

    private static void startDfs(Graph g) {
        for (CityPair pair : g.getDistances()
                .keySet()) {
            int city1 = pair.getCity1().getId();
            int city2 = pair.getCity2().getId();

            // Add city2 to the adjacency list of city1
            adjacent.computeIfAbsent(city1, k -> new ArrayList<>()).add(city2);

             // Add city1 to the adjacency list of city2
            adjacent.computeIfAbsent(city2, k -> new ArrayList<>()).add(city1);
        }
        int minKey = g.getCities().keySet().stream().min(Comparator.naturalOrder()).get();
        dfs(minKey, -1);
    }

    private static void dfs(int at, int prev) {
        if (prev >= 0) {
            double distance = utils.calculateDistance(g.getCities().get(at), g.getCities().get(prev));
            String distanceStr = String.format("%.2f", distance);
            tsv.add(prev + "\t" + at + "\t" + distanceStr);
        }
        stepCount++;
        visited.put(at, true);
        sortAdjacentCities(at);

        for (Integer i : adjacent.get(at)) {
            if (!visited.get(i)) {
                dfs(i, at);
            }
        }
    }

    private static void sortAdjacentCities(int currentCityId) {
        ArrayList<Integer> adjacentCities = adjacent.get(currentCityId);
        if (adjacentCities != null) {
            Collections.sort(adjacentCities, Comparator.comparingDouble(cityId -> utils.calculateDistance(g.getCities().get(currentCityId), g.getCities().get(cityId))));
        }
    }

    private static void writeBfsStepsToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("id (von)\tid (nach)\tDistanz\n");
            for (String step : tsv) {
                writer.write(step);
                writer.newLine();
            }
        }
    }
}
