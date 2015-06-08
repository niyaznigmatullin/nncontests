#include <bits/stdc++.h>

int const MOD = 1000000007;

void add(int & a, int b) {
    a += b;
    if (a >= MOD) a -= MOD;
}

int mul(int a, int b) {
    return (int) ((long long) a * b % MOD);
}

int const N = 123456;

int a[N], two[N];

void solve() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) scanf("%d", a + i);
    std::sort(a, a + n);
    int ans = 0;
    for (int i = 0; i < n; i++) add(ans, MOD - mul(two[n - i - 1], a[i]));
    for (int i = n - 1; i >= 0; i--) add(ans, mul(two[i], a[i]));
    printf("%d\n", ans);
}

int main() {
    two[0] = 1;
    for (int i = 1; i < N; i++) {
        add(two[i], two[i - 1]);
        add(two[i], two[i - 1]);
    }
    int tt;
    scanf("%d", &tt);
    while (tt--) solve();
}
