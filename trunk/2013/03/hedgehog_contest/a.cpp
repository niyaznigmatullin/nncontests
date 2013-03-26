#include <cstdio>

int main() {
    long long n;
    int q;
    scanf("%I64d %d", &n, &q);
    int cn = 0;
    while (n > 1) {
        long long m = n / (q + 2);
        long long a = n - m - 1;
        if (a < m) a = m;
        n = a;
        ++cn;
    }
    printf("%d\n", cn + q + 1);
}