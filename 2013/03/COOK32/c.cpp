#include <cstdio>
#include <algorithm>
#include <memory.h>
#include <set>

using std::set;
using std::pair;
using std::make_pair;

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

const int INF = 1 << 30;
const int N = 422222;
int ss[N], ff[N], st[N], fi[N], deg[N], id[N], d[N], eid[N], tsz[N];
int * ed[N], *t1[N], *t2[N];
int buf[15 * N];

void setmin(int * t, int all, int l, int r, int val) {
    --r;
    l += all;
    r += all;
    while (l <= r) {
        if ((l & 1) == 1) {
            if (t[l] > val) t[l] = val;
            ++l;            
        }
        if ((r & 1) == 0) {
            if (t[r] > val) t[r] = val;
            --r;
        }
        l >>= 1;
        r >>= 1;
    }
}

int getel(int * t, int all, int x) {
    int ret = INF;
    x += all;
    while (x > 0) {
        if (ret > t[x]) ret = t[x];
        x >>= 1;
    }
    return ret;
}

bool bb(int x, int y) {
    return st[x] < st[y];
}

int main() {
    int n = ni();
    int t = ni();
    int m = ni();
    for (int i = 0; i < m; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
        deg[ss[i]]++;
        st[i] = ni();
        fi[i] = ni();
    }    
    int * curb = buf;
    for (int i = 0; i < n; i++) {
        ed[i] = curb;
        curb += deg[i] + 1;
        int nn = 1;
        while (nn <= deg[i]) nn <<= 1;
        nn <<= 1;
        t1[i] = curb;
        curb += nn;
        t2[i] = curb;
        curb += nn;
        for (int j = 0; j < nn; j++) t2[i][j] = t1[i][j] = INF;
        tsz[i] = nn >> 1;
        deg[i] = 0;
    }
    for (int i = 0; i < m; i++) {
        ed[ss[i]][deg[ss[i]]] = i;
        deg[ss[i]]++;
        d[i] = INF;
        eid[i] = i;
    }
    for (int i = 0; i < n; i++) {
        std::sort(ed[i], ed[i] + deg[i], bb);
    }
    std::sort(eid, eid + m, bb);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < deg[i]; j++) {
            id[ed[i][j]] = j;
        }
    }
    for (int i = 0; i < deg[0]; i++) d[ed[0][i]] = st[ed[0][i]];
    for (int it = 0; it < m; it++) {
        int e = eid[it];
        int v = ss[e];
        int go = ff[e];
        int z1 = getel(t1[v], tsz[v], id[e]);
        int z2 = getel(t2[v], tsz[v], id[e]) + st[e];
        if (d[e] > z1) d[e] = z1;
        if (d[e] > z2) d[e] = z2;
        if (d[e] == INF) {
            continue;
        }
        int l = -1;
        int r = deg[go];
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (st[ed[go][mid]] < fi[e]) l = mid;
                else r = mid;
        }
        int firs = r;
        l = -1;
        r = deg[go];
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (st[ed[go][mid]] - fi[e] < d[e]) l = mid;
                else r = mid;
        }
        int middle = r;
        setmin(t1[go], tsz[go], firs, middle, d[e]);
        setmin(t2[go], tsz[go], middle, deg[go], -fi[e]);
    }
    int ans = INF;
    for (int i = 0; i < m; i++) {
        if (ff[i] != n - 1) continue;
        if (fi[i] > t) continue;
        if (ans > d[i]) ans = d[i];        
    }
    printf("%d\n", ans == INF ? -1 : ans);
}
