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

int ss[44444], ff[44444], he[222], ne[44444], pv[222], de[222], was[222];

void dfs(int v, int p, int d) {
    pv[v] = p;
    de[v] = d;
    was[v] = 1;
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (ff[e] == p || was[ff[e]]) continue;
        dfs(ff[e], v, d + 1);
    }
}


int main() {
    int n = ni();
    int m = ni();
    for (int i = 0; i < m; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
        ss[i + m] = ff[i];
        ff[i + m] = ss[i];
    }
    for (int i = 0; i < n; i++) he[i] = -1;
    for (int i = 0; i < m + m; i++) {
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (was[i]) continue;
        ++cnt;
        dfs(i, 0, 0);
    }
    printf("%d\n", m - n + cnt);
    for (int i = 0; i < m; i++) {
        int v = ff[i];
        int u = ss[i];
        if (de[v] > de[u]) {
            int t = v;
            v = u;
            u = t;
        }
        if (pv[u] == v) continue;
        printf("%d", de[u] - de[v] + 1);
        for (int i = u; i != v; i = pv[i]) printf(" %d", i + 1);
        printf(" %d\n", v + 1);
    }
}