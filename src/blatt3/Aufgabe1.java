package blatt3;

import blatt2.structs.City;
import blatt2.structs.CityPair;
import blatt2.structs.Graph;
import blatt2.structs.Pair;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.*;

import static blatt2.Aufgabe3.kruskal;
import static blatt2.utils.readSeparator;

public class Aufgabe1 {
    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("Aufgabe1").build().defaultHelp(true).description("Find 2-Approximation for TSP");
        parser.addArgument("-c", "--cities").required(true).help("File path for the cities file").metavar("<cities>");

        parser.addArgument("-m", "--mst").help("File path for the MST file (optional)").metavar("<mst>");


        try {
            Namespace res = parser.parseArgs(args);
            start(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start(Namespace res) {
        Graph g = new Graph(res.getString("cities"));
        if (res.get("mst") != null) {
            // Read in the lines of the file and create an ArrayList of CityPairs
            List<List<String>> data = readSeparator(res.getString("mst"), "\t");
            List<CityPair> cityPairs = new ArrayList<>();
            for (List<String> line : data) {

                City city1 = new City(Integer.parseInt(line.get(0)));
                City city2 = new City(Integer.parseInt(line.get(1)));
                cityPairs.add(new CityPair(city1, city2));
            }
            runTwoApproximationTSP(cityPairs, g);
        } else {
            runTwoApproximationTSP(Arrays.stream(kruskal(g)).toList(), g);
        }
    }

    private static void runTwoApproximationTSP(List<CityPair> cityPairs, Graph g) {
        //
        int startCityId = cityPairs.getFirst().getCity1().getId();

        // DFS
        List<Integer> visited = new ArrayList<>();
        visited.add(startCityId);
        depthFirstSearch(startCityId, visited, cityPairs);

        // Remove duplicate cities (triangle inequality)
        List<Integer> visitedWithoutDuplicates = new ArrayList<>();
        Set<Integer> visitedSet = new HashSet<>();
        for (int cityId : visited) {
            if (visitedSet.add(cityId)) {
                visitedWithoutDuplicates.add(cityId);
            }
        }

        //  Add the last city to the beginning to complete the cycle
        visitedWithoutDuplicates.add(visitedWithoutDuplicates.getFirst());

        // Now we have the cycle, we can create a List of Pair<CityPair, Double> to represent the cycle
        List<Pair<CityPair, Double>> cycle = new ArrayList<>();
        for (int i = 0; i < visitedWithoutDuplicates.size() - 1; i++) {
            int city1Id = visitedWithoutDuplicates.get(i);
            int city2Id = visitedWithoutDuplicates.get(i + 1);
            City city1 = g.getCities().get(city1Id);
            City city2 = g.getCities().get(city2Id);
            CityPair cityPair = new CityPair(city1, city2);
            double distance = g.getDistance(city1Id, city2Id);
            cycle.add(new Pair<>(cityPair, distance));
        }
        System.out.println("source\ttarget\tweight");
        for (Pair<CityPair, Double> pair : cycle) {
            CityPair cityPair = pair.getFirst();
            double distance = pair.getSecond();
            System.out.println(cityPair.getCity1().getId() + "\t" + cityPair.getCity2().getId() + "\t" + distance);
        }


    }

    private static void depthFirstSearch(int cityID, List<Integer> visited, List<CityPair> cityPairs) {

        List<Integer> destinations = getDestinations(cityID, cityPairs);
        int lastVisited = visited.getLast();
        visited.add(cityID);
        for (int destination : destinations) {
            if (destination == lastVisited) {
                continue;
            }

            depthFirstSearch(destination, visited, cityPairs);
            visited.add(cityID);
        }
    }

    private static List<Integer> getDestinations(int cityID, List<CityPair> cityPairs) {
        List<Integer> destinations = new ArrayList<>();
        for (CityPair cityPair : cityPairs) {
            if (cityPair.getCity1().getId() == cityID) {
                destinations.add(cityPair.getCity2().getId());
            } else if (cityPair.getCity2().getId() == cityID) {
                destinations.add(cityPair.getCity1().getId());
            }
        }
        return destinations;
    }


}
