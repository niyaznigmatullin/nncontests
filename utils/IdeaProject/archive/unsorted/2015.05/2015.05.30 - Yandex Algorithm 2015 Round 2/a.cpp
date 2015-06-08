#include <bits/stdc++.h>

using namespace std;

int const MOD = 1000000007;

void add(int & a, int b) {
    a += b;
    if (a >= MOD) a -= MOD;
}

int const N = 1234567;
long long a[N];
int dp[N];

void solve() {
    int n, m;
    scanf("%d%d", &n, &m);
    long long have = 0;
    a[0] = have;
    for (int i = 0; i < n; i++) {
        int x;
        scanf("%d", &x);
        have += x;
        a[i + 1] = have;
    }
    dp[0] = 1;
    for (int i = 1, j = 0; i <= n; i++) {
        while (a[i] - a[j] > m) ++j;
        add(dp[i], dp[i - 1]);
        if (j > 0)
            add(dp[i], MOD - dp[j - 1]);
        add(dp[i], dp[i - 1]);
    }
    int ans = dp[n];
    add(ans, MOD - dp[n - 1]);
    printf("%d\n", ans);
}

int main() {
    int t;
//    scanf("%d", &t);
    t = 1;
    while (t--) solve();
}
