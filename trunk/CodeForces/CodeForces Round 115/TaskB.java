package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String name = in.next();
            if (map.containsKey(name)) {
                map.put(name, Math.max(in.nextInt(), map.get(name)));
            } else {
                map.put(name, in.nextInt());
            }
        }
        Map.Entry<String, Integer>[] en = map.entrySet().toArray(new Map.Entry[map.size()]);
        out.println(en.length);
        for (int i = 0; i < en.length; i++) {
            int countMore = 0;
            for (int j = 0; j < en.length; j++) {
                if (en[j].getValue() > en[i].getValue()) {
                    countMore++;
                }
            }
            out.println(en[i].getKey() + " " + getAns(countMore, en.length));
        }
    }

    static String getAns(int a, int b) {
        int c = b - a;
        if (c * 100 >= b * 99) {
            return "pro";
        } else if (c * 100 >= b * 90 && a * 100 > b) {
            return "hardcore";
        } else if (c * 100 >= b * 80 && a * 10 > b) {
            return "average";
        } else if (c * 100 >= b * 50 && a * 5 > b) {
            return "random";
        } else {
            return "noob";
        }
    }
}
