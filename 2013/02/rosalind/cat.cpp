#include <cstdio>
#include <cassert>

const int MOD = 1000000;
int dp[1333][1333];
char s[1333];
char ma[1333];

bool ip(char c, char d) {
    for (int i = 0; i < 4; i++) if ("AUGC"[i] == c && "UACG"[i] == d) return 1;
    return 0;
}

int main() {
    scanf("%s", s);
    int n = 0;
    while (s[n]) ++n;
    for (int i = 0; i < n; i++) assert(s[i] == 'A' || s[i] == 'G' || s[i] == 'C' || s[i] == 'U');
    for (int i = 0; i <= n; i++) dp[i][i] = 1;
    for (int i = 0; i + 1 < n; i++) {
        dp[i][i + 2] = ip(s[i], s[i + 1]);
    }
    for (int len = 4; len <= n; len += 2) {
        for (int i = 0; i + len <= n; i++) {
            int j = i + len;
            int ans = 0;
            for (int k = i + 1; k <= j; k++) {
                if (ip(s[i], s[k])) {
                    ans += 1LL * dp[i + 1][k] * dp[k + 1][j] % MOD;
                    ans %= MOD;
                }
            }
            dp[i][j] = ans;
        }
    }
    printf("%d\n", dp[0][n]);
}