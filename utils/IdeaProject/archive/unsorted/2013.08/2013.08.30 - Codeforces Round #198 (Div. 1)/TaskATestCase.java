package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskATestCase implements TestProvider {
    public Collection<Test> createTests() {
//        return Collections.emptyList();
        List<Test> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder("100000\n");
        for (int i = 0; i < 100000; i++) {
            sb.append(10000000 - i).append(" ");
        }
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
