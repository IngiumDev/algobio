package blatt3;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
            HashSet<String> activeGenes = loadActiveGenes(activeGenesPath);
            HashSet<String> inActiveGenes = new HashSet<>(networkMap.keySet());
            inActiveGenes.removeAll(activeGenes);

            makeSAT(networkMap, activeGenes, inActiveGenes);

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

    private static HashSet<String> loadActiveGenes(String filePath) throws IOException {
        HashSet<String> activeGenes = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            activeGenes.add(line.trim());
        }
        reader.close();
        return activeGenes;
    }

    private static void makeSAT(HashMap<String, HashSet<String>> networkMap, HashSet<String> activeGenes, HashSet<String> inActiveGenes) {
        for (String gene : activeGenes) {
            for (String tf : networkMap.get(gene)) {
                System.out.print(tf + " ");
            }
            System.out.println();
        }
    }
}