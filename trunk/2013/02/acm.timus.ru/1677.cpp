#include <stdio.h>

int ni() {
    int c, sg, ret;
    c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    sg = c == '-';
    if (sg) c = getchar();
    ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

char s[30000];
short p[30000];
unsigned int ans[5355];
int lans;
const unsigned int MOD = 100000000;

void mul(unsigned int * m, int * lmp, unsigned int d) {
    int lm = *lmp;
    unsigned int c = 0;
    unsigned int * mm = m + lm;
    for (; m != mm; ++m) {
        unsigned int z = (*m) * d + c;
        c = z / MOD;
        *m = z - c * MOD;
    }
    while (c > 0) {
        unsigned int f = c / MOD;
        *m = c - f * MOD;
        c = f;
        ++m;
        ++lm;
    }
    *lmp = lm;
}

int main() {
    int n = ni();
    int i;
    int len = 0;
    int c = getchar();
    while (c < 'a' || c > 'z') c = getchar();
    int k = -1;
    p[0] = -1;
    i = 0;
    while (c >= 'a' && c <= 'z') {
        s[i] = c;
        if (i != 0) {        
            while (k > -1 && s[k + 1] != c) k = p[k];
            if (s[k + 1] == c) ++k;
            p[i] = k;
        }
        c = getchar();
        ++i;
    }     
    len = i;
    ans[0] = 0;
    lans = 1;
    for (i = len - 1; i >= 0; ) {
        int j = p[i];
        int z = 0;
        while (z < lans && ans[z] == MOD - 1) {
            ans[z++] = 0;
        }
        if (z < lans)
            ++ans[z];
        else {
            ans[lans++] = 1;
        }
        while (i > j) {
            mul(ans, &lans, n);
            i--;
        }
    }
    printf("%d", ans[lans - 1]);
    for (i = lans - 2; i >= 0; i--) {
        int p;
        int f = ans[i];
        for (p = MOD / 10; p > 1; p /= 10) {
            if (f < p) putchar('0');
        }
        printf("%d", f);
    }
    puts("");
}