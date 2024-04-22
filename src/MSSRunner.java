import Model.MSSFinder;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.helper.HelpScreenException;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.MutuallyExclusiveGroup;
import net.sourceforge.argparse4j.inf.Namespace;

import static net.sourceforge.argparse4j.impl.Arguments.storeTrue;

public class MSSRunner {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("MSSRunner").build().defaultHelp(true).description("Find Maximum Subsequence Sum");
        MutuallyExclusiveGroup group = parser.addMutuallyExclusiveGroup();
        group.addArgument("--algorithm").choices("naive", "rekursiv","dynamic-programming","divide-and-conquer","optimal").metavar("<naive|rekursiv|dynamic-programming|divide-and-conquer|optimal>").help("Algorithm to use");
        group.addArgument("--benchmark").action(storeTrue()).help("Benchmark the algorithms");

        try {
            Namespace res = parser.parseArgs(args);
            start(res);
        } catch (HelpScreenException e) {
            e.printStackTrace();
        }catch (Exception e) {
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

    private static void startBenchmark() {
    }

    private static void startAlgorithm(String algorithm) {
        MSSFinder finder;
        switch (algorithm) {
            case "naive":
               finder = new naivRunner();
        }
    }


}
