package coding;

import net.egork.chelper.task.Test;
import net.egork.chelper.tester.TestProvider;

import java.util.*;

public class AutocompleteTestCase implements TestProvider {

    static int solve(String[] s) {
        Set<String> dictionary = new HashSet<>();
        int ans = 0;
        for (String e : s) {
            String cur = "";
            for (char c : e.toCharArray()) {
                cur += c;
                boolean has = false;
                for (String z : dictionary) {
                    if (z.startsWith(cur)) {
                        has = true;
                        break;
                    }
                }
                if (!has) break;
            }
            ans += cur.length();
            dictionary.add(e);
        }
        return ans;
    }

    static final Random RNG = new Random(12323L);

    static String[] genTest() {
        String[] ret = new String[RNG.nextInt(10) + 1];
        for (int i = 0; i < ret.length; i++) {
            int letters = RNG.nextInt(2) + 6;
            int len = RNG.nextInt(50) + 1;
            ret[i] = "";
            for (int j = 0; j < len; j++) {
                ret[i] += (char) ('a' + RNG.nextInt(letters));
            }
        }
        return ret;
    }

    public Collection<Test> createTests() {
        List<Test> ret = new ArrayList<>();
        for (int it = 0; it < 100; it++) {
            int tCount = 100;
            StringBuilder input = new StringBuilder();
            StringBuilder output = new StringBuilder();
            input.append(tCount).append('\n');
            for (int i = 0; i < tCount; i++) {
                String[] f = genTest();
                input.append(f.length).append('\n');
                for (String e : f) {
                    input.append(e).append('\n');
                }
                output.append("Case #").append(i + 1).append(": ").append(solve(f)).append('\n');
            }
            ret.add(new Test(input.toString(), output.toString()));
        }
        return ret;
    }
}
