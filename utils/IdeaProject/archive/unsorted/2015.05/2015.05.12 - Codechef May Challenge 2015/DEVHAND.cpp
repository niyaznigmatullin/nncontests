#include <bits/stdc++.h>

int const MOD = 1000000007;

int mul(int a, int b) {
    return (int) ((long long) a * b % MOD);
}

int modPow(int a, int b) {
    int ret = 1;
    while (b > 0) {
        if (b & 1) ret = mul(ret, a);
        a = mul(a, a);
        b >>= 1;
    }
    return ret;
}

void solve() {
    int n, k;
    scanf("%d%d", &n, &k);
    int all = modPow(k, n);
    int ans = mul(mul(all, (all + MOD - 1) % MOD), (all + MOD - 2) % MOD);
    ans = mul(mul(mul(ans, n), n), n);
    printf("%d\n", ans);
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
