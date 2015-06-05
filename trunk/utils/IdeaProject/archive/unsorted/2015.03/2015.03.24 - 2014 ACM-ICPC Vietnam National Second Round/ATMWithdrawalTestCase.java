package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class ATMWithdrawalTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
//        if (true) return ret;
        Random rng = new Random(1232L);
        for (int z = 0; z < 1; z++) {
            int n = 10000;
            StringBuilder sb = new StringBuilder(n + "\n");
            for (int it = 0; it < n; it++) {
                int w = rng.nextInt(10000) * 1000;
                int c = rng.nextInt(7) + 1;
                sb.append(w).append(' ').append(c).append('\n');
            }
            ret.add(new Test(sb.toString()));
        }
        return ret;
    }
}
