package coding;

import java.util.BitSet;

public class XorCards {
    public long numberOfWays(long[] number, long limit) {
        final int BITS = 60;
        long ans = 0;
        for (int bit = BITS; bit >= 0; bit--) {
            if (((limit >> bit) & 1) == 0) {
                continue;
            }
            long cur = limit >> bit;
            cur &= ~1;
            cur <<= bit;
            BitSet[] bs = new BitSet[BITS - bit + 1];
            for (int i = BITS; i >= bit; i--) {
                for (int j = 0; j < number.length; j++) {
                    bs[i - bit].set(j, 1 == (1 & (number[j] >> i)));
                }
                bs[i - bit].set(number.length, 1 == (1 & (cur >> i)));
            }
            int r = getRank(bs, number.length);
            if (r >= 0) {
                ans += 1L << (number.length - r);
            }
        }
        return ans;
    }

    static int getRank(BitSet[] bs, int n) {
        int var = 0;
        int cur = 0;
        for (; cur < bs.length && var < n; var++) {
            if (!bs[cur].get(var)) {
                for (int j = cur + 1; j < bs.length; j++) {
                    if (bs[j].get(var)) {
                        BitSet t = bs[cur];
                        bs[cur] = bs[j];
                        bs[j] = t;
                        break;
                    }
                }
                if (!bs[cur].get(var)) {
                    continue;
                }
            }
            for (int j = cur + 1; j < bs.length; j++) {
                if (bs[j].get(var)) {
                    bs[j].xor(bs[cur]);
                }
            }
            ++cur;
        }
        int ret = cur;
        while (cur < bs.length) {
            if (bs[cur].cardinality() > 0) return -1;
            ++cur;
        }
        return ret;
    }
}
