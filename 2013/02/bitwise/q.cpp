#include <cstdio>

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

const int INF = ~(1 << 31);
const int MOD = 1007;
const int GN = 222222;
const int TN = 555555;

int ss[GN], ff[GN], tlast[GN], ne[GN], pv[GN], sz[GN], tsz[GN], rtsg[GN], tid[GN], de[GN], lca[18][GN];
int left[TN], right[TN], fr, ver, chng, zm[GN], troot[GN], n;
int sum[TN];
short add[TN][15];

int sumV(int v, int l, int r) {
    int ret = sum[v];
    for (int i = 0; i < 15; i++) {
        int cnt = (r - l - i);
        if (cnt <= 0) continue;
        cnt = (cnt + 14) / 15;
        ret += cnt * add[v][i];
        ret %= MOD;
    }
//    printf("sumV %d %d %d = %d\n", v, l, r, ret);
    return ret % MOD;
}

int build(int l, int r) {
    if (l == r - 1) {
        int z = fr++;
        left[z] = right[z] = -1;
        for (int i = 0; i < 15; i++) add[z][i] = 0;
        sum[z] = 0;
        return z;
    }
    int z = fr++;
    int mid = (l + r) >> 1;
    left[z] = build(l, mid);
    right[z] = build(mid, r);
    sum[z] = 0;
    for (int i = 0; i < 15; i++) add[z][i] = 0;
    return z;
}

void relax(int v, int ll, int rr) {
    int ok = 0;
    for (int i = 0; i < 15; i++) if (add[v][i] != 0) {
        ok = 1;
        break;
    }
    if (!ok) return;
    int mid = (ll + rr) >> 1;
    int szLeft = mid - ll;
    if (szLeft > 0) {
        for (int i = 0; i < 15; i++) {
            add[left[v]][i] += add[v][i];
            add[left[v]][i] %= MOD;
        }
    }
    if (rr - mid > 0) {
        int shift = szLeft % 15;
        for (int i = 0; i < 15; i++) {
            add[right[v]][i] += add[v][(i + shift) % 15];
            add[right[v]][i] %= MOD;
        }
    }
    sum[v] = sumV(v, ll, rr);
    for (int i = 0; i < 15; i++) add[v][i] = 0;
}

void doAdd(int v, int ll, int rr, int l, int r, int * a) {
//    printf("doAdd %d %d %d %d %d : [", v, ll, rr, l, r);
//    for (int i = 0; i < 15; i++) printf("%d, ", a[i]);
//    printf("]\n");
    if (rr <= l || r <= ll) return;
    if (l <= ll && rr <= r) {
        int shift = (ll - l) % 15;
        for (int i = 0; i < 15; i++) {
            add[v][i] += a[(i + shift) % 15];
            add[v][i] %= MOD;
        }
//        puts("Added");
        return;
    }
    relax(v, ll, rr);   
    int mid = (ll + rr) >> 1;
    doAdd(left[v], ll, mid, l, r, a);
    doAdd(right[v], mid, rr, l, r, a);
    sum[v] = (sumV(left[v], ll, mid) + sumV(right[v], mid, rr)) % MOD;
//    printf("%d got sum %d\n", v, sum[v]);
    for (int i = 0; i < 15; i++) add[v][i] = 0;
}

int getSum(int v, int ll, int rr, int l, int r) {
    if (rr <= l || r <= ll) {
        return 0;
    }
    if (l <= ll && rr <= r) {
        int ret = sumV(v, ll, rr);
//        printf("getSum %d %d %d %d %d = %d\n", v, ll, rr, l, r, ret);
        return ret;
    }
    relax(v, ll, rr);
    int mid = (ll + rr) >> 1;
    int ans = (getSum(left[v], ll, mid, l, r) + getSum(right[v], mid, rr, l, r)) % MOD;
    return ans;
}

void dfs(int v, int p, int d) {
    pv[v] = p;
    de[v] = d;
    sz[v] = 1;
    tsz[v] = 1;
    rtsg[v] = 1;
    int best = -1;
    for (int e = tlast[v]; e >= 0; e = ne[e]) {
        if (ff[e] == p) continue;
        dfs(ff[e], v, d + 1);
        sz[v] += sz[ff[e]];
        if (best < 0 || sz[best] < sz[ff[e]]) best = ff[e];
    }
    tsz[v] += tsz[best];
    if (best >= 0) rtsg[best] = 0;
}

void dfs2(int v) {
    if (rtsg[v]) {
        int tree = build(0, tsz[v]);
        tid[v] = v;
        troot[v] = tree;
    }
    for (int e = tlast[v]; e >= 0; e = ne[e]) {
        if (ff[e] == pv[v]) continue;
        tid[ff[e]] = tid[v];
        dfs2(ff[e]);
    }
}

int tmp[20];

void addPath(int v, int u, int * a) {
//    printf("addpath %d %d\n", v, u);
    while (true) {
        int z = troot[tid[v]];
        if (tid[v] == tid[u]) {
            doAdd(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[u], a);
            return;
        }
        int cnt = tsz[tid[v]] - tsz[v] + 1;
//        printf("hzz (%d %d)", v, u);
//        for (int i = 0; i < 15; i++) printf("%d, ", a[i]);
//        puts("");
        doAdd(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[tid[v]], a);
        for (int i = 0; i < 15; i++) tmp[i] = a[i];
        for (int i = 0; i < 15; i++) {
            a[i] = tmp[(i + cnt) % 15];
        }
        v = pv[tid[v]];
    }
} 

int ask(int v, int u) {
//    printf("ask %d %d\n", v, u);
    int ret = 0;
    while (true) {
        int z = troot[tid[v]];
//        printf("do ask %d %d\n", v, u);
        if (tid[v] == tid[u]) {
            ret += getSum(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[u]);
            ret %= MOD;
            return ret;
        }
        ret += getSum(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[tid[v]]);
        ret %= MOD;
        v = pv[tid[v]];
    }
}

void glca(int v, int u, int & pa, int & pb) {
    int changed = 0;
    if (de[v] > de[u]) {
        int t = v;
        v = u;
        u = t;
        changed = 1;
    }
    for (int i = 17; i >= 0; i--) {
        if (de[lca[i][u]] >= de[v]) u = lca[i][u];
    }
    if (u == v) {
        pa = pb = v;
        return;
    }
    for (int i = 17; i >= 0; i--) {
        if (lca[i][u] != lca[i][v]) {
            v = lca[i][v];
            u = lca[i][u];
        }
    }
    if (changed) {
        pa = u;
        pb = v;
    } else {
        pa = v;
        pb = u;
    }
}

int main() {
    fr = 0;
    n = ni();
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
    dfs2(0);
    for (int i = 0; i < n; i++) lca[0][i] = pv[i];
    for (int i = 1; i <= 17; i++) {
        for (int j = 0; j < n; j++) {
            lca[i][j] = lca[i - 1][lca[i - 1][j]];
        }
    }
//    printf("%d\n", fr);
//    return 0;
    int upd[20];
    int upd2[20];
    int m = ni();
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
//                printf("%d ", upd[i]);
                c = getchar();
            }
//            puts("");
            int pa, pb;
            glca(v, u, pa, pb);
            if (pa == pb) {
                if (v == pa) {
                    int k = de[u] - de[v];
                    for (int i = 0; i < 15; i++) {
                        upd2[i] = upd[(k - i + 15) % 15];
                    }
//                    for (int i = 0; i < 15; i++) printf("%d ", upd2[i]);
//                    puts("");
                    addPath(u, v, upd2);
                } else {
                    addPath(v, u, upd);
                }
            } else {
                for (int i = 0; i < 15; i++) upd2[i] = upd[i];
                addPath(v, pv[pa], upd2);
                int k = de[v] - de[pv[pa]] + 1;
                k += de[u] - de[pb];
//                    for (int i = 0; i < 15; i++) printf("%d ", upd[i]);
                for (int i = 0; i < 15; i++) upd2[i] = upd[(k - i + 15) % 15];
//                    printf("%d asdsd\n", k);
//                    for (int i = 0; i < 15; i++) printf("%d ", upd2[i]);
//                    puts("");
                addPath(u, pb, upd2);
            }
        } else if (c == 'Q') {
            int v = ni();
            int u = ni();
            int pa, pb;
            glca(v, u, pa, pb);
            if (pa == pb) {
                int ans = pa == v ? ask(u, v) : ask(v, u);
                printf("%d\n", (int) ans);
            } else {
                int ans = ask(v, pv[pa]) + ask(u, pb);
                ans %= MOD;
                printf("%d\n", (int) ans);
            }
        }
    }
}