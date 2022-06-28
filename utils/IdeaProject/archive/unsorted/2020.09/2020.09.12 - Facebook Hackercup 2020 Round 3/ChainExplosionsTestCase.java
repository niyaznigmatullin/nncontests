package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ChainExplosionsTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int n = 100000;
        sb.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(2000000 - 2 * i).append('\n');
        }
//        ret.add(new Test(sb.toString()));
        return ret;
    }
}
