package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TaskCTestCase implements TestProvider {
    public Collection<Test> createTests() {
//        return Collections.emptyList();
        List<Test> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder("2000\n");
        for (int i = 0; i < 2000; i++) {
            sb.append("-1 ");
        }
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
