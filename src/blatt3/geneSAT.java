package blatt3;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class geneSAT {
    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("geneSAT").build().defaultHelp(true).description("GeneSAT Application");
        parser.addArgument("-network").required(true).help("Path to the regulatory network file");
        parser.addArgument("-actives").required(true).help("Path to the active genes file");

        try {
            Namespace res = parser.parseArgs(args);
            String regNetworkPath = res.get("network");
            String activeGenesPath = res.get("actives");

            HashMap<String, HashSet<String>> networkMap = loadNetwork(regNetworkPath);
            HashSet<String> activeGenes = loadActiveGenes(activeGenesPath, networkMap);
            HashSet<String> inActiveGenes = new HashSet<>(networkMap.keySet());
            inActiveGenes.removeAll(activeGenes);
            Map<String, Integer> TFmap = mapTFs(regNetworkPath);

            makeSAT(networkMap, activeGenes, inActiveGenes, TFmap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static HashMap<String, HashSet<String>> loadNetwork(String filePath) throws IOException, IOException {
        HashMap<String, HashSet<String>> networkMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            if (parts.length == 2) {
                String tf = parts[0];
                String gene = parts[1];

                networkMap.computeIfAbsent(gene, k -> new HashSet<>()).add(tf);
            }
        }
        reader.close();
        return networkMap;
    }

    private static HashSet<String> loadActiveGenes(String filePath, HashMap<String, HashSet<String>> networkMap) throws IOException {
        HashSet<String> activeGenes = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            activeGenes.add(line.trim());
        }
        reader.close();

        // Keep only the genes that are also keys in the networkMap
        activeGenes.retainAll(networkMap.keySet());

        return activeGenes;
    }

    private static void makeSAT(HashMap<String, HashSet<String>> networkMap, HashSet<String> activeGenes, HashSet<String> inActiveGenes, Map<String, Integer> TFmap) {
        System.out.println("p cnf " + TFmap.size() + " " + (activeGenes.size() + inActiveGenes.size()));
        for (String gene : activeGenes) {
            for (String tf : networkMap.get(gene)) {
                Integer tfIndex = TFmap.get(tf);
                System.out.print(tfIndex + " ");
            }
            System.out.print("0\n");
        }
        for (String gene : inActiveGenes) {
            for (String tf : networkMap.get(gene)) {
                Integer tfIndex = TFmap.get(tf);
                System.out.print("-" + tfIndex + " ");
            }
            System.out.print("0\n");
        }
    }

    public static Map<String, Integer> mapTFs(String filePath) throws IOException {
        Map<String, Integer> tfMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int count = 1;

        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("#")) { // Skip the header line
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String tf = parts[0];
                    if (!tfMap.containsKey(tf)) {
                        tfMap.put(tf, count++);
                    }
                }
            }
        }
        reader.close();
        return tfMap;
    }
}