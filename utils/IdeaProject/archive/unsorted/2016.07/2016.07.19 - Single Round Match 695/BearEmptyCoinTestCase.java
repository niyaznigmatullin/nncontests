package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class BearEmptyCoinTestCase {

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        Random rng = new Random(12323);
        List<NewTopCoderTest> list = new ArrayList<>();
        for (int it = 0; it <= 20; it++) {
            list.add(new NewTopCoderTest(new Object[]{rng.nextInt(60) + 1, rng.nextInt(1000000000) + 1}));
        }
        return list;
    }
}
