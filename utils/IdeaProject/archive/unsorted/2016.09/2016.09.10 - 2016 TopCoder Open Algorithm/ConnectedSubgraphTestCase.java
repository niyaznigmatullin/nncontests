package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ConnectedSubgraphTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> ret = new ArrayList<>();
        for (int i = 63000; i <= 65535; i++) {
            ret.add(new NewTopCoderTest(new Object[]{i}));
        }
        return ret;
    }
}
