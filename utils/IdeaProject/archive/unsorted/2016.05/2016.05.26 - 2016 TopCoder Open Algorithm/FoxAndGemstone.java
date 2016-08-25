package coding;

import java.util.Arrays;

public class FoxAndGemstone {

    static class Bag {
        int[] val;

        public Bag(String s) {
            val = new int[16];
            for (int i = 0; i < s.length(); i++) {
                val[s.charAt(i) - 'A']++;
            }
        }

        boolean beats(Bag b) {
            for (int i = 0; i < 16; i++) {
                if (val[i] < b.val[i]) return false;
            }
            return true;
        }

        boolean beats(Bag b, int heaviest, int mask) {
            if (val[heaviest] == 0) return false;
            for (int i = 0; i < 16; i++) {
                if (((mask >> i) & 1) == 1) continue;
                val[heaviest]--;
                b.val[i]--;
                boolean res = Arrays.equals(val, b.val);
                val[heaviest]++;
                b.val[i]++;
                if (res) {
                    return true;
                }
            }
            return false;
        }
    }

    public String isPossible(String[] bagstr) {
        int n = bagstr.length;
        Bag[] bags = new Bag[n];
        for (int i = 0; i < n; i++) {
            bags[i] = new Bag(bagstr[i]);
        }
        long first = (1L << n) - 1;
        for (int i = 0; i < n; i++) {
            if (((first >> i) & 1) == 0) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (bags[i].beats(bags[j])) {
                    first &= ~(1L << j);
                }
            }
        }
        long[] dp = new long[1 << 16];
        Arrays.fill();
    }
}
