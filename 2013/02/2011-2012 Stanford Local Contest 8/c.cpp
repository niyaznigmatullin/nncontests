#include <cstdio>
#include <algorithm>

using namespace std;

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

const int M = 222222;

int ss[M], ff[M], w[M], last[M], ne[M], pv[M], de[M], was[M], onc[M], lca[18][M], wlca[18][M], wp[M], col[M];
int cyc[M], idc[M], cw[M];
int best1 = -1;
int best2 = -1;

void dfs(int v, int p, int d, int c) {
//    fprintf(stderr, "%d %d %d %d\n", v, p, d, c);
    was[v] = 1;
    de[v] = d;
    col[v] = c;
    pv[v] = p;
    for (int e = last[v]; e >= 0; e = ne[e]) {
        if (onc[ff[e]] || was[ff[e]]) continue;
        wp[ff[e]] = w[e];
        dfs(ff[e], v, d + 1, c);
    }
}   

int getwlca(int v, int u) {
    if (de[v] > de[u]) {
        int t = v;
        v = u;
        u = t;
    }
    int ans = 0;
    for (int i = 17; i >= 0; i--) {
        if (de[lca[i][u]] >= de[v]) {
            ans += wlca[i][u];
            u = lca[i][u];
        }
    }
    if (u == v) return ans;
    for (int i = 17; i >= 0; i--) {
        if (lca[i][u] != lca[i][v]) {
            ans += wlca[i][u];
            ans += wlca[i][v];
            u = lca[i][u];
            v = lca[i][v];
        }
    }
    ans += wp[u];
    ans += wp[v];
    return ans;
}

void solve(int n) {
    int m = 2 * n;
    for (int i = 0; i < n; i++) {
        ss[i] = ni();
        ff[i] = ni();
        w[i] = ni();
        ss[i + n] = ff[i];
        ff[i + n] = ss[i];
        w[i + n] = w[i];
    }
    for (int i = 0; i < n; i++) last[i] = -1, was[i] = onc[i] = 0;
    for (int i = 0; i < m; i++) {
        ne[i] = last[ss[i]];
        last[ss[i]] = i;
    }
    dfs(0, -1, 0, -1);
    for (int i = 0; i < n; i++) onc[i] = 0;
    int clen = 0;
    int wlen = 0;
    for (int i = 0; i < m; i++) {
        if (de[ss[i]] > de[ff[i]]) continue;
        if (pv[ff[i]] == ss[i]) continue;
        for (int v = ff[i], last = w[i]; ; v = pv[v]) {
            onc[v] = 1;
            cyc[clen] = v;
            cw[clen] = (clen > 0 ? cw[clen - 1] : 0) + last;
            idc[v] = clen++;
            last = wp[v];
            if (v == ss[i]) break;
            wlen += wp[v];
        }
        wlen += w[i];
    }
    for (int i = 0; i < n; i++) was[i] = 0;
    for (int i = 0; i < n; i++) if (onc[i]) {
        wp[i] = 0;
        dfs(i, i, 0, i);
    }
    for (int i = 0; i < n; i++) lca[0][i] = pv[i], wlca[0][i] = wp[i];
    for (int i = 1; i < 18; i++)
        for (int j = 0; j < n; j++) {
            lca[i][j] = lca[i - 1][lca[i - 1][j]];
            wlca[i][j] = wlca[i - 1][j];
            if (lca[i - 1][j] != col[j]) {
                wlca[i][j] += wlca[i - 1][lca[i - 1][j]];
            }
        }
    
    int q = ni();
    for (int t = 0; t < q; t++) {
        int v = ni();
        int u = ni();
        if (col[v] == col[u]) {
            printf("%d\n", getwlca(v, u));
        } else {
            int ans = wlca[17][v] + wlca[17][u];
            v = col[v];
            u = col[u];
            v = idc[v];
            u = idc[u];
            if (v > u) {
                int t = v;
                v = u;
                u = t;
            }
            int ans2 = cw[u] - cw[v];
            if (ans2 > wlen - ans2) ans2 = wlen - ans2;
            printf("%d\n", ans + ans2);
        }
    }
}

int main() {
    while (true) {
        int n = ni();
        if (n == 0) break;
        solve(n);
    }
}