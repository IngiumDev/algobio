package blatt2.structs;

import blatt2.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    Map<Integer, City> cities;
    Map<CityPair, Double> distances;


    public Graph(Map<Integer, City> cities, Map<CityPair, Double> distances) {
        this.cities = cities;
        this.distances = distances;
    }

    public Graph() {
        cities = new HashMap<>();
        distances = new HashMap<>();
    }

    public Graph(String filepath) {
        cities = new HashMap<>();
        distances = new HashMap<>();
        List<List<String>> lines = utils.readSeparator(filepath, "\t");
        addCities(lines);
        calculateDistances();
    }

    public void addCities(List<List<String>> lines) {
        for (List<String> line : lines) {
            int id = Integer.parseInt(line.get(0));
            String name = line.get(1);
            double phi = Double.parseDouble(line.get(2));
            double lambda = Double.parseDouble(line.get(3));
            City city = new City(id, name, phi, lambda);
            cities.put(id, city);
        }
    }

    public void calculateDistances() {
        for (City city1 : cities.values()) {
            for (City city2 : cities.values()) {
                if (city1.getId() < city2.getId()) {
                    CityPair pair = new CityPair(city1, city2);
                    double distance = utils.calculateDistance(city1, city2);
                    distances.put(pair, distance);
                }
            }
        }
    }

    public static Graph createGraphWithTiming(String filepath) {
        Graph g = new Graph();
        long startTime = System.currentTimeMillis();
        List<List<String>> lines = utils.readSeparator(filepath, "\t");
        g.addCities(lines);
        long endTime = System.currentTimeMillis();
        // read 250 cities in 79 ms
        System.out.print("read " + g.cities.size() + " cities in " + (endTime - startTime) + "ms\n");
        //generated 62250 edges in 17 ms
        startTime = System.currentTimeMillis();
        g.calculateDistances();
        endTime = System.currentTimeMillis();
        System.out.print("generated " + g.distances.size() + " edges in " + (endTime - startTime) + "ms\n");
        return g;
    }

    public Map<CityPair, Double> getDistances() {
        return distances;
    }

    public void setDistances(Map<CityPair, Double> distances) {
        this.distances = distances;
    }

    public Map<Integer, City> getCities() {
        return cities;
    }

    public void setCities(Map<Integer, City> cities) {
        this.cities = cities;
    }

    public double getDistance(int id1, int id2) {
        return getDistance(cities.get(id1), cities.get(id2));
    }

    public double getDistance(City city1, City city2) {
        // Check which city has the lower id
        if (city1.getId() < city2.getId()) {
            return distances.get(new CityPair(city1, city2));
        } else {
            return distances.get(new CityPair(city2, city1));
        }
    }


}
