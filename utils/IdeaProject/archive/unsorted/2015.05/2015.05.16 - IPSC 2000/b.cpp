#include <bits/stdc++.h>

using namespace std;

struct point {
    long long num1;
    long long den1;
    long long num2;
    long long den2;
    
    bool operator < (point const & p) const {
        if (num1 != p.num1) return num1 < p.num1;
        if (den1 != p.den1) return den1 < p.den1;
        if (num2 != p.num2) return num2 < p.num2;
        return den2 < p.den2;
    }
};

long long gcd(long long a, long long b) {
    return b == 0 ? a : gcd(b, a % b);
}

void make_rational(long long & a, long long & b) {
    long long g = gcd(a, b);
    a /= g;
    b /= g;
    if (a < 0 || (a == 0 && b < 0)) {
        a = -a;
        b = -b;
    }
}

point make_point(long long x, long long y, long long z) {
    long long xz = z;
    long long yz = z;
    make_rational(x, xz);
    make_rational(y, yz);
    return {x, xz, y, yz};
}

int const N = 13245;

long long a[N], b[N], c[N];

int main() {
    int n;
    scanf("%d", &n);
    int ans = 1;
    for (int i = 0; i < n; i++) {
        int x1, y1, x2, y2;
        scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
        a[i] = y2 - y1;
        b[i] = x1 - x2;
        c[i] = -x1 * a[i] - y1 * b[i];
        set<point> zz;
        for (int j = 0; j < i; j++) {
            long long z = a[i] * b[j] - b[i] * a[j];
            if (z == 0) continue;
            long long x = (long long) b[i] * c[j] - (long long) c[i] * b[j];
            long long y = (long long) c[i] * a[j] - (long long) c[j] * a[i];
            zz.insert(make_point(x, y, z));
        }
        ans += (int) zz.size() + 1;
    }
    printf("%d\n", ans);
}
