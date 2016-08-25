package coding;

import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class ParenthesesAndPermutationTestCase {

    static Random rnd;

    static String gen(int len) {
        if (len == 0) return "";
        int len1 = rnd.nextInt(len);
        return "(" + gen(len1) + ")" + gen(len - 1 - len1);
    }

    @TestCase
    public Collection<NewTopCoderTest> createTests() {
        rnd  = new Random(12323);
        List<NewTopCoderTest> tests = new ArrayList<>();
        for (int it = 0; it < 10000; it++) {
            int len = rnd.nextInt(20) + 1;
            String f1 = gen(len);
            String f2 = gen(len);
            int[] p = new int[2 * len];
            for (char c : "()".toCharArray()) {
                List<Integer> z1 = getIndices(f1, c);
                List<Integer> z2 = getIndices(f2, c);
                Collections.shuffle(z2, rnd);
                for (int i = 0; i < z1.size(); i++) {
                    p[z1.get(i)] = z2.get(i);
                }
            }
            tests.add(new NewTopCoderTest(new Object[]{p}));
        }
        return tests;
    }

    private List<Integer> getIndices(String f1, char c) {
        List<Integer> z1 = new ArrayList<>();
        for (int i = 0; i < f1.length(); i++) {
            if (f1.charAt(i) == c) {
                z1.add(i);
            }
        }
        return z1;
    }
}
