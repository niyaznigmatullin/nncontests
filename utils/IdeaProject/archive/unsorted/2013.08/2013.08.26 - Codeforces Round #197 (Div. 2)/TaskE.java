package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[] a = in.readIntArray(n);
        for (int i = 0; i < n; i++) --a[i];
        Random random = new Random();
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < n; ) {
            int j = i + 1;
            if (j >= n || Math.abs(a[j] - a[i]) != 1) {
                list.add(new Element(a[i], a[i]));
                i = j;
                continue;
            }
            j++;
            while (j < n && a[j] - a[j - 1] == a[i + 1] - a[i]) {
                ++j;
            }
            list.add(new Element(a[i], a[j - 1]));
            i = j;
        }
//        if (list.size() > 10) {
//            throw new AssertionError();
//        }
        answer = new ArrayList<>();
        if (!go(0, list)) throw new AssertionError();
        Collections.reverse(answer);
        out.println(answer.size());
        for (Element e : answer) {
            out.println(e.l + 1 + " " + e.r);
        }
    }

    static List<Element> answer;

    static boolean go(int v, List<Element> list) {
        {
            int last = -1;
            boolean ok = true;
            for (int i = 0; i < list.size(); i++) {
                Element e = list.get(i);
                if (e.l != last + 1) {
                    ok = false;
                    break;
                }
                last = e.r;
            }
            if (ok) {
                return true;
            }
        }
        if (v == 3) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                if (i == j && list.get(i).l == list.get(i).r) continue;
                reverse(list, i, j);
                if (go(v + 1, list)) {
                    reverse(list, i, j);
                    int countL = 0;
                    for (int k = 0; k < i; k++) {
                        Element e = list.get(k);
                        countL += Math.abs(e.l - e.r) + 1;
                    }
                    int countR = 0;
                    for (int k = 0; k <= j; k++) {
                        Element e = list.get(k);
                        countR += Math.abs(e.l - e.r) + 1;
                    }
                    answer.add(new Element(countL, countR));
                    return true;
                }
                reverse(list, i, j);
            }
        }
        return false;
    }

    static void reverse(List<Element> list, int l, int r) {
        for (int i = l, j = r; i < j; i++, j--) {
            Element t = list.get(i);
            list.set(i, list.get(j));
            list.set(j, t);
        }
        for (int i = l; i <= r; i++) {
            Element e = list.get(i);
            int t = e.l;
            e.l = e.r;
            e.r = t;
        }
    }

    static class Element {
        int l;
        int r;

        Element(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public String toString() {
            return "Element{" +
                    "l=" + l +
                    ", r=" + r +
                    '}';
        }
    }
}
