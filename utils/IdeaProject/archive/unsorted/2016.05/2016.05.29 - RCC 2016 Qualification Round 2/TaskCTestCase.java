package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class TaskCTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> list = new ArrayList<>();
        Random rnd = new Random(123);
        for (int it = 0; it < 20; it++) {
            StringBuilder sb = new StringBuilder();
            sb.append("100\n");
            for (int it2 = 0; it2 < 100; it2++) {
                int len = rnd.nextInt(20) + 1;
                for (int i = 0; i < len; i++) {
                    sb.appendCodePoint(rnd.nextInt(3) + 'a');
                }
                sb.append('\n');
                sb.append(rnd.nextInt(5) + 1);
                sb.append('\n');
            }
            list.add(new Test(sb.toString()));
        }
        return list;
//        return Collections.emptyList();
    }
}
