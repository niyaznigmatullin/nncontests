package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class PencilGameTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        int n = 1000;
        StringBuilder sb = new StringBuilder();
        sb.append(n).append('\n');
        Random rand = new Random(12323L);
        for (int i = 0; i < n; i++) {
            sb.append(rand.nextInt(6) + 1).append(' ').append(rand.nextInt(6) + 1).append(' ').append(rand.nextInt(100) + 1).append('\n');
        }
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
