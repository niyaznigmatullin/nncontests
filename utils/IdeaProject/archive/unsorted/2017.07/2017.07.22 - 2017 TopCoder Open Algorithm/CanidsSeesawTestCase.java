package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class CanidsSeesawTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> ret = new ArrayList<>();
        Random rng = new Random(123);
        for (int it = 0; it < 1000; it++) {
            int n = rng.nextInt(10) + 1;
            int[] a = genArray(rng, n);
            int[] b = genArray(rng, n);
            int k = rng.nextInt(n) + 1;
            ret.add(new NewTopCoderTest(new Object[]{a, b, k}));
        }
        return ret;
    }

    private int[] genArray(Random rng, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rng.nextInt(10) + 1;
        return a;
    }
}
