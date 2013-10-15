package lib.test.on2013_08.on2013_08_26_Codeforces_Round__197__Div__2_.E___Three_Swaps;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import ru.ifmo.niyaz.arrayutils.ArrayUtils;

import java.util.*;

public class TaskETestCase implements TestProvider {
    public Collection<Test> createTests() {
//        if (true) return Collections.emptyList();
        List<Test> a = new ArrayList<>();
        Random rand = new Random(123823);
        for (int it = 0; it < 1000; it++) {
            int n = rand.nextInt(5) + 3;
            int[] p = new int[n];
            for (int i = 0; i < n; i++) p[i] = i;
            int IT = rand.nextInt(4);
            StringBuilder zz = new StringBuilder();
            for (int i = 0; i < IT; i++) {
                int x = rand.nextInt(n);
                int y = rand.nextInt(n);
                while (x >= y) {
                    x = rand.nextInt(n);
                    y = rand.nextInt(n);
                }
                for (int c = x, d = y; c < d; c++, d--) {
                    int t = p[c];
                    p[c] = p[d];
                    p[d] = t;
                }
                zz.append(Arrays.toString(p)).append('\n');
                zz.append(x).append(' ').append(y).append('\n');
            }
            StringBuilder sb = new StringBuilder();
            sb.append(n).append('\n');
            for (int i : p) {
                sb.append(i + 1).append(' ');
            }
            sb.append('\n');
            a.add(new Test(sb.toString() + "\n\n" + zz.toString(), ""));
        }
        return a;
    }

}
