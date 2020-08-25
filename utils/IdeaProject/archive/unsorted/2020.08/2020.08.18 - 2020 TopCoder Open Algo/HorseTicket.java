package coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorseTicket {

    static int code(char c) {
        if (Character.isDigit(c)) {
            return c - '0';
        }
        if (Character.isUpperCase(c)) {
            return c - 'A' + 10;
        }
        if (Character.isLowerCase(c)) {
            return c - 'a' + 26 + 10;
        }
        throw new AssertionError();
    }

    static char decode(int code) {
        if (code < 10) return (char) ('0' + code);
        code -= 10;
        if (code < 26) return (char) ('A' + code);
        code -= 26;
        if (code < 26) return (char) ('a' + code);
        throw new AssertionError();
    }

    static long count(boolean[] usedGroups, String[] races) {
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < races.length; i++) {
            if (!usedGroups[i]) {
                counts.add(races[i].length());
            }
        }
        return getCount(counts);
    }

    static final long INF = 1L << 60;

    static long add(long a, long b) {
        if (a == INF || b == INF) return INF;
        a += b;
        if (a >= INF) return INF;
        return a;
    }

    static long mul(long a, long b) {
        if (a == 0) return 0;
        if (INF / a < b) return INF;
        a *= b;
        if (a >= INF) return INF;
        return a;
    }

    static long[] fact = new long[100];
    static {
        fact[0] = 1;
        for (int i = 1; i < fact.length; i++) {
            fact[i] = mul(fact[i - 1], i);
        }
    }

    static long getCount(List<Integer> counts) {
        long[] dp = new long[1];
        dp[0] = 1;
        for (int i : counts) {
            long[] ndp = new long[dp.length + 1];
            for (int j = 0; j < dp.length; j++) {
                ndp[j] = add(ndp[j], dp[j]);
                ndp[j + 1] = add(ndp[j + 1], mul(dp[j], i));
            }
            dp = ndp;
        }
        long ans = 0;
        for (int i = 0; i < dp.length; i++) {
            ans = add(ans, mul(fact[i], dp[i]));
        }
        return ans;
    }

    public String getTicket(String[] races, long index) {
        index++;
        int[] group = new int[62];
        Arrays.fill(group, -1);
        for (int i = 0; i < races.length; i++) {
            String e = races[i];
            for (char c : e.toCharArray()) {
                group[code(c)] = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        boolean[] usedGroups = new boolean[races.length];
        if (count(usedGroups, races) < index) {
            return "!";
        }
        while (index > 1) {
            index--;
            boolean found = false;
            for (int i = 0; i < 62; i++) {
                if (group[i] < 0 || usedGroups[group[i]]) {
                    continue;
                }
                ans.add(i);
                usedGroups[group[i]] = true;
                long got = count(usedGroups, races);
                if (got < index) {
                    index -= got;
                    ans.remove(ans.size() - 1);
                    usedGroups[group[i]] = false;
                } else {
                    found = true;
                    break;
                }
            }
            if (!found) throw new AssertionError();
        }
        StringBuilder answer = new StringBuilder();
        for (int i : ans) answer.append(decode(i));
        return answer.toString();
    }
}
