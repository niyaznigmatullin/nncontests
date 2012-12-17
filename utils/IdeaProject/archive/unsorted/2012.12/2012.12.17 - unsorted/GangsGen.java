package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;
import net.egork.chelper.tester.Tester;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 17.12.12
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
public class GangsGen implements TestProvider {

    static final Random rand = new Random(23144324L);

    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<Test>();
        for (int it = 0; it < 1000; it++) {
            int n = rand.nextInt(50) + 1;
            StringBuilder sb = new StringBuilder();
            int sum = 0;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = rand.nextInt(10) + 1;
                sum += a[i];
            }
            sb.append(sum).append(" ").append(n).append("\n");
            for (int i : a) {
                sb.append(i).append("\n");
            }
            ret.add(new Test(sb.toString(), "YES", 0));
        }
        return ret;
    }
}
