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

int p[555], g[555];

int get(int x) {
    return x == p[x] ? x : (p[x] = get(p[x]));
}

int un(int x, int y) {
    return p[get(x)] = get(y);
}

int main() {
    int n = ni();
    int m = ni();
    for (int i = 0; i < n; i++) p[i] = i;
    int ans = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int x = ni() - 1;
            g[i] |= x != i;
            ans += i != x;
            un(i, x);
        }
    }
    for (int i = 0; i < n; i++) {
        if (g[i] && i == p[i]) ++ans;
    }
    if (ans == 0) ++ans;
    printf("%d\n", ans - 1);
}
