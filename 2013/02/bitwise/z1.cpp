#include <cstdio>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

const int MOD = 1000000007;
long long dp[1234][1234];

void add(long long & a, long long b) {
     a += b;
     if (a >= MOD) a -= MOD;
}

void solve(int m, int n) {
    for (int i = 0; i <= n; i++) for (int j = 0; j < m; j++) dp[i][j] = 0;
    dp[0][0] = 1;
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            int val = dp[i][j];
            if (val == 0) continue;
            for (int k = 0; k < m; k++) {
                if (j == k) add(dp[i + 1][k + 1], val); else
                if (k == 0) add(dp[i + 1][1], val); else
                add(dp[i + 1][0], val);
            }
        }
    }
    long long ans = 0;
    for (int i = 0; i < m; i++) add(ans, dp[n][i]);
    printf("%d\n", (int) ans);
}

int main() {
    int t = ni();
    for (int i = 0; i < t; i++) {
        int m = ni();
        int n = ni();
        solve(m, n);
    }
}
