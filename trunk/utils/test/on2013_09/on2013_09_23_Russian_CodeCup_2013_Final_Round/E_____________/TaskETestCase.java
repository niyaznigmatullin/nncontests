package lib.test.on2013_09.on2013_09_23_Russian_CodeCup_2013_Final_Round.E_____________;



import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class TaskETestCase implements TestProvider {
    public Collection<Test> createTests() {
        Random rand = new Random(12323L);
        StringBuilder sb = new StringBuilder("50\n");
        StringBuilder ans = new StringBuilder();
        for (int it = 0; it < 50; it++) {
            int m = rand.nextInt(5) + 1;
            int[] year = new int[m];
            int[] inc = new int[m];
            int curYear = 0;
            for (int i = 0; i < m; i++) {
                year[i] = curYear + rand.nextInt(10) + 1;
                curYear = year[i];
                inc[i] = rand.nextInt(10);
            }
//            System.out.println(Arrays.toString(year));
            int k = rand.nextInt(7) + 1;
            int n = 10;
            sb.append(m + " " + n + " " + k + "\n");
            for (int i = 0; i < m; i++) sb.append(year[i]).append(" ").append(inc[i]).append("\n");
            for (int i = 0; i < n; i++) {
                int start = rand.nextInt(10 * m);
                sb.append(start).append(' ');
                int end = rand.nextInt(10 * m) + start;
                sb.append(end).append(' ');
                int z = rand.nextInt(20) + 1;
                sb.append(z).append('\n');
                for (int y = start; y <= end; y++) {
                    int id = Arrays.binarySearch(year, y);
                    if (id >= 0) z += inc[id];
                    if (y != end) {
                        z = Math.max(0, z - k);
                    }
                }
                ans.append(z).append('\n');
            }
        }
        List<Test> test = new ArrayList<>();
        test.add(new Test(sb.toString(), ans.toString()));
        return test;
    }
}
