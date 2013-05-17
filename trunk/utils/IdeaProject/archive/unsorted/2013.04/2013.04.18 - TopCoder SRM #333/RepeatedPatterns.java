package coding;

import java.math.BigInteger;

public class RepeatedPatterns {

    public long findZeroSegment(String p1, String p2, String zeroCountS) {
        long zeroCount = Long.parseLong(zeroCountS);
        boolean z1 = isZero(p1);
        boolean z2 = isZero(p2);
        long answer = Long.MAX_VALUE;
        if (zeroCount < 70) {
            String z = "";
            for (int i = 0; i < zeroCount; i++) z += "0";
            int pos1 = p1.indexOf(z);
            if (pos1 >= 0) {
                answer = Math.min(answer, pos1);
            }
            int pos2 = p2.indexOf(z);
            if (pos2 >= 0) {
                answer = Math.min(answer, p1.length() * 1000000 + pos2);
            }
        }
        if (z1 && z2) {
            return 0;
        }
        if (z1) {
            if (p1.length() * 1000000 + zerosAtStart(p2) >= zeroCount) return 0;
            if (zerosAtStart(p2) + zerosAtTheEnd(p2) + p1.length() * 1000000 >= zeroCount) {
                return Math.min(answer, 1000000 * p1.length() + p2.length() - zerosAtTheEnd(p2));
            }
            if (answer != Long.MAX_VALUE) return answer;
            return -1;
        }
        int start1 = zerosAtStart(p1);
        int end1 = zerosAtTheEnd(p1);
        int start2 = zerosAtStart(p2);
        int end2 = zerosAtTheEnd(p2);
        if (start1 >= zeroCount) {
            return 0;
        }
        if (end1 + start1 >= zeroCount) {
            return Math.min(answer, p1.length() - end1);
        }
        if (z2) {
            BigInteger cn = BigInteger.valueOf((zeroCount - end1 - start1 + p2.length() - 1) / p2.length());
            BigInteger ans = BigInteger.valueOf(p1.length() * 1000000).multiply(cn).subtract(BigInteger.valueOf(end1));
            ans = ans.add(cn.multiply(cn.subtract(BigInteger.ONE)).shiftRight(1).multiply(BigInteger.valueOf(p2.length())));
            if (ans.add(BigInteger.valueOf(zeroCount)).compareTo(BigInteger.TEN.pow(16)) > 0) return answer == Long.MAX_VALUE ? -1 : answer;
            return Math.min(answer, ans.longValue());
        }
        if (end1 + start2 >= zeroCount) {
            return Math.min(answer, p1.length() * 1000000 - end1);
        }
        if (end2 + start1 >= zeroCount) {
            return Math.min(answer, p1.length() * 1000000 + p2.length() - end2);
        }
        if (end2 + start2 >= zeroCount) {
            return Math.min(answer, p1.length() * 1000000 * 2 + p2.length() + p2.length() - end2);
        }
        if (answer != Long.MAX_VALUE) return answer;
        return -1;
    }

    static int zerosAtStart(String s) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') break;
            ++cnt;
        }
        return cnt;
    }

    static int zerosAtTheEnd(String s) {
        int cnt = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != '0') break;
            ++cnt;
        }
        return cnt;
    }

    static boolean isZero(String s) {
        for (char c : s.toCharArray()) {
            if (c != '0') return false;
        }
        return true;
    }
}
