#include <bits/stdc++.h>

int const MOD = 1000000007;

int dp[2][555][555];

void add(int & a, int b) {
    a += b;
    if (a >= MOD) a -= MOD;
}

int main() {
    int n, k;
    scanf("%d%d", &n, &k);
    if (n <= 2) {
        puts("1");
        return 0;
    }
    dp[2 & 1][1][0] = 1;
    for (int i = 3; i <= n; i++) {
        for (int x = 0; x <= i; x++)
            for (int y = 0; y <= i; y++) dp[i & 1][x][y] = 0;
        for (int last = 0; last + 1 < i; last++) {
            for (int j = 0; j + 1 < i; j++) {
                add(dp[i & 1][j + 1][last], dp[(i & 1) ^ 1][j][last]);
                add(dp[i & 1][1][last], dp[(i & 1) ^ 1][j][(i - 1) - last - 1]);
                add(dp[i & 1][(i - 1 - j) + 1][last], MOD - dp[(i & 1) ^ 1][j][(i - 1) - last - 1]);
            }
            for (int j = 1; j < i; j++) add(dp[i & 1][j][last], dp[i & 1][j - 1][last]);
        }
    }
    int ans = 0;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < n - i; j++) {
            add(ans, dp[n & 1][i][k - 1]);
            add(ans, dp[n & 1][i][n - k]);
        }
    }
    printf("%d\n", ans);
}
