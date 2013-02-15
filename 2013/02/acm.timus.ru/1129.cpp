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

int cur[111], c[111][111], a[111][111], b[111][111], path[111 * 111], cnt, n;

void go(int v) {
    while (cur[v] < n) {
        int u = cur[v]++;
        if (b[v][u] > 0) {
            b[u][v]--;
            b[v][u]--;
            go(u);
        }
    }
    path[cnt++] = v;
}

int main() {
    n = ni();
    int odd = -1;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) a[i][j] = b[i][j] = c[i][j] = 0;
    for (int i = 0; i < n; i++) {
        int m = ni();
        for (int j = 0; j < m; j++) {
            int x = ni() - 1;
            a[i][x]++;
            b[i][x]++;
        }
        if (m & 1) {
            if (odd < 0) odd = i; else {
                b[i][odd]++;
                b[odd][i]++;
                odd = -1;
            }
        }
        cur[i] = 0;
    }
    for (int i = 0; i < n; i++) {
        cnt = 0;
        go(i);
        for (int j = 1; j < cnt; j++) {
            c[path[j - 1]][path[j]] = 1;
            c[path[j]][path[j - 1]] = -1;
        }
    }
    for (int i = 0; i < n; i++) {
        int first = 1;
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 0) continue;
            if (first) first = 0; else putchar(' ');
            putchar("GY"[c[i][j] == 1]);
        }
        puts("");
    }
}