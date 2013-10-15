package lib.test.on2013_08.on2013_08_27_Single_Round_Match_589.FlippingBitsDiv1;



import net.egork.chelper.task.NewTopCoderTest;
import net.egork.chelper.tester.TestCase;

import java.util.*;

public class FlippingBitsDiv1TestCase {
    @TestCase
    public Collection<NewTopCoderTest> createTests() {
//        if (true)
//        return Collections.emptyList();
        List<NewTopCoderTest> list = new ArrayList<>();
        Random rand = new Random(1232L);
        for (int it = 0; it < 1000; it++) {
            int n = rand.nextInt(6) + 1;
            String[] s = new String[n];
            int sumLen = 0;
            for (int i = 0; i < n; i++) {
                s[i] = "";
                int len = rand.nextInt(50) + 1;
                for (int j = 0; j < len; j++) {
                    s[i] += rand.nextInt(2);
                }
                sumLen += len;
            }
            int M = rand.nextInt(sumLen) + 1;
            list.add(new NewTopCoderTest(new Object[]{s, M}));
        }
        return list;
    }
}
