package coding;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class CircuitsConstruction {
    static String s;
    static int cur;

    static int go() {
        if (s.charAt(cur) == 'A') {
            ++cur;
            return go() + go();
        } else if (s.charAt(cur) == 'B') {
            ++cur;
            return Math.max(go(), go());
        } else {
            ++cur;
            return 1;
        }
    }

    public int maximizeResistance(String circuit, int[] conductors) {
        System.out.println(BigInteger.probablePrime(29, new Random()));
        s = circuit;
        cur = 0;
        int get = go();
        Arrays.sort(conductors);
        int n = conductors.length;
        int ans = 0;
        for (int i = n - get; i < n; i++) {
            ans += conductors[i];
        }
        return ans;
    }
}
