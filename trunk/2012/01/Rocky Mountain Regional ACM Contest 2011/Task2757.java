package mypackage;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.HashMap;
import java.util.HashSet;

public class Task2757 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        if (n == 0 && m == 0) {
            throw new UnknownError();
        }
        HashSet<String> set = new HashSet<String>();
        out.println("Case " + testNumber);
        all:
        for (int curName = 0; curName < n; curName++) {
            String nameString = in.nextLine().trim();
            String firstName = nameString.substring(0, nameString.indexOf(' ')).toLowerCase();
            String lastName = nameString.substring(nameString.lastIndexOf(' ') + 1).toLowerCase();
            StringBuilder ans = new StringBuilder("" + firstName.charAt(0));
            for (int j = 0; j < lastName.length() && ans.length() < m; j++) {
                if (Character.isLetter(lastName.charAt(j))) {
                    ans.append(lastName.charAt(j));
                }
            }
            if (set.add(ans.toString())) {
                out.println(ans.toString());
                continue;
            }
            for (int i = 1; i < 10; i++) {
                String name = (ans.length() == m ? ans.toString().substring(0, ans.length() - 1) : ans.toString()) + i;
                if (set.add(name)) {
                    out.println(name);
                    continue all;
                }
            }
            for (int i = 10; i < 100; i++) {
                String name = (ans.length() == m ? ans.toString().substring(0, ans.length() - 2) : ans.length() == m - 1 ? ans.toString().substring(0, ans.length() - 1) : ans.toString())
                        + i;
                if (set.add(name)) {
                    out.println(name);
                    continue all;
                }
            }
            throw new AssertionError();
        }
    }
}
