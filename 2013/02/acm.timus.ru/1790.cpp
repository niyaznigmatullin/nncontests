#include <cstdio>

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


unsigned long long s[111111 >> 6], s1[111111 >> 6], s2[111111 >> 6];

int main() {
    int n = ni();
    int m = ni();
    int all = (n + 63) >> 6;
    for (int i = 0; i < all; i++) s[i] = 0;
    for (int i = 0; i < n; i++) s[i >> 6] |= 1ULL << i;
    for (int i = 0; i < m; i++) {
        int x = ni() - 1;
        s[x >> 6] &= ~(1ULL << x);
        int found = 0;
        for (int j = 0; j < all; j++) if (s[j] != 0) {
            found = 1;
            break;
        }
        if (!found) {
            puts("YES");
            return 0;
        }
        unsigned long long * ds = s, *ns = s + all;
        unsigned long long * ds1 = s1;
        unsigned long long * ds2 = s2;        
        *ds2 = 0;
        while (ds != ns) {
            *ds1 = (*ds >> 1);            
            *ds2 |= (*ds << 1);
            ++ds2;         
            *ds2 = *ds >> 63;
            ++ds;
            *ds1 |= ((*ds & 1ULL) << 63);
            ++ds1;
        }
        s2[n >> 6] &= ~(1ULL << n);
        ds = s;
        ds1 = s1;
        ds2 = s2;
        while (ds != ns) {
            *ds = *ds1 | *ds2;
            ++ds;
            ++ds1;
            ++ds2;
        }
    }
    puts("NO");
}