package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class SeatingTestCase0 implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<Test>();
        Random rand = new Random(3434L);
        for (int it = 0; it < 10; it++) {
            StringBuilder sb = new StringBuilder();
            sb.append("500000 300000\n");
            for (int i = 0; i < 300000; i++) {
                if (rand.nextBoolean()) {
                    sb.append("A ").append(rand.nextInt(500000) + 1).append('\n');
                } else {
                    int left = rand.nextInt(500000);
                    int right = rand.nextInt(500000 - left) + left + 1;
                    sb.append('L').append(' ').append(left + 1).append(' ').append(right).append('\n');
                }
            }
            ret.add(new Test(sb.toString()));
        }
        return ret;
    }
}
