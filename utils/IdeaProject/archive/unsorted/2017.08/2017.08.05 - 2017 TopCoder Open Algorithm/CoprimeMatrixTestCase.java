package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;
import ru.ifmo.niyaz.math.MathUtils;

import java.util.*;

public class CoprimeMatrixTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        Random rng = new Random(123L);
        List<NewTopCoderTest> ret = new ArrayList<>();
        for (int it = 0; it < 1000; it++) {
            int n = rng.nextInt(50) + 1;
            long start = rng.nextInt(1000000000) * 1000000000L + rng.nextInt(1000000000);
//            long start = rng.nextInt(10) + 1;
//            long start = 8;
//            int n = 7;
            String[] z = new String[n];
            for (int i = 0; i < n; i++) {
                StringBuilder s = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    s.append(MathUtils.gcd(i + start, j + start) == 1 ? "Y" : "N");
                }
                z[i] = s.toString();
            }
            ret.add(new NewTopCoderTest(new Object[]{z}, "Possible"));
            System.out.println(it + " " + start);
        }
        return ret;
    }
}
