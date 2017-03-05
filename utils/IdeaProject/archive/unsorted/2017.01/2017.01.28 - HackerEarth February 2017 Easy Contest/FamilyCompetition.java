package coding;

import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FamilyCompetition {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int h = in.nextInt();
        Member[] a = new Member[n];
        int minX = 0;
        int maxX = 0;
        for (int i = 0; i < n; i++) {
            a[i] = new Member(in.nextInt(), in.nextInt());
            minX = Math.min(a[i].x, minX);
            maxX = Math.max(a[i].x, maxX);
        }
        Arrays.sort(a, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                return Integer.compare(o1.age, o2.age);
            }
        });
        MinSegmentTree dp = new MinSegmentTree(maxX - minX + 1);
        int[] value = new int[maxX - minX + 1];
        for (int i = 0; i < value.length; i++) {
            dp.set(i, value[i]);
        }
        int ans = 0;
        int[] best = new int[n];
        for (int i = 0; i < n; ) {
            int j = i;
            while (j < n && a[i].age == a[j].age) ++j;
            for (int k = i; k < j; k++) {
                Member e = a[k];
                int val = 1;
                if (e.x < 0) {
                    if (e.x + h > 0) {
                        val = Math.max(val, -dp.getMin(-minX, Math.min(e.x + h, maxX) + 1 - minX) + 1);
                    }
                } else {
                    if (e.x - h < 0) {
                        val = Math.max(val, -dp.getMin(Math.max(e.x - h, minX) - minX, -minX) + 1);
                    }
                }
                best[k] = val;
            }
            while (i < j) {
                int val = best[i];
                Member e = a[i++];
                int curX = e.x - minX;
                if (value[curX] < val) {
                    value[curX] = val;
                    dp.set(curX, -val);
                }
                ans = Math.max(ans, val);
            }
        }
        out.println(ans - 1);
    }

    static class Member {
        int x;
        int age;

        public Member(int age, int x) {
            this.x = x;
            this.age = age;
        }
    }
}
