#include <cstdio>
#include <cassert>
#include <set>
#include <cmath>

using namespace std;
long long nl() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    long long ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

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

const long long INF = (1LL << 60);

const int P = 3;
const int MAXN = 100000;
const int MAXP = 100;
long long pows[MAXP][MAXN];

int moe[MAXP], pr[MAXP], have;


long long gp(long long n, int p) {
    if (p == 2) return n * n;
    if (p == 3) {
        if (n > 1 << 20) return INF;
        return n * n * n;
    }
    long long ret = 1;
    while (p > 0) {
        if (p & 1) {
            if (INF / n < ret) return INF;
            ret *= n;
        }
        p >>= 1;
        if (p == 0) break;
        if (INF / n < n) return INF;
        n *= n;
    }
    return ret;
}

long long get1(long long n, int p) {
    if (n <= 3) return 0;
    if (p == 2) {
        long long m = sqrt(1. * n);
        while (m * m < n) ++m;
        while (m * m > n) --m;
        if (m * m == n) have = 1;
        return m;
    }
    long long right = 1LL << 30;
    long long left = 1;
    while (left < right - 1) {
        long long mid = (left + right) >> 1;
        long long z = gp(mid, p);
        if (z == n) have = 1;
        if (z > n) right = mid; else left = mid;   
    }
    return left;
}

long long get2(long long n, int p) {
    if (n <= 3) return 0;
    int l = 0;
    int r = MAXN;
    while (l < r - 1) {
        int m = (l + r) >> 1;
        if (pows[p][m] > n) r = m; else l = m;
    }
    if (pows[p][l] == n) have = 1;
    return l;
}

long long get3(long long n, int p) {
    if (p > 60) return 1;
    if ((1LL << p) > n) return 1;
    return get1(n, p);
}

void solve() {
    have = 0;
    long long n = nl();
    if (n == 0) {
        printf("2");
        return;
    }
    if (n == 1) {
        printf("1");
        return;
    }
    long long m = 0;
    for (int p = 2; ; p++) {
        if (moe[p] == 0) continue;
        long long g;
        if (p <= P) {
            g = get3(n, p);
        } else {
            g = get2(n, p);
        }
        if (g < 2) {
            break;
        }
        m += (g - 1) * moe[p];
    }
    m = -m;
    if (have) {
        printf("%lld", m);
    } else {
        m = n - m + 1;
        long long z = 1;
        int cnt = 0;
        while (z < m) {
            z *= 2;
            ++cnt;
        }
        if (z != m) {
            long long left = z >> 1;
            long long right = z;
            while (1) {
                ++cnt;            
                long long mid = (left + right) >> 1;
                if (mid == m) break;
                if (mid > m) right = mid; else left = mid;
            }
        }
        printf("%d", cnt);
    }
}
void init() {
    for (int i = 0; i < MAXP; i++) pr[i] = 0, moe[i] = 1;
    for (int i = 2; i < MAXP; i++) {
        if (!pr[i]) {
            for (int j = i; j < MAXP; j += i) {
                pr[j] = 1;
                moe[j] = -moe[j];
            }
        }
    }
    for (int i = 2; i * i < MAXP; i++) {
        for (int j = i * i; j < MAXP; j += i * i)
            moe[j] = 0;
    }
    for (int p = 2; p < MAXP; p++) {
        for (int i = 1; i < MAXN; i++) {
            pows[p][i] = gp(i, p);
        }
    }
}

int main() {
    init();
    int n = ni();
    for (int i = 0; i < n; i++) {
        if (i > 0) putchar(' ');
        solve();
    }
    puts("");
}