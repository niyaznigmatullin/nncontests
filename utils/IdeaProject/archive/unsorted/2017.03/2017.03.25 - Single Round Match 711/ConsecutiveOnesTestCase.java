package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class ConsecutiveOnesTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<>();
        Random rng = new Random(123213L);
        for (int i =0; i < 1000; i++) {
            tests.add(new NewTopCoderTest(new Object[]{rng.nextLong() & ((1L << 50) - 1), rng.nextInt(50) + 1}));
        }
        return tests;
    }
}
