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

const int INF = ~(1 << 31);
const int GN = 222222;
const int TN = 40555555;

int ss[GN], ff[GN], tlast[GN], ne[GN], pv[GN], sz[GN], tsz[GN], rtsg[GN], tid[GN], de[GN], lca[18][GN];
int left[TN], right[TN], fr, ver, chng, zm[GN], troot[GN], n;
long long sum[TN];
long long add1[TN];
int add2[TN];

long long sumV(int v, int l, int r) {
    return sum[v] + (long long) add1[v] * (r - l) + add2[v] * ((long long) (r - l) * (r - l - 1) >> 1);
}

int build(int l, int r) {
    int z = fr++;
    if (l == r - 1) {
        left[z] = right[z] = -1;
        sum[z] = add1[z] = add2[z] = 0;
        return z;
    }
    int mid = (l + r) >> 1;
    left[z] = build(l, mid);
    right[z] = build(mid, r);
    add1[z] = add2[z] = sum[z] = 0;
    return z;
}

int doSet(int v, int ll, int rr, int x, int y) {
    if (ll == rr - 1) {
        int z = fr++;
        left[z] = right[z] = -1;
        add2[z] = y;
        return z;
    }
    int mid = (ll + rr) >> 1;
    int z = fr++;
    left[z] = left[v];
    right[z] = right[v];
    if (x < mid) {
        left[z] = doSet(left[v], ll, mid, x, y);
    } else {
        right[z] = doSet(right[v], mid, rr, x, y);
    }
    return z;
}

int doGet(int v, int l, int r, int x) {
    while (l < r - 1) {
        int mid = (l + r) >> 1;
        if (x < mid) {
            r = mid;
            v = left[v];
        } else {
            l = mid;
            v = right[v];
        }
    }
    return add2[v];
}

void relax(int v, int ll, int rr) {
    if (add1[v] == 0 && add2[v] == 0) return;
    int mid = (ll + rr) >> 1;
    int szLeft = mid - ll;
    if (szLeft > 0) {
        int z = fr++;
        int lc = left[v];
        left[z] = left[lc];
        right[z] = right[lc];
        sum[z] = sum[lc];
        add1[z] = add1[lc] + add1[v];
        add2[z] = add2[lc] + add2[v];
        left[v] = z;
    }
    if (rr - mid > 0) {
        int z = fr++;
        int lc = right[v];
        left[z] = left[lc];
        right[z] = right[lc];
        sum[z] = sum[lc];
        add1[z] = add1[lc] + add1[v] + (long long) szLeft * add2[v];
        add2[z] = add2[lc] + add2[v];
        right[v] = z;
    }
    sum[v] = sumV(v, ll, rr);
    add1[v] = 0;
    add2[v] = 0;
}

int doAdd(int v, int ll, int rr, int l, int r, int a, int b) {
    if (rr <= l || r <= ll) return v;
    if (l <= ll && rr <= r) {
        int z = fr++;
        left[z] = left[v];
        right[z] = right[v];
        sum[z] = sum[v];
        add1[z] = add1[v] + a + (long long) b * (ll - l);
        add2[z] = add2[v] + b;
        return z;
    }
    relax(v, ll, rr);
    int mid = (ll + rr) >> 1;
    int z = fr++;
    left[z] = doAdd(left[v], ll, mid, l, r, a, b);
    right[z] = doAdd(right[v], mid, rr, l, r, a, b);
    sum[z] = sumV(left[z], ll, mid) + sumV(right[z], mid, rr);
    add1[z] = add2[z] = 0;
    return z;
}

long long getSum(int v, int ll, int rr, int l, int r) {
    if (rr <= l || r <= ll) {
        return 0;
    }
    if (l <= ll && rr <= r) {
        return sumV(v, ll, rr);
    }
    relax(v, ll, rr);
    int mid = (ll + rr) >> 1;
    long long ans = getSum(left[v], ll, mid, l, r) + getSum(right[v], mid, rr, l, r);
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

int addPath(int v, int u, int a, int b, int dz) {
    while (true) {
        int z = doGet(dz, 0, n, tid[v]);
        if (tid[v] == tid[u]) {
            int nn = doAdd(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[u], a, b);
            dz = doSet(dz, 0, n, tid[v], nn);
            return dz;
        }
        int nn = doAdd(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[tid[v]], a, b);
        dz = doSet(dz, 0, n, tid[v], nn);
        a += (tsz[tid[v]] - tsz[v] + 1) * b;
        v = pv[tid[v]];
    }
} 

long long ask(int v, int u) {
    long long ret = 0;
    while (true) {
        int z = doGet(zm[ver], 0, n, tid[v]);
        if (tid[v] == tid[u]) {
            return ret + getSum(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[u]);
        }
        ret += getSum(z, 0, tsz[tid[v]], tsz[v] - 1, tsz[tid[v]]);
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
    int m = ni();
    for (int i = 0; i + 1 < n; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
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
    ver = 0;
    chng = 0;
    long long lastans = 0;
    zm[0] = build(0, n);
    for (int i = 0; i < n; i++) {
        if (rtsg[i]) {
            zm[0] = doSet(zm[0], 0, n, i, troot[i]);
        }
    }
    for (int i = 0; i < m; i++) {
        int c = getchar();
        while (c != 'c' && c != 'q' && c != 'l') {
            c = getchar();
        }
        if (c == 'c') {
            ++chng;
            int dz = zm[ver];
            int v = ni();
            int u = ni();
            v = (v + lastans) % n;
            u = (u + lastans) % n;
            int a = ni();
            int b = ni();
            int pa, pb;
            glca(v, u, pa, pb);
            if (pa == pb) {
                if (v == pa) {
                    dz = addPath(u, v, a + b * (de[u] - de[v]), -b, dz);
                } else {
                    dz = addPath(v, u, a, b, dz);
                }
            } else {
                dz = addPath(v, pv[pa], a, b, dz);
                a += (de[v] - de[pv[pa]] + 1) * b;
                dz = addPath(u, pb, a + b * (de[u] - de[pb]), -b, dz);
            }
            ver = chng;
            zm[ver] = dz;
        } else if (c == 'q') {
            int v = ni();
            int u = ni();
            v = (v + lastans) % n;
            u = (u + lastans) % n;
            int pa, pb;
            glca(v, u, pa, pb);
            if (pa == pb) {
                long long ans = pa == v ? ask(u, v) : ask(v, u);
                lastans = ans;
                printf("%lld\n", ans);
            } else {
                long long ans = ask(v, pv[pa]) + ask(u, pb);
                lastans = ans;
                printf("%lld\n", ans);
            }
        } else if (c == 'l') {
            ver = ni();
            ver = (ver + lastans) % (chng + 1);
        }
    }
}