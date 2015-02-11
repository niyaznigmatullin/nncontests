package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class HigherTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        Random rand = new Random(123L);
        StringBuilder sb = new StringBuilder();
        for (int it = 0; it < 100; it++) {
            int n = rand.nextInt(5) + 1;
            sb.append(n).append('\n');
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sb.append(rand.nextInt(10)).append(' ');
                }
                sb.append('\n');
            }
        }
        sb.append(0).append('\n');
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
