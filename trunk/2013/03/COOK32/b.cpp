#include <cstdio>
#include <algorithm>

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

void solve() {
    int n = ni();
    int c = ni() - 1;
    int q = ni();
    for (int i = 0; i < q; i++) {
        int l = ni() - 1;
        int r = ni() - 1;
        if (l <= c && c <= r) {
            c = l + r - c;
        }
    }
    printf("%d\n", c + 1);
}

int main() {
    int t = ni();
    for (int i = 0; i < t; i++) {
        solve();
    }
}