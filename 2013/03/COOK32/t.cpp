#include <cstdio>
#include <algorithm>

int ni() {
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

const int MOD = 1000000007;

int f(int a, int b) {
    long long ret = 0;
    long long q = a;
    while (b > 0) {
        if (b & 1) {
            ret = (ret * q + a) % MOD;
        }
        a = (a * q + a) % MOD;
        q = (q * q) % MOD;
        b >>= 1;
    }
    return ret;
}

int solve(int n) {
    int m = n;
    int ans = f(26, (m + 1) / 2);
    ans += f(26, m / 2);
    return ans % MOD;
}

int main() {
    int t = ni();
    for (int i = 0; i < t; i++) {
        int n = ni();
        printf("%d\n", solve(n));
    }
}