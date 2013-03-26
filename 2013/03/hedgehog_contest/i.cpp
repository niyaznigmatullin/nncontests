#include <cstdio>

char bad[] = {'A', 'B', 'C', 'E', 'H', 'K', 'M', 'O', 'P', 'T', 'X', 'a', 'c', 'e', 'o', 'p', 'x', 'y'};

unsigned long long dp[111][111];
int len[111];
unsigned long long way[111];
char s[111];

int main() {
    gets(s);
    int a = 0, b = 0;
    {
        int i = 0;
        while (s[i] <= 32) ++i;
        while (s[i] > 32) {
            a = a * 10 + s[i] - '0';
            ++i;
        }
        while (s[i] <= 32) ++i;
        while (s[i] > 32) {
            b = b * 10 + s[i] - '0';
            ++i;
        }
    }
    ++a;
    ++b;
    gets(s);
    int n = 0;
    while (s[n]) ++n;
    int wr = 0;
    for (int i = 0; i < n; ) {
        if (s[i] <= 32) {
            ++i;
            continue;
        }
        int j = i;
        while (j < n && s[j] > 32) {
            j++;
        }
        unsigned long long ways = 1;
        for (int k = i; k < j; k++) {
            int ok = 0;
            for (int l = 0; l < 18; l++) {
                if (s[k] == bad[l]) ok = 1;
            }
            if (ok) {
                ways *= 2;
            }
        }
        len[wr] = j - i + 1;
        way[wr] = ways;
        ++wr;
        i = j;
    }
    ++n;
    dp[0][0] = 1;
    for (int i = 1; i <= b; i++) {
        for (int j = 0; j <= wr; j++) {
            dp[i][j] = dp[i - 1][j];
            if (j > 0 && len[j - 1] > i) continue;
            dp[i][j] += dp[i - len[j - 1]][j - 1] * way[j - 1];
        }
    }
    if (n <= b) dp[n][wr]--;
    long long ans = 0;
    for (int i = a; i <= b; i++) ans += dp[i][wr];
    printf("%I64u\n", ans);
}
