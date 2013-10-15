package lib.test.on2013_08.on2013_08_27_.Mammoth;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class MammothTestCase implements TestProvider {
    public Collection<Test> createTests() {
//        return Collections.emptyList();
        List<Test> ret = new ArrayList<>();
        Random rand = new Random(234312L);
        for (int it = 0; it < 100; it++) {
            StringBuilder sb = new StringBuilder();
            sb.append(rand.nextInt(20) - 10).append(' ').append(rand.nextInt(20) - 10).append(' ').append(rand.nextInt(10) + 1).append('\n');
            sb.append(rand.nextInt(20) - 10).append(' ').append(rand.nextInt(20) - 10).append('\n');
            sb.append(rand.nextInt(20) - 10).append(' ').append(rand.nextInt(20) - 10).append('\n');
            ret.add(new Test(sb.toString()));
        }
        return ret;
    }
}
