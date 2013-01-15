package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class TaskDTestCase0 implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret =new ArrayList<Test>();
        int n = 20000;
        int m = 20000000 / n;
        StringBuilder sb = new StringBuilder("10 " + n + " " + m + " " + m / 2 + "\n");
        Random rand = new Random(1232L);
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < n; i++) {
                sb.append(rand.nextInt(m) + 1).append(' ');
            }
            sb.append('\n');
        }
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
