package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class NameTheBabyTestCase implements TestProvider {
    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int l = rand.nextInt(20) + 1;
            long k = Math.abs(rand.nextLong()) % 1000000000L + 1;
            int n = rand.nextInt(5) + 1;
            StringBuilder sb = new StringBuilder("1\n" + l + " " + k + " " + n + "\n");
            for (int j = 0; j < n; j++) {
                sb.append(getString(rand.nextInt(8) + 1)).append('\n');
            }
            ret.add(new Test(sb.toString()));
        }
        return ret;
    }

    static int count;
    static final Random rand = new Random(1232L);

    static String getString(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) (rand.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    static void go(int x, int len, String s, List<String> list) {
        if (count == 0) {
            return;
        }
        if (x == len) {
            list.add(s);
            --count;
            return;
        }
        for (int i = 0; i < 4; i++) {
            go(x + 1, len, s + (char) (i + 'a'), list);
            if (count == 0) {
                return;
            }
        }
    }
}
