package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class TaskDTestCase implements TestProvider {
    public Collection<Test> createTests() {
        StringBuilder sb = new StringBuilder();
        Random rng = new Random(17823);
        int n = 500;
        int m = rng.nextInt((n * n * 2) / 200);
        sb.append(n).append(' ').append(m).append('\n');
        for (int i = 0; i < m; i++) {
            int v = rng.nextInt(n) + 1;
            int u = rng.nextInt(n) + 1;
            int t = rng.nextInt(2);
            sb.append(v).append(" ").append(u).append(" ").append(t).append('\n');
        }
        List<Test> ret = new ArrayList<>();
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
