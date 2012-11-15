package mypackage;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        List<Integer> a = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int prePeriod = -1;
        int period = -1;
        int x = 1;
        for (int i = 0; ; i++) {
            if (map.containsKey(x)) {
                prePeriod = map.get(x);
                period = i - prePeriod;
                break;
            }
            a.add(x);
            map.put(x, i);
            x = x * 2;
            char[] c = (x + "").toCharArray();
            Arrays.sort(c);
            x = Integer.parseInt(new String(c));
        }
        int n = in.nextInt() - 1;
        if (n > prePeriod) {
            n -= prePeriod;
            n %= period;
            n += prePeriod;
        }
        out.println(a.get(n));
	}
}
