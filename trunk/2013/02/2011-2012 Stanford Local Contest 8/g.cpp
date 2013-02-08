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

int w[11111], v[11111], u[11111], d[1111];
const int INF = ~(1 << 31);

void solve(int n, int m, int q) {
    for (int i = 0; i < q; i++) {
        int a = ni() - 1;
        int b = ni() - 1 + n;
        int c = getchar();
        while (c != '<' && c != '>') c = getchar();
        int z = ni();
        if (c == '<') {
            v[i] = b;
            u[i] = a;
            w[i] = z;
        } else {
            v[i] = a;
            u[i] = b;
            w[i] = -z;
        }
    }
    n += m;
    for (int i = 0; i < n; i++) d[i] = 0;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < q; j++) {
            if (d[v[j]] == INF) continue;
            if (d[v[j]] + w[j] < d[u[j]]) d[u[j]] = d[v[j]] + w[j];
        }
    }
    int ok = 1;
    for (int i = 0; i < q; i++) {
        if (d[v[i]] == INF) continue;
//        printf("%d %d %d\n", v[i], u[i], w[i]);
        if (d[v[i]] + w[i] < d[u[i]]) ok = 0;
    }
    puts(ok ? "Possible" : "Impossible");
}

int main() {
    while (true) {
        int n = ni();
        int m = ni();
        int q = ni();
        if ((n | m | q) == 0) break;
        solve(n, m, q);
    }
}