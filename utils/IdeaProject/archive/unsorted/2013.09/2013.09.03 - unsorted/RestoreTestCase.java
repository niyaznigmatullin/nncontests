package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RestoreTestCase implements TestProvider {
    public Collection<Test> createTests() {
//        return Collections.emptyList();
        List<Test> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(200).append('\n');
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                if (i == j) sb.append(0); else sb.append(200);
                sb.append(' ');
            }
            sb.append('\n');
        }
        ret.add(new Test(sb.toString()));
        return ret;
    }
}
