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

long long row[444444], col[444444];

int main() {
    int n = ni();
    int q = ni();
    for (int i = 0; i < q; i++) {
        int c  = getchar();
        while (c != 'R' && c != 'C') {
            c = getchar();
        }
        int v = ni() - 1;
        int x = ni();
        if (c == 'R') {
            row[v] += x;
        } else {
            col[v] += x;
        }
    }
    long long m1 = 0;
    long long m2 = 0;
    for (int i = 0; i < n; i++) {
        if (m1 < row[i]) m1 = row[i];
        if (m2 < col[i]) m2 = col[i];
    }
    printf("%lld\n", m1 + m2);
    return 0;
}