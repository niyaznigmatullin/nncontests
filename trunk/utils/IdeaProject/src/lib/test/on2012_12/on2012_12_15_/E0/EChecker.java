package lib.test.on2012_12.on2012_12_15_.E0;





import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 15.12.12
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class EChecker implements TestProvider {

    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<Test>();
        for (int it = 0; it < 0; it++) {
            int n = 300;
            String[] s = new String[n];
            for (int i = 0; i < n; i++) {
                s[i] = randomString(300);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(n).append('\n');
            for (String e : s) {
                sb.append(e).append('\n');
            }
            ret.add(new Test(sb.toString(), "", 0));
        }
        return ret;
    }

    static final Random rng = new Random();

    static String randomString(int len) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < len; i++) {
            ret.append((char) ('A' + rng.nextInt(26)));
        }
        return ret.toString();
    }

}
