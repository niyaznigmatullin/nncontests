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

int a[11111111];

const int MOD = 1000000007;

void solve(int m, int n) {
    a[0] = 1;
    for (int i = 1; i < m && i <= n; i++) {
        long long x = (long long) m * a[i - 1] % MOD;
        a[i] = x;
    }
    for (int i = m; i <= n; i++) {
        long long x = (long long) m * a[i - 1] % MOD - a[i - m];
        if (x < 0) x += MOD;
        a[i] = x;
    }
    printf("%d\n", a[n]);
}

int main() {
    int t = ni();
    for (int i = 0; i < t; i++) {
        int m = ni();
        int n = ni();
        solve(m, n);
    }
}
