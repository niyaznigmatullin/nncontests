package coding;

import java.util.BitSet;
import java.util.HashSet;

public class LittleElephantAndIntervalsDiv2 {
    public int getNumber(int m, int[] l, int[] r) {
        int n = l.length;
        HashSet<BitSet> ret = new HashSet<>();
        for (int mask = 0; mask < 1 << n; mask++) {
            BitSet b = new BitSet(m);
            for (int i = 0; i < n; i++) {
                boolean bit = ((mask >> i) & 1) == 1;
                b.set(l[i], r[i] + 1, bit);
            }
            ret.add(b);
        }
        return ret.size();
    }
}
