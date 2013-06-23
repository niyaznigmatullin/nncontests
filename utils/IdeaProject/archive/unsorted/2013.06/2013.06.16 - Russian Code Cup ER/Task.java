package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        List<Integer> answer = new ArrayList<>();
        int last = -1;
        for (int i = n; i >= 0; i--) {
            List<Integer> d = get(n, last == -1 ? (1 << i) - 1 : (last & (last - 1)));
            answer.addAll(d);
            last = answer.get(answer.size() - 1);
        }
        for (int i : answer) {
            for (int j = 0; j < n; j++) {
                out.print((i >> j) & 1);
            }
            out.println();
        }
    }

    static List<Integer> get(int bits, int mask) {
        List<Integer> ret = new ArrayList<>();
        int k = Integer.bitCount(mask);
        if (k == bits || k == 0) {
            ret.add(mask);
            return ret;
        }
        int nMask = mask & ((1 << bits - 1) - 1);
        List<Integer> z = get(bits - 1, nMask);
        for (int i : z) {
            ret.add((mask & (1 << bits - 1)) | i);
        }
        mask = ret.get(ret.size() - 1);
        for (int i = 0; i < bits - 1; i++) {
            if (((mask >> i) & 1) != ((mask >> bits - 1) & 1)) {
                mask ^= (1 << i) | (1 << bits - 1);
                break;
            }
        }
        nMask = mask & ((1 << bits - 1) - 1);
        z = get(bits - 1, nMask);
        for (int i : z) {
            ret.add((mask & (1 << bits - 1)) | i);
        }
        return ret;
    }
}
