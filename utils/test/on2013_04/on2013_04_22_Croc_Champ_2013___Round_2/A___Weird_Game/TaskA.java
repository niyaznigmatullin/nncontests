package lib.test.on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.A___Weird_Game;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.List;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        String s = in.next();
        String t = in.next();
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        int c4 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1' && t.charAt(i) == '1') {
                ++c1;
            } else if (s.charAt(i) == '0' && t.charAt(i) == '0') {
                ++c2;
            } else if (s.charAt(i) == '1') {
                ++c3;
            } else {
                ++c4;
            }
        }
        int first = 0;
        int second = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i & 1) == 0) {
                if (c1 > 0) {
                    --c1;
                    first++;
                } else if (c3 > 0) {
                    --c3;
                    first++;
                } else if (c4 > 0) {
                    --c4;
                } else {
                    --c2;
                }
            } else {
                if (c1 > 0) {
                    --c1;
                    second++;
                } else if (c4 > 0) {
                    --c4;
                    ++second;
                } else if (c3 > 0) {
                    --c3;
                } else {
                    --c2;
                }
            }
        }
        if (first > second) {
            out.println("First");
        } else if (first < second) {
            out.println("Second");
        } else {
            out.println("Draw");
        }
    }
}
