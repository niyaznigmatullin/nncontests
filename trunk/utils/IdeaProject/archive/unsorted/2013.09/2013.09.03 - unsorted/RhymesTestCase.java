package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class RhymesTestCase implements TestProvider {
    public Collection<Test> createTests() {
        if (true)
        return Collections.emptyList();
        List<Test> ret = new ArrayList<>();
        Random rand = new Random(1232L);
        for (int it = 0; it < 100; it++) {
            int n = rand.nextInt(10) + 1;
            StringBuilder sb = new StringBuilder(n + "\n");
            for (int i = 0; i < n; i++) {
                sb.append(rand.nextInt(10) + 1).append(' ');
            }
            ret.add(new Test(sb.toString()));
        }
        return ret;
    }
}
