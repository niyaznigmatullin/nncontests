package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;

public class TaskM {

    static byte[] dig;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int seed = in.nextInt();
        dig = new byte[n];
        int lastR = seed;
        for (int i = 1; i < n; i++) {
            lastR = (int) ((1103515245L * lastR + 12345) & 0x7fffffff);
            dig[i] = (byte) ((lastR >> 16) % 9 + 1);
        }
        int leaves = Integer.highestOneBit(n);
        long[] dpDown = new long[leaves];
        long[] dpUp = new long[leaves];
        int height = Integer.numberOfTrailingZeros(leaves);
        int number = n - leaves + 1;
        long[] powTen = new long[18];
        BigInteger[] bowTen = new BigInteger[100];
        powTen[0] = 1;
        bowTen[0] = BigInteger.ONE;
        for (int i = 1; i < powTen.length; i++) {
            powTen[i] = powTen[i - 1] * 10;
        }
        for (int i = 1; i < bowTen.length; i++) {
            bowTen[i] = bowTen[i - 1].multiply(BigInteger.TEN);
        }
        long ans = 0;
        BigInteger[] bpDown = null;
        BigInteger[] bpUp = null;
        BigInteger bans = null;
        for (int ch = height; ch > 0; ch--) {
            if (height - ch > 5) {
                if (bpUp == null) {
                    bpUp = new BigInteger[leaves];
                    bpDown = new BigInteger[leaves];
                    for (int i = 0; i < leaves; i++) {
                        bpUp[i] = BigInteger.valueOf(dpUp[i]);
                        bpDown[i] = BigInteger.valueOf(dpDown[i]);
                    }
                    dpUp = null;
                    dpDown = null;
                    bans = BigInteger.valueOf(ans);
                    System.gc();
                }
                int nleaves = leaves / 2;
                for (int i = 0; i < nleaves; i++) {
                    int lId = i * 2;
                    int rId = lId + 1;
                    BigInteger left = BigInteger.valueOf(getDigit(lId + leaves - 1));
                    BigInteger right = BigInteger.valueOf(getDigit(rId + leaves - 1));
                    int lenLeft = ((lId << height - ch) >= number ? -1 : 0) + (height - ch);
                    int lenRight = ((rId << height - ch) >= number ? -1 : 0) + (height - ch);
                    BigInteger leftUp = bpUp[lId].multiply(BigInteger.TEN).add(left);
                    BigInteger leftDown = bpDown[lId].add(bowTen[lenLeft].multiply(left));
                    BigInteger rightUp = bpUp[rId].multiply(BigInteger.TEN).add(right);
                    BigInteger rightDown = bpDown[rId].add(bowTen[lenRight].multiply(right));
                    lenLeft++;
                    lenRight++;
                    bans = bans.add(leftUp.multiply(bowTen[lenRight]).add(rightDown).max(rightUp.multiply(bowTen[lenLeft]).add(leftDown)));
                    bpDown[i] = leftDown.max(rightDown);
                    bpUp[i] = leftUp.max(rightUp);
                }
                leaves = nleaves;
            } else {
                int nleaves = leaves / 2;
                for (int i = 0; i < nleaves; i++) {
                    int lId = i * 2;
                    int left = getDigit(lId + leaves - 1);
                    int rId = lId + 1;
                    int right = getDigit(rId + leaves - 1);
                    int lenLeft = ((lId << height - ch) >= number ? -1 : 0) + (height - ch);
                    int lenRight = ((rId << height - ch) >= number ? -1 : 0) + (height - ch);
                    long leftUp = dpUp[lId] * 10 + left;
                    long leftDown = lenLeft < 0 ? 0 : dpDown[lId] + powTen[lenLeft] * left;
                    long rightUp = dpUp[rId] * 10 + right;
                    long rightDown = lenRight < 0 ? 0 : dpDown[rId] + powTen[lenRight] * right;
                    lenLeft++;
                    lenRight++;
                    ans += Math.max(leftUp * powTen[lenRight] + rightDown, rightUp * powTen[lenLeft] + leftDown);
                    dpDown[i] = Math.max(leftDown, rightDown);
                    dpUp[i] = Math.max(leftUp, rightUp);
                }
                leaves = nleaves;
            }
        }
        out.println(bans == null ? ans : bans);
    }

    static int getDigit(int v) {
        if (v >= dig.length) return 0;
        return dig[v];
    }
}
