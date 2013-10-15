package lib.test.on2013_10.on2013_10_05_Single_Round_Match_593.WolfDelaymasterHard;



import java.util.Arrays;

public class WolfDelaymasterHard {
    static final int MOD = 1000000007;

    public int countWords(int N, int wlen, int w0, int wmul, int wadd, int olen, int o0, int omul, int oadd) {
        char[] c = new char[N];
        Arrays.fill(c, '?');
        genIt(wlen, w0, wmul, wadd, c, '0');
        genIt(olen, o0, omul, oadd, c, '1');
        System.out.println(c);
        int[] prev0 = new int[N + 1];
        int[] prev1 = new int[N + 1];
        prev0[0] = -1;
        prev1[0] = -1;
        for (int i = 0; i < N; i++) {
            prev0[i + 1] = prev0[i];
            prev1[i + 1] = prev1[i];
            if (c[i] == '1') {
                prev1[i + 1] = i;
            } else if (c[i] == '0') {
                prev0[i + 1] = i;
            }
        }
        int[] dp0 = new int[N + 1];
        dp0[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp0[i] = dp0[i - 1];
            if ((i & 1) == 1) {
                continue;
            }
            int p0 = prev0[i];
            int p1 = prev1[i];
            int left = 1;
            int right = Integer.MAX_VALUE;
            right = Math.min(right, (i - p1 - 1) >> 1);
            right = Math.min(right, i - p0 - 1);
            if (right < left) continue;
            int add = dp0[i - 2];
            int id = i - (right << 1) - 1;
            if (id >= 0) {
                add -= dp0[id];
                if (add < 0) add += MOD;
            }
            dp0[i] += add;
            if (dp0[i] >= MOD) {
                dp0[i] -= MOD;
            }
            System.out.println(i + " " + add + " " + left + " " + right);
        }
        int ans = dp0[N] - dp0[N - 1];
        if (ans < 0) ans += MOD;
        return ans;
    }

    private void genIt(int wlen, int w0, int wmul, int wadd, char[] c, char d) {
        int[] w = new int[wlen];
        int z = w0;
        int n = c.length;
        for (int i = 0; i < wlen; i++) {
            c[z] = d;
            z = (int) (((long) z * wmul + wadd) % n);
        }
    }
}
