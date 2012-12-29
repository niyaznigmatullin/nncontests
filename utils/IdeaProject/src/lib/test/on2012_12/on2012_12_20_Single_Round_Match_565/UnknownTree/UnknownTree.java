package lib.test.on2012_12.on2012_12_20_Single_Round_Match_565.UnknownTree;



import java.util.Arrays;

public class UnknownTree {

    final int MOD = 1000000009;

	public int getCount(int[] da, int[] db, int[] dc) {
        return (solve1(da, db, dc) + solve2(da, db, dc)) % MOD;
	}

    private int solve2(int[] da, int[] db, int[] dc) {
        return 0;  //To change body of created methods use File | Settings | File Templates.
    }

    int solve1(int[] da, int[] db, int[] dc) {
        int n = da.length;
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (da[i] > db[i]) {
                ++cnt2;
            } else if (da[i] < db[i]) {
                ++cnt1;
            } else {
                return 0;
            }
        }
        int[] set1 = new int[cnt1];
        int[] set2 = new int[cnt2];
        cnt1 = cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (da[i] > db[i]) {
                set2[cnt2] = i;
                ++cnt2;
            } else if (da[i] < db[i]) {
                set1[cnt1] = i;
                ++cnt1;
            } else {
                return 0;
            }
        }
        int dif = -1;
        for (int i = 0; i < n; i++) {
            int cur = da[i] > db[i] ? da[i] - db[i] : db[i] - da[i];
            if (dif >= 0) {
                if (dif != cur) {
                    return 0;
                }
            }
            dif = cur;
        }
        int d1 = -1;
        for (int i : set1) {
            int cur = dc[i] - da[i];
            if (cur <= 0 || d1 >= 0 && cur != d1) {
                d1 = -2;
                break;
            }
            d1 = cur;
        }
        int d2 = -1;
        for (int i : set2) {
            int cur = dc[i] - db[i];
            if (cur <= 0 || d2 >= 0 && cur != d2) {
                d2 = -2;
                break;
            }
            d2 = cur;
        }
        int[] dep1 = getSetDepths(da, set1);
        int[] dep2 = getSetDepths(db, set2);
        if (d1 > 0 && d2 > 0 && d1 + d2 == dif) {
            return (int) ((long) getTree(dep1) * getTree(dep2) % MOD);
        }
        if (d1 == -1 || d2 == -1) {
            if (d1 != -1) {
                {int t = d1; d1 = d2; d2 = t;}
                {int[] t = dep1; dep1 = dep2; dep2 = t;}
                {int[] t = set1; set1 = set2; set2 = t;}
            }
            long ans = 0;
            if (d2 > 0 && d2 < dif) {
                ans += 2 * getTree(dep2);
            }
            for (int parent : set2) {

            }
        }
        if (d1 == -1 && d2 > 0 && d2 < dif) {
            return getTree(dep2);
        }
        if (d2 == -1 && d1 > 0 && d1 < dif) {
            return getTree(dep1);
        }
        if (d1 == -2 && d2 == -2) {
            return 0;
        }
        long ret = 0;
        if (d1 > dif) {

        }
        return 0;
    }

    private int[] getSetDepths(int[] da, int[] set1) {
        int cnt1 = set1.length;
        int[] dep1 = new int[cnt1];
        for (int i = 0; i < cnt1; i++) {
            dep1[i] = da[set1[i]];
        }
        return dep1;
    }

    int getTree(int[] d) {
        d = d.clone();
        Arrays.sort(d);
        long ret = 1;
        for (int i = 0; i < d.length; ) {
            int j = i;
            while (j < d.length && d[i] == d[j]) {
                ++j;
            }
            int mul = i + 1;
            while (i < j) {
                ret = ret * mul % MOD;
                ++i;
            }
        }
        return (int) ret;
    }

}
