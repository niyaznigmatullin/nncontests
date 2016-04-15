package coding;

import java.util.Arrays;

public class BearFair {
    public String isFair(int n, int b, int[] upTo, int[] quantity) {
        int q = upTo.length;
        upTo = Arrays.copyOf(upTo, q + 1);
        quantity = Arrays.copyOf(quantity, q + 1);
        upTo[q] = b;
        quantity[q] = n;
        ++q;
        for (int i = 0; i < q; i++) {
            for (int j = i + 1; j < q; j++) {
                if (upTo[i] > upTo[j]) {
                    int t = upTo[i];
                    upTo[i] = upTo[j];
                    upTo[j] = t;
                    t = quantity[i];
                    quantity[i] = quantity[j];
                    quantity[j] = t;
                }
            }
        }
        int last = 0;
        int lastHave = 0;
        int minEven = 0;
        int minOdd = 0;
        for (int i = 0; i < q; i++) {
            int from = last + 1;
            int to = upTo[i];
            int curHave = quantity[i] - lastHave;
            int even = to / 2 - (from + 1) / 2 + 1;
            int odd = (to - from + 1) - even;
            if (curHave > to - from + 1 || curHave < 0) {
                return "unfair";
            }
            minEven += Math.max(0, curHave - odd);
            minOdd += Math.max(0, curHave - even);
            last = to;
            lastHave = quantity[i];
        }
        return minEven <= n / 2 && minOdd <= n / 2 ? "fair" : "unfair";
    }
}
