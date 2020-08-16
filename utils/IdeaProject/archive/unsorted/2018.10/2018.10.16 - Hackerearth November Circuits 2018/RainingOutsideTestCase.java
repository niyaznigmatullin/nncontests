package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;
import java.util.stream.Collectors;

public class RainingOutsideTestCase implements TestProvider {
    static Random rng = new Random(123L);

    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        for (int it = 0; it < 400; it++) {
            StringBuilder sb = new StringBuilder();
            int TESTS = 100;
            sb.append(TESTS).append('\n');
            for (int z = 0; z < TESTS; z++) {
                int n = rng.nextInt(10) + 2;
                int m = rng.nextInt(10) + 2;
                int[] a = getArray(n, 15);
                int[] b = getArray(m, 15);
                sb.append(n).append(" ").append(m).append("\n");
                sb.append(outputArray(a)).append('\n');
                sb.append(outputArray(b)).append('\n');
            }
            ret.add(new Test(sb.toString()));
        }
        return ret;
    }

    static int[] getArray(int len, int maxValue) {
        int[] a = new int[len];
        for (int i = 0; i < len; i++) a[i] = rng.nextInt(maxValue);
        return a;
    }

    static String outputArray(int[] a) {
        return Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }
}
