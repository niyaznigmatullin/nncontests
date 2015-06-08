#include <bits/stdc++.h>

using namespace std;

int const N = 123456;

long long gcd(long long a, long long b) {
    return b == 0 ? a : gcd(b, a % b);
}


int np[N], mo[N];
long long f[N];

long long getAll(int n, long long mid, long long den) {
    f[0] = 0;
    for (int i = 1; i <= n; i++) {
        f[i] = i * mid / den + 1 + f[i - 1];
    }
    long long all = 0;
    for (int d = 1; d <= n; d++) {
        all += mo[d] * f[n / d];
    }
//    printf("%d %lld/%lld %lld\n", n, mid, den, all);
//    for (int i = 1; i <= n; i++) printf("f[%d] = %lld\n", i, f[i]);
    return all;
}

void solve() {
    int type, n;
    scanf("%d%d", &type, &n);
    long long den = 1LL << 35;
    if (type == 0) {
        long long x;
        scanf("%lld", &x);
        long long left = -1;
        long long right = den + 1;
        while (left < right - 1) {
            long long mid = (left + right) >> 1;
            if (getAll(n, mid, den) > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right > den) {
            puts("none");
            return;
        }
        long long ansNum = 0;
        long long ansDen = 1;
        for (int i = 1; i <= n; i++) {
            long long q = i * right / den;
            if (ansNum * i < q * ansDen) {
                ansNum = q;
                ansDen = i;
            }
        }
        printf("%lld/%lld\n", ansNum, ansDen);
    } else {
        long long a, b;
        scanf("%lld%lld", &a, &b);
        long long g = gcd(a, b);
        a /= g;
        b /= g;
        if (b > n || a > b) {
            puts("none");
            return;
        }
        printf("%lld\n", getAll(n, a, b) - 1);
    }
}

int main() {
    for (int i = 1; i < N; i++) mo[i] = 1;
    for (int i = 2; i < N; i++) {
        if (!np[i]) {
            for (int j = i; j < N; j += i) {
                np[j] = true;
                mo[j] = -mo[j];
            }
        } 
    }
    for (int i = 2; i * i < N; i++) {
        for (int j = i * i; j < N; j += i * i)
            mo[j] = 0;
    }
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
