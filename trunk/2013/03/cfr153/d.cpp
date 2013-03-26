#include <cstdio>
#include <cassert>


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

long long nl() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
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

long long b[123456], a[123456];
int id[111], id2[111];
long long num[123];
int ans[123456];
int d[77][77], cn;

int solve(long long bx) {
    cn = 0;
    for (int bit = 0; bit <= 60; bit++) {
        if (id[bit] < 0) continue;
        for (int i = 0; i <= 60; i++) {
            d[i][cn] = (a[id[bit]] >> i) & 1;
        }
        id2[cn] = id[bit];
        ++cn;
    }
    for (int i = 0; i <= 60; i++) {
        d[i][cn] = (bx >> i) & 1;
    }
    for (int i = 0; i < cn; i++) {
        if (!d[i][i]) {
            for (int j = i + 1; j <= 60; j++) {
                if (d[j][i]) {
                    for (int k = 0; k <= cn; k++) {
                        int t = d[i][k];
                        d[i][k] = d[j][k];
                        d[j][k] = t;
                    }
                    break;
                }
            }
            assert(d[i][i]);
        }
        for (int j = i + 1; j <= 60; j++) {
            if (!d[j][i]) continue;
            for (int k = 0; k <= cn; k++) d[j][k] ^= d[i][k];
        }
    }
    for (int i = cn - 1; i >= 0; i--) {
        for (int j = i - 1; j >= 0; j--) {
            if (!d[j][i]) continue;
            for (int k = 0; k <= cn; k++) d[j][k] ^= d[i][k];
        }
    }
    return 0;
}

int main() {
    int n = ni();
    for (int i = 0; i < n; i++) {
        a[i] = b[i] = nl();
    }
    long long xo = 0;
    for (int i = 0; i < n; i++) xo ^= b[i];
    for (int bit = 60; bit >= 0; bit--) {
        id[bit] = -1;
        for (int i = 0; i < n; i++) {
            if ((b[i] >> bit) & 1) {
                id[bit] = i;
                num[bit] = b[i];
                for (int j = 0; j < n; j++) {
                    if ((b[j] >> bit) & 1) 
                        b[j] ^= num[bit];
                }
                break;
            }
        }
    }
    for (int i = 0; i < n; i++) ans[i] = 2;
    long long bx = 0;
    long long z = 0;
    for (int bit = 60; bit >= 0; bit--) {
        if (((xo >> bit) & 1) != ((bx >> bit) & 1)) {
            continue;
        }
        if (id[bit] < 0) continue;
        bx ^= num[bit];
        continue;
        for (int i = 0; i <= 60; i++) {
            if (id[i] < 0) continue;
            if (z & num[i]) continue;
            if ((num[i] >> bit) & 1) {
                bx ^= num[i];
                if (((xo >> bit) & 1) == 0) z |= 1LL << bit;
                break;
            }
        }
    }
    if ((xo ^ bx) < bx) bx ^= xo;
    solve(bx);
    for (int i = 0; i < cn; i++) if (d[i][cn]) ans[id2[i]] = 1;
    long long got = 0;
    for (int i = 0; i < n; i++) if (ans[i] == 1) got ^= a[i];
    assert(got == bx);
    for (int i = 0; i < n; i++) {
        if (i > 0) putchar(' ');
        putchar('0' + ans[i]);
    }
    puts("");
}
