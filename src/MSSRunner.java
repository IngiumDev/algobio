import Model.MSSFinder;
import Model.Subscore;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.helper.HelpScreenException;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.MutuallyExclusiveGroup;
import net.sourceforge.argparse4j.inf.Namespace;

import static net.sourceforge.argparse4j.impl.Arguments.storeTrue;

public class MSSRunner {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("MSSRunner").build().defaultHelp(true).description("Find Maximum Subsequence Sum");
        MutuallyExclusiveGroup group = parser.addMutuallyExclusiveGroup().required(true);
        group.addArgument("--algorithm").choices("naive", "rekursiv", "dynamic-programming", "divide-and-conquer", "optimal").metavar("<naive|rekursiv|dynamic-programming|divide-and-conquer|optimal>").help("Algorithm to use");
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
        MSSFinder finder = switch (algorithm) {
            case "naive" -> new naivRunner();
            case "rekursiv" -> new rekursivRunner();
            case "dynamic-programming" -> new DPRunner();
            case "divide-and-conquer" -> new DivideAndConquer();
            case "optimal" -> new OptimalRunner();
            default -> throw new IllegalArgumentException("Invalid algorithm");
        };


        long startTime = System.nanoTime();
        Subscore result = finder.findMSS(MSSUtils.TEST_CASE, MSSUtils.TEST_CASE.length);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double microseconds = (double) duration / 1000;
        System.out.println("Eingabe: " + MSSUtils.arrayStr(MSSUtils.TEST_CASE));
        System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
        System.out.println("Laufzeit: " + microseconds + " μs");
    }

    private static void startBenchmark() {
        MSSFinder[] finders = MSSUtils.createMSSFinderInstances();
        for (int size : MSSUtils.SIZES_TO_BENCHMARK) {
            int[] arr = MSSUtils.createRandomArray(size);
            System.out.println("Benchmarking for size " + size + " started");
            for (MSSFinder finder : finders) {
                if ((finder.getClass().getName().equals("naivRunner") || finder.getClass().getName().equals("rekursivRunner")) && size > Math.pow(10, 3)) {
                    // TOO SLOW
                    System.out.println("Skipping " + finder.getClass().getSimpleName() + " for size " + size);
                } else if (finder.getClass().getName().equals("DPRunner") && size > Math.pow(10, 4)) {
                    // TOO Much Space
                    System.out.println("Skipping " + finder.getClass().getSimpleName() + " for size " + size);
                } else if (finder.getClass().getName().equals("DivideAndConquer") && size > Math.pow(10, 9)) {
                    // TOO SLOW
                    System.out.println("Skipping " + finder.getClass().getSimpleName() + " for size " + size);
                } else {

                long startTime = System.nanoTime();
                Subscore result = finder.findMSS(arr, arr.length);
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                double microseconds = (double) duration / 1000;
                //System.out.println("Eingabe: " + MSSUtils.arrayStr(arr));
                System.out.println("Algorithm: " + finder.getClass().getSimpleName());
                System.out.println("Ausgabe: " + "[" + result.getPair().getL() + "," + result.getPair().getR() + "] mit Score " + result.getScore());
                System.out.println("Laufzeit: " + microseconds + " μs"); }
            }
            System.out.println("-----------------------------------");
        }
    }


}
