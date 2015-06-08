#include <bits/stdc++.h>

using namespace std;

int const N = 239;
int const M = 123456;


int a[N], b[N];
int ans[N];
long long dp1[M], dp2[M], ways1[M], ways2[M];
int fm[M];
int cn;

long long const MAX = 1LL << 60;
long long const INF = 1e18;

void add(long long & a, long long b) {
    a += b;
    if (a >= MAX) a = MAX;
}

void solve(int n, int * a, long long * dp, long long * ways) {
    for (int i = 0; i < cn; i++) dp[i] = -INF;
    for (int it = cn - 1; it >= 0; it--) {
        int mask = fm[it];
        int who = 0;
        while (who < n && ((mask >> who) & 1) == 1) ++who;
        if (who == n) {
            dp[it] = 0;
            ways[it] = 1;
            continue;
        }
        for (int with = who + 1; with < n; with++) if (((mask >> with) & 1) == 0) {
            int nit = (int) (lower_bound(fm, fm + cn, mask | (1 << who) | (1 << with)) - fm);
            if (dp[nit] == -INF) continue;
            long long value = dp[nit] + abs(a[who] - a[with]);
            if (value > dp[it]) {
                dp[it] = value;
                ways[it] = ways[nit];
            } else if (value == dp[it]) {
                add(ways[it], ways[nit]);
            }
        }
    }
}

int cc[1234];

int main() {
    int n;
    scanf("%d", &n);
    n /= 2;
    for (int i = 0; i < n; i++) {
        scanf("%d%d", a + i, b + i);
    }
    cn = 0;
    for (int i = 0; i < 1 << n; i++) {
        int ones = __builtin_popcount(i);
        if (ones & 1) continue;
        int pref = 0;
        while (pref < n && ((i >> pref) & 1) == 1) ++pref;
        if (2 * pref < ones) continue;
        fm[cn++] = i;
    }
    int ccc = 0;
    for (int i = 0; i < n; i++) cc[ccc++] = a[i];
    for (int i = 0; i < n; i++) cc[ccc++] = b[i];
    sort(cc, cc + ccc);
    long long ff = 0;
    for (int i = 0; i < n; i++) ff += cc[2 * n - i - 1] - cc[i];
    solve(n, a, dp1, ways1);
    solve(n, b, dp2, ways2);
    int mask1 = 0;
    int mask2 = 0;
    int it1 = 0;
    int it2 = 0;
    long long k;
    scanf("%lld", &k);
    --k;
    if (dp1[0] + dp2[0] != ff) {
        puts("NO");
        return 0;
    }
    if ((double) ways1[0] * (double) ways2[0] > 2e18 || ways1[0] * ways2[0] > k) {
        puts("YES");
    } else {
        puts("OVER");
        return 0;
    }
    for (int i = 0; i < 2 * n; i++) {
        int j = i / 2;
        if ((i & 1) == 0) {
            if (((mask1 >> j) & 1) == 1) {
                continue;
            }
            for (int with = j + 1; with < n; with++) {
                if (((mask1 >> with) & 1) == 1) continue;
                int nit = (int) (lower_bound(fm, fm + cn, mask1 | (1 << j) | (1 << with)) - fm);
                if (dp1[nit] + abs(a[j] - a[with]) != dp1[it1]) continue;
                double zz = (double) ways1[nit] * (double) ways2[it2];
                if (zz > 2e18 || ways1[nit] * ways2[it2] > k) {
                    ans[i] = with * 2;
                    ans[with * 2] = i;
                    mask1 |= (1 << j) | (1 << with);
                    it1 = nit;
                    break;
                } else {
                    k -= ways1[nit] * ways2[it2];
                }
            }
            assert(((mask1 >> j) & 1) == 1); 
        } else {
            if (((mask2 >> j) & 1) == 1) continue;
            for (int with = j + 1; with < n; with++) {
                if (((mask2 >> with) & 1) == 1) continue;
                int nit = (int) (lower_bound(fm, fm + cn, mask2 | (1 << j) | (1 << with)) - fm);
                if (dp2[nit] + abs(b[j] - b[with]) != dp2[it2]) continue;
                double zz = (double) ways1[it1] * (double) ways2[nit];
                if (zz > 2e18 || ways1[it1] * ways2[nit] > k) {
                    ans[i] = with * 2 + 1;
                    ans[with * 2 + 1] = i;
                    it2 = nit;
                    mask2 |= (1 << j) | (1 << with);
                    break;
                } else {
                    k -= ways1[it1] * ways2[nit];
                }
            }
            assert(((mask2 >> j) & 1) == 1);
        }
    }
    for (int i = 0; i < 2 * n; i++) {
        if (i > 0) putchar(' ');
        printf("%d", ans[i] + 1);
    }
    puts("");
}
