import java.math.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] cnt = new int[n + 1];
        for (int i = 0; i < n; i++) {
            cnt[gcd(i, n)]++;
        }
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i <= n; i++) {
            if (cnt[i] == 0) continue;
            BigInteger g = BigInteger.ZERO;
            g = g.setBit(i);
            g = g.multiply(BigInteger.valueOf(cnt[i]));
            ans = ans.add(g);
        }
        System.out.println(ans.divide(BigInteger.valueOf(n)));
    }
    
    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    
}
