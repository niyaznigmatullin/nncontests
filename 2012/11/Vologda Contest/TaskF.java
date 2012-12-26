package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskF {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.next();
        int answer = 0;
        Map<String, Integer> map = new HashMap<String, Integer>();
        int sign = 1;
        List<Integer> stack = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); ) {
            int curSign = s.charAt(i) == '-' ? -1 : 1;
            if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                ++i;
            }
            int zSign = curSign;
            curSign *= sign;
            while (s.charAt(i) == '(') {
                sign *= zSign;
                stack.add(zSign);
                zSign = 1;
                ++i;
            }
            String t = "";
            while (i < s.length() && (Character.isDigit(s.charAt(i)) || Character.isLetter(s.charAt(i)))) {
                t += s.charAt(i);
                ++i;
            }
            if (t.charAt(0) >= '0' && t.charAt(0) <= '9') {
                answer += curSign * Integer.parseInt(t);
            } else {
                if (!map.containsKey(t)) {
                    map.put(t, 0);
                }
                map.put(t, map.get(t) + curSign);
            }
            while (i < s.length() && s.charAt(i) == ')') {
                sign *= stack.remove(stack.size() - 1);
                ++i;
            }
        }
        out.println("niyazvar = " + answer);
        for (String z : map.keySet()) {
            if (map.get(z).intValue() != 0) {
                int k = map.get(z);
                char c;
                if (k < 0) {
                    c = '-';
                    k = -k;
                } else {
                    c = '+';
                }
                while (k-- > 0) {
                    out.println("niyazvar = niyazvar " + c + " " + z);
                }
            }
        }
        out.println("print niyazvar");
    }

}
