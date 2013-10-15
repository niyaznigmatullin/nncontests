package lib.test.on2013_06.on2013_06_18_Single_Round_Match_583.TravelOnMars;



import java.util.Arrays;

public class TravelOnMars {
    public int minTimes(int[] range, int startCity, int endCity) {
        int n = range.length;
        int[] q = new int[n];
        int[] d = new int[n];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[startCity] = 0;
        int head = 0;
        int tail = 1;
        q[0] = startCity;
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < n; i++) {
                int dist = Math.abs(i - v);
                if (dist > n - dist) dist = n - dist;
                if (dist <= range[v]) {
                    if (d[i] == Integer.MAX_VALUE) {
                        d[i] = d[v] + 1;
                        q[tail++] = i;
                    }
                }
            }
        }
        return d[endCity];
    }
}
