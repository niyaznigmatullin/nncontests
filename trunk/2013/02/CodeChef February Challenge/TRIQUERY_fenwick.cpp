#include <cstdio>
#include <cstdlib>
#include <cassert>
#include <algorithm>

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

const int TN = 333333;
int * fn[TN], * rn[TN], cn[TN];
const int GN = 10000000;
int mem[GN];
int xs[TN], ys[TN], ds[TN];
int bx[TN], mx, curb, fr, n, bxy[TN], ez[TN];
int xq[TN], yq[TN], dq[TN];
int ansq[TN];
char big[GN];

bool cmpx(int a, int b) {
    return xs[a] < xs[b] || xs[a] == xs[b] && ys[a] < ys[b];
}

bool cmpxy(int a, int b) {
    return ds[a] < ds[b];
}

bool cmpq(int a, int b) {
    int x = xq[a] + yq[a] + dq[a];
    int y = xq[b] + yq[b] + dq[b];
    return x < y;
}


void build(int mx) {
    for (int v = 0; v < mx; v++) {
        int j = (v & v + 1);
        int * a = mem + fr;
        int r = j == 0 ? 0 : ez[j - 1];
        while (curb < n && xs[bx[curb]] == v) ++curb;
        ez[v] = curb;
        int cnt = 0;
        for (int z = r; z < curb; z++) {
            a[cnt++] = ys[bx[z]];
        }
        if (cnt == 0) {
            cn[v] = cnt;
            rn[v] = a;
            fn[v] = a;
            continue;
        }
        std::sort(a, a + cnt);
        int k = 1;
        for (int z = 1; z < cnt; z++) {
            if (a[z] != a[k - 1]) a[k++] = a[z];
        }
        cnt = k;
        rn[v] = a;
        fr += cnt;
        fn[v] = mem + fr;
        fr += cnt;
        for (int i = 0; i < cnt; i++) {
            fn[v][i] = 0;
        }
        cn[v] = cnt;
    }
}

void mod(int * f, int sz, int x) {
    for (int i = x; i < sz; i |= i + 1) {
        f[i]++;
    }
}

int gSum(int * f, int x) {
    int ret = 0;
    for (int i = x; i >= 0; i = (i & i + 1) - 1) {
        ret += f[i];
    }
    return ret;
}

int bse(int * a, int sz, int x) {
    int l = -1;
    int r = sz;
    while (l < r - 1) {
        int m = (l + r) >> 1;
        if (a[m] >= x) r = m; else l = m;
    }
    return r;
}

int get(int x, int y) {
//    printf("get %d %d\n", x, y);
    int ret = 0;
    for (int v = x; v >= 0; v = (v & v + 1) - 1) {
        if (cn[v] == 0) continue;
        int z = bse(rn[v], cn[v], y);
        ret += gSum(fn[v], cn[v] - z - 1);
    }
    return ret;
}

void add(int x, int y) {
//    printf("add %d %d\n", x, y);
    for (int v = x; v < mx; v |= v + 1) {
        int cc = cn[v];
        if (cn[v] == 0) continue;
        int * a = rn[v];
        int l = bse(a, cc, y);
        assert(l >= 0 && a[l] == y);
        mod(fn[v], cc, cc - l - 1);
    }
}

int main() {
    n = ni();
    int q = ni();
    mx = 0;
    for (int i = 0; i < n; i++) {
        xs[i] = ni();
        ys[i] = ni();
        ds[i] = xs[i] + ys[i];
        xs[i] = 300000 - xs[i];
        bx[i] = i;
        bxy[i] = i;
        if (mx < xs[i]) mx = xs[i];
    }
    ++mx;
    std::sort(bx, bx + n, cmpx);
    curb = 0;
    fr = 0;
    build(mx);
    std::sort(bxy, bxy + n, cmpxy);
    curb = 0;
    for (int i = 0; i < q; i++) {
        xq[i] = ni();
        yq[i] = ni();
        dq[i] = ni();
        bx[i] = i;
    }
    std::sort(bx, bx + q, cmpq);
    for (int it = 0, j = 0; it < q; it++) {
        int i = bx[it];
        int ss = xq[i] + yq[i] + dq[i];
//        printf("ss = %d\n", ss);
        while (j < n && ds[bxy[j]] <= ss) {
//            printf("%d\n", xs[bxy[j]] + ys[bxy[j]]);
            add(xs[bxy[j]], ys[bxy[j]]);
            ++j;
        }
        ansq[i] = get(300000 - xq[i], yq[i]);
    }
    char num[20];
    for (int i = 0; i < q; i++) {
        int x = ansq[i];
        int cc = 0;
        if (x == 0) num[cc++] = '0';
        while (x > 0) {
            int z = x / 10;
            num[cc++] = x - z * 10 + '0';
            x = z;
        }
        while (cc > 0) putchar(num[--cc]);
        putchar('\n');
    }
}