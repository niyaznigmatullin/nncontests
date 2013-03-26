#include <cstdio>

int gcd(int a, int b) {
    return !b ? a : gcd(b, a % b);
}

int main() {
    for (int n = 1; n <= 30; n++) {
        long long s = 0;
        for (int i = 0; i < n; i++) {
            int g = gcd(i, n);
            long long z = 1;
            for (int j = 0; j < g; j++) z *= 2;
            s += z;            
        }
        printf("%I64d\n", s / n);
    }
}