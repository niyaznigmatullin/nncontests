package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class OperationWithSetsTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<Test>();
        StringBuilder sb = new StringBuilder();
        for (int test = 0; test < 10; test++) {
            sb.append('(');
            sb.append('{');
            Random rand = new Random(34L);
            final int n = 130000;
            int balance = 1;
            for (int i = 0; i < n; i++) {
                if (rand.nextInt(5) == 0) {
                    sb.append(i).append('}');
                    if (rand.nextInt(2) == 0 && balance > 0) {
                        sb.append(')');
                        --balance;
                    }
                    sb.append(rand.nextInt(5) != 0 ? '|' : '&');
                    if (rand.nextInt(3) == 0) {
                        sb.append('(');
                        ++balance;
                    }
                    if (rand.nextInt(3) == 0) {
                        sb.append('!');
                    }
                    sb.append('{');
                } else {
                    sb.append(i).append(',');
                }
            }
            sb.append(n).append('}');
            while (balance > 0) {
                --balance;
                sb.append(')');
            }
            sb.append('\n');
        }
        ret.add(new Test(sb.toString(), "" + sb.length()));
        return ret;
    }
}
