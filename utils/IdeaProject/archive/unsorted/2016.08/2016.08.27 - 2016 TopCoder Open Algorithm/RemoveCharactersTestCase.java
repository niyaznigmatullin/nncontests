package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class RemoveCharactersTestCase {

    static final int ALPHABET = 15;
    static final Random rng = new Random(12123);

    static String gen(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) (rng.nextInt(ALPHABET) + 'a'));
        }
        return sb.toString();
    }

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        List<NewTopCoderTest> ret = new ArrayList<>();
        for (int curTest = 0; curTest < 100; curTest++) {
            String s = gen(rng.nextInt(10) + 1);
            String t = gen(rng.nextInt(10) + 1);
            int ans = Integer.MAX_VALUE;
            for (int mask = 0; mask < 1 << ALPHABET; mask++) {
                StringBuilder s1 = new StringBuilder();
                StringBuilder t1 = new StringBuilder();
                for (char c : s.toCharArray()) {
                    if (((mask >> (c - 'a')) & 1) == 1) continue;
                    s1.append(c);
                }
                for (char c : t.toCharArray()) {
                    if (((mask >> (c - 'a')) & 1) == 1) continue;
                    t1.append(c);
                }
                if (s1.toString().equals(t1.toString())) {
                    ans = Math.min(ans, Integer.bitCount(mask));
                }
            }
            ret.add(new NewTopCoderTest(new Object[]{s, t}, ans));
        }
        return ret;
    }
}
