#include <cstdio>
const int GN = 222222;


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

int ss[GN], ff[GN], tlast[GN], ne[GN], pv[GN], de[GN], lca[18][GN], chng, ver, sum[GN];


void dfs(int v, int p, int d) {
    pv[v] = p;
    de[v] = d;
    for (int e = tlast[v]; e >= 0; e = ne[e]) {
        if (ff[e] == p) continue;
        dfs(ff[e], v, d + 1);
    }
}

int glca(int v, int u) {
    if (de[v] > de[u]) {
        int t = v;
        v = u;
        u = t;
    }
    for (int i = 17; i >= 0; i--) {
        if (de[lca[i][u]] >= de[v]) u = lca[i][u];
    }
    if (u == v) return v;
    for (int i = 17; i >= 0; i--) {
        if (lca[i][u] != lca[i][v]) {
            v = lca[i][v];
            u = lca[i][u];
        }
    }
    return pv[v];
}



int main() {
    int n = ni();
    for (int i = 0; i + 1 < n; i++) {
        ss[i] = ni();
        ff[i] = ni();
        ss[i + n - 1] = ff[i];
        ff[i + n - 1] = ss[i];
    }
    for (int i = 0; i < n; i++) {
        tlast[i] = -1;
    }
    for (int i = 0; i < n + n - 2; i++) {
        ne[i] = tlast[ss[i]];
        tlast[ss[i]] = i;
    }
    dfs(0, 0, 0);
    for (int i = 0; i < n; i++) lca[0][i] = pv[i];
    for (int i = 1; i <= 17; i++) {
        for (int j = 0; j < n; j++) {
            lca[i][j] = lca[i - 1][lca[i - 1][j]];
        }
    }
    int m = ni();
    for (int i = 0; i < n; i++) sum[i] = 0;
    int upd[20];
    for (int i = 0; i < m; i++) {
        int c = getchar();
        while (c != 'U' && c != 'Q') {
            c = getchar();
        }
        if (c == 'U') {
            int v = ni();
            int u = ni();
            while (c < 'a' || c > 'z') c = getchar();
            for (int i = 0; i < 15; i++) {
                upd[i] = c - 'a' + 1;
                c = getchar();
            }
            int lc = glca(v, u);
            int s = 0;
            for (int d = v; d != lc; d = pv[d]) {
                sum[d] += upd[s];
                s = (s + 1) % 15;
            }
            sum[lc] += upd[s];
            int cnt = 0;
            for (int d = u; d != lc; d = pv[d]) {
                ++cnt;
            }
            s = (s + cnt) % 15;
            for (int d = u; d != lc; d = pv[d]) {
                sum[d] += upd[s];
                s = (s + 14) % 15;
            }
        } else if (c == 'Q') {
            int v = ni();
            int u = ni();
            int lc = glca(v, u);
            long long ans = 0;
            for (int d = v; d != lc; d = pv[d]) {
                ans += sum[d];
            }
            ans += sum[lc];
            for (int d = u; d != lc; d = pv[d]) {
                ans += sum[d];
            }
            printf("%lld\n", ans % 1007);
        }
        for (int j = 0; j < n; j++) sum[j] %= 1007;
    }

}