package blatt1;

import blatt1.Model.MultiMSSFinder;
import blatt1.Model.Subscore;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.helper.HelpScreenException;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.MutuallyExclusiveGroup;
import net.sourceforge.argparse4j.inf.Namespace;

import java.util.ArrayList;

import static net.sourceforge.argparse4j.impl.Arguments.storeTrue;

public class MultiMSSRunner {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("MultiMSSRunner").build().defaultHelp(true).description("Find Maximum Subsequences");
        MutuallyExclusiveGroup group = parser.addMutuallyExclusiveGroup().required(true);
        group.addArgument("--algorithm").choices("minimal", "shortest", "overlapping").metavar("<minimal|shortest|overlapping>").help("Algorithm to use");
        group.addArgument("--benchmark").action(storeTrue()).help("Benchmark the algorithms");

        try {
            Namespace res = parser.parseArgs(args);
            start(res);
        } catch (HelpScreenException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void start(Namespace res) {
        if (res.get("algorithm") != null) {
            startAlgorithm(res.getString("algorithm"));
        } else {
            startBenchmark();
        }
    }

    private static void startAlgorithm(String algorithm) {
        MultiMSSFinder finder = switch (algorithm) {
            case "minimal" -> new AllMSS();
            case "shortest" -> new ShortestMSS();
            case "overlapping" -> new OverlappingMSS();

            default -> throw new IllegalArgumentException("Invalid algorithm");
        };


        long startTime = System.nanoTime();
        ArrayList<Subscore> result = finder.findMSS(MSSUtils.TEST_CASE, MSSUtils.TEST_CASE.length);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        System.out.println("Eingabe: " + MSSUtils.arrayStr(MSSUtils.TEST_CASE));
        //System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
        for (Subscore s : result) {
            System.out.println("Ausgabe: " + "[" + s.getPair().getL() + "," + s.getPair().getR() + "] mit Score " + s.getScore());
        }
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    private static void startBenchmark() {
        MultiMSSFinder[] finders = MSSUtils.createMultiMSSFinderInstances();
        ArrayList<ArrayList<Double>> results = new ArrayList<>();
        for (MultiMSSFinder finder : finders) {
            results.add(new ArrayList<>());
        }
        for (int size : MSSUtils.SIZES_TO_BENCHMARK) {
            int[] arr = MSSUtils.createRandomArray(size);
            System.out.println("Benchmarking for size " + size + " started");
            for (int i = 0; i < finders.length; i++) {
                MultiMSSFinder finder = finders[i];

                long startTime = System.nanoTime();
                ArrayList<Subscore> result = finder.findMSS(arr, arr.length);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                double microseconds = (double) duration / 1000;
                results.get(i).add(microseconds);
                //System.out.println("Eingabe: " + MSSUtils.arrayStr(arr));
                System.out.println("Algorithm: " + finder.getClass().getSimpleName());
                for (Subscore s : result) {
                    System.out.println("Ausgabe: " + "[" + s.getPair().getL() + "," + s.getPair().getR() + "] mit Score " + s.getScore());
                }
                System.out.println("Laufzeit: " + microseconds + " μs");

            }
            System.out.println("-----------------------------------");
        }
        System.out.println("test");

        saveResults(results, finders);
    }

    private static void saveResults(ArrayList<ArrayList<Double>> results, MultiMSSFinder[] finders) {
        // Write to CSV output txt. row = size, col = algorithm
        try (java.io.FileWriter writer = new java.io.FileWriter("benchmark_results.csv")) {
            writer.write("Algorithm/Size");
            for (int size : MSSUtils.SIZES_TO_BENCHMARK) {
                writer.write("," + size);
            }
            writer.write("\n");
            for (int i = 0; i < finders.length; i++) {
                writer.write(finders[i].getClass().getSimpleName());
                for (double time : results.get(i)) {
                    writer.write("," + time);
                }
                writer.write("\n");
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

    }


}
