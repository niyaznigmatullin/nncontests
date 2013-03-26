#include <cstdio>

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

const int N = 555555;

int lscol[N], lsrow[N], col0[N], col1[N], row0[N], row1[N];

int al(int c) {
    return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
}


void mod(int * a, int x, int y) {
    for (int i = x; i < N; i |= i + 1) {
        a[i] += y;
    }
}

int sum(int * a, int x) {
    int ret = 0;
    for (int i = x; i >= 0; i = (i & i + 1) - 1) {
        ret += a[i];
    }
    return ret;
}

int gSum(int * a, int l, int r) {
    return sum(a, r - 1) - sum(a, l - 1);
}

int main() {
    int n = ni();
    int q = ni();
    for (int i = 0; i < n; i++) lscol[i] = lsrow[i] = 0;
    for (int i = 1; i <= q; i++) {
        int c = getchar();
        while (!al(c)) c = getchar();
        int row = c == 'R';
        int cnt = 0;
        while (al(c)) {
            c = getchar();
            ++cnt;
        }
        int * lst = row ? lsrow : lscol;
        int * s0 = row ? col0 : row0;
        int * s1 = row ? col1 : row1;
        int * t0 = row ? row0 : col0;
        int * t1 = row ? row1 : col1;
        if (cnt > 6) {
            int x = ni() - 1;
            int z = lst[x];
            if (z < 0) {
                printf("%d\n", gSum(s0, (~z) + 1, i));
            } else {
                printf("%d\n", n - gSum(s1, z + 1, i));
            }
        } else {
            int x = ni() - 1;
            int val = ni();
            if (lst[x] < 0) {
                mod(t1, ~lst[x], -1);
            } else {
                mod(t0, lst[x], -1);
            }
            if (val == 0) {
                mod(t0, i, 1);
            } else {
                mod(t1, i, 1);
            }
            lst[x] = val == 0 ? i : ~i;
        }
    }
}