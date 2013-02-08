#include <cstdio>
#include <cassert>
#include <algorithm>
#include <memory.h>

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

int n, curt;
const int NN = 1555555;
int sq[NN];
const int TN = 666666;
const int QN = 16666666;
int ss[TN], ff[TN], he[TN], ne[TN], lab[TN], slab[TN], pv[TN], bc[TN], trt[TN], en[TN], ex[TN], lid[TN], fs[TN], heq[TN], de[TN], sz[TN], bsz[TN];
long long fsd[TN];
int qk[QN], qv[QN], qlen[QN], neq[QN], cntq;

bool cmp(int a, int b) {
    return slab[a] < slab[b];
}

void dfs(int v) {
    en[v] = curt++;
    slab[v] += lab[v];
    sz[v] = 1;
    bsz[v] = 0;
    trt[v] = v;
    bc[v] = -1;
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (ff[e] == pv[v]) continue;
        pv[ff[e]] = v;
        de[ff[e]] = de[v] + 1;
        slab[ff[e]] = slab[v];
        dfs(ff[e]);
        if (sz[ff[e]] > bsz[v]) {
            bsz[v] = sz[ff[e]];
            bc[v] = ff[e];
        }
        sz[v] += sz[ff[e]];
    }
    ex[v] = curt;
}

template<typename T>
void mod(T * a, int x, int y) {
    for (int i = x; i < n; i |= i + 1) {
        a[i] += y;
    }
}

template<typename T>
T sum(T * a, int x) {
    T ret = 0;
    for (int i = x; i >= 0; i = (i & i + 1) - 1) {
        ret += a[i];
    }
    return ret;
}

template<typename T>
T sum(T * a, int l, int r) {
    return sum(a, r - 1) - sum(a, l - 1);
}

void dfs2(int v) {
    if (bc[v] >= 0) {
        trt[bc[v]] = trt[v];
    }
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (ff[e] == pv[v]) continue;
        dfs2(ff[e]);
    }
}

int main() {
    n = ni();
    for (int i = 0; i < n; i++) {
        he[i] = -1;
    }
    for (int i = 0; i < n - 1; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
        ss[i + n - 1] = ff[i];
        ff[i + n - 1] = ss[i];
    }
    for (int i = 0; i < 2 * (n - 1); i++) {
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    for (int i = 0; i < NN; i++) sq[i] = 0;
    for (int i = 1; i * i < NN; i += 2) sq[i * i] = 1;
    for (int i = 0; i < n; i++) {
        int x = ni();
        while ((x & 1) == 0) x >>= 1;
        lab[i] = sq[x] ? -1 : 1;
    }
    curt = 0;
    slab[0] = n;
    de[0] = 0;
    pv[0] = 0;
    dfs(0);
    dfs2(0);
/*    for (int i = 0; i < n; i++) {
        printf("%d ", en[i]);
    }
    puts("");
    for (int i = 0; i < n; i++) {
        printf("%d ", trt[i]);
    }
    puts("");
    for (int i = 0; i < n; i++) {
        printf("%d ", lab[i]);
    }
    puts("");
    for (int i = 0; i < n; i++) {
        printf("%d ", de[i]);
    }
    puts("");
    for (int i = 0; i < n; i++) {
        printf("%d ", pv[i]);
    }
    puts("");*/
    for (int i = 0; i < n; i++) {
        lid[i] = i;
        fs[i] = 0;
        fsd[i] = 0;
    }
    cntq = 0;
    for (int i = 0; i < n; i++) {
        qk[cntq] = slab[i] - lab[i];
        qv[cntq] = i;
        qlen[cntq] = 0;
        ++cntq;
        for (int v = trt[i]; v != 0; v = trt[v]) {
            int u = pv[v];
            int was = 0;
            for (int e = he[u]; e >= 0; e = ne[e]) {
                if (ff[e] == pv[u]) continue;
                if (v == ff[e]) {
                    was = 1;
                    continue;
                }
                if (was || bc[u] == ff[e]) {
                    qk[cntq] = 2 * slab[u] - slab[i] - lab[u];
                    qv[cntq] = ff[e];
                    qlen[cntq] = de[i] - de[u] + 1;
//                    printf("query from %d: %d %d %d\n", i, qk[cntq] - n, qv[cntq], qlen[cntq]);
                    ++cntq;
                }
            }
            v = u;
        }
    }
    for (int i = 0; i <= 2 * n; i++) heq[i] = -1;
    for (int i = 0; i < cntq; i++) {
//        printf("%d %d\n", qk[i] - n, qv[i]);
        if (qk[i] > 2 * n) qk[i] = 2 * n;
        if (qk[i] < 0 || qk[i] > 2 * n) continue;
        neq[i] = heq[qk[i]];
        heq[qk[i]] = i;
    }
    std::sort(lid, lid + n, cmp);
    long long answer = 0;
    for (int i = 0, j = 0; i <= 2 * n; i++) {
        while (j < n && slab[lid[j]] == i) {
            mod(fs, en[lid[j]], 1);
            mod(fsd, en[lid[j]], de[lid[j]]);
            ++j;
        }
        for (int e = heq[i]; e >= 0; e = neq[e]) {
            long long got = sum(fsd, en[qv[e]], ex[qv[e]]);
            int cnt = sum(fs, en[qv[e]], ex[qv[e]]);
            got -= (long long) cnt * (de[qv[e]] - 1);
            got += (long long) cnt * qlen[e];
//            assert(got >= 0);
//            printf("%d %d %d %lld\n", qk[e] - n, qv[e], qlen[e], got);
            answer += got;
        }
    }
    printf("%lld\n", answer);
    return 0;
}
