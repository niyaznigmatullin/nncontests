package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class BuffingPixieTestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> tests = new ArrayList<>();
        Random rng = new Random(1232132);
        for (int it = 0; it < 10000; it++) {
            int hp = rng.nextInt(10) + 1;
            int a = rng.nextInt(100) + 1;
            int b = rng.nextInt(100) + 1;
            int ans = (hp + a - 1) / a;
            for (int i = 0; i * a < hp ; i++) {
                int d = (hp - i * a + b - 1) / b;
                d *= 2;
                if (d + i < ans) ans = d + i;
            }
            tests.add(new NewTopCoderTest(new Object[]{hp, a, b}, ans));
        }
        return tests;
    }
}
