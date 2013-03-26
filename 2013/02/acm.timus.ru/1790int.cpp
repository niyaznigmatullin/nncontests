#include <cstdio>
#include <memory.h>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    int sg = c == '-';
    if (sg) c = getchar();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}


unsigned int s[111111 >> 5], s1[111111 >> 5], s2[111111 >> 5];

int main() {
    int n = ni();
    int m = ni();
    int all = (n + 31) >> 5;
    for (int i = 0; i < all; i++) s[i] = 0;
    for (int i = 0; i < n; i++) s[i >> 5] |= 1 << i;
    unsigned int * a = s;
    unsigned int * b = s1;
    for (int i = 0; i < m; i++) {
        int x = ni() - 1;
        a[x >> 5] &= ~(1 << x);
        int found = 0;
        for (int j = 0; j < all; j++) if (a[j] != 0) {
            found = 1;
            break;
        }
        if (!found) {
            puts("YES");
            return 0;
        }
        unsigned int * ds = a, *ns = a + all;
        unsigned int * ds2 = b;
        memset(ds2, 0, all << 2);
        unsigned int last = 0;
        while (ds != ns) {
            unsigned int z = *ds;
            unsigned int f = (z >> 1) | (z << 1) | last;
            last = z >> 31;
            ++ds;
            f |= (*ds & 1) << 31;
            *ds2 = f;
            ++ds2;
        }
        b[n >> 5] &= ~(1 << n);
        unsigned int * t = a;
        a = b;
        b = t;
    }
    puts("NO");
}