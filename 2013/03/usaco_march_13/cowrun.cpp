#include <cstdio>
#include <algorithm>

inline int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
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


const int INF = ~(1 << 31);
int a[1111], dp[3][1111][1111];

int ds(int i, int j) {
    int ret = a[i] - a[j];
    if (ret < 0) ret = -ret;
    return ret;   
}

int main() {
    freopen("cowrun.in", "r", stdin);
    freopen("cowrun.out", "w", stdout);
    int n = ni();
    for (int i = 0; i < n; i++) a[i] = ni();
    a[n] = 0;
    ++n;
    std::sort(a, a + n);
    int st = -1;
    for (int i = 0; i < n; i++) if (a[i] == 0) st = i;
    for (int i = 0; i < 2; i++) for (int j = 0; j <= n; j++) for (int k = 0; k <= n; k++) dp[i][j][k] = INF;
    dp[0][0][0] = 0;
    dp[1][0][0] = 0;
    for (int i = 0; i + 1 < n; i++) {
        for (int j = 0; j <= i; j++) {
            for (int k = 0; k < 2; k++) {
                int left = st - j;
                int right = st + i - j;
                int togo = n - 1 - i;
                int cur = dp[k][i][j];
                if (cur == INF) continue;
                int val = cur + togo * ds(left - 1, k == 0 ? left : right);
                if (left > 0 && dp[0][i + 1][j + 1] > val) {
                    dp[0][i + 1][j + 1] = val;
                }
                val = cur + togo * ds(right + 1, k == 0 ? left : right);
                if (right + 1 < n && dp[1][i + 1][j] > val) {
                    dp[1][i + 1][j] = val;
                }
            }
        } 
    }
    int ans = dp[0][n - 1][st];
    if (ans > dp[1][n - 1][st]) ans = dp[1][n - 1][st];
    printf("%d\n", ans);
}