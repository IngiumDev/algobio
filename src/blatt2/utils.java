package blatt2;

import blatt2.structs.City;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class utils {

    public static double calculateDistance(City city1, City city2) {
        return calculateDistance(city1.getPhi(), city1.getLambda(), city2.getPhi(), city2.getLambda());
    }

    public static double calculateDistance(double phi1, double lambda1, double phi2, double lambda2) {
        return Math.sqrt(Math.pow(phi1 - phi2, 2) + Math.pow(lambda1 - lambda2, 2));
    }


    public static List<List<String>> readSeparator(String filepath, String separator) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(separator));
                data.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
