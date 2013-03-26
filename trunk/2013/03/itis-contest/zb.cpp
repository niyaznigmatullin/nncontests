#include <cstdio>

long long gcd(long long a, long long b) {
    return !b ? a : gcd(b, a % b);
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n;
    scanf("%d", &n);
    long long ans = 1;
    for (int i = 0; i < n; i++) {
        long long x;
        scanf("%lld", &x);
        long long g = gcd(ans, x);
        ans = ans / g * x;
    }
    printf("%lld\n", ans);
}