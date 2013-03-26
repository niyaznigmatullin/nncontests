#include <cstdio>
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

const int TN = 1777777;
int * fn[TN], * rn[TN], cn[TN];
const int GN = 20000000;
int mem[GN];
int xs[TN], ys[TN];
int bx[TN], mx, curb, fr, n, bxy[TN];
int xq[TN], yq[TN], dq[TN];
int ansq[TN];

bool cmpx(int a, int b) {
    return xs[a] < xs[b] || xs[a] == xs[b] && ys[a] < ys[b];
}

bool cmpxy(int a, int b) {
    int x = xs[a] + ys[a];
    int y = xs[b] + ys[b];
    return x < y;
}

bool cmpq(int a, int b) {
    int x = xq[a] + yq[a] + dq[a];
    int y = xq[b] + yq[b] + dq[b];
    return x < y;
}

void build(int v, int l, int r) {
    if (l == r - 1) {
        rn[v] = mem + fr;
        int cnt = 0;
        while (curb < n && xs[bx[curb]] == l) {
            mem[fr++] = ys[bx[curb]];
            ++cnt;
            ++curb;
        }
        fn[v] = mem + fr;
        for (int i = 0; i < cnt; i++) fn[v][i] = 0;
        fr += cnt;
        cn[v] = cnt;
//        printf("%d %d %d %d\n", v, l, r, cnt);
        return;
    }
    int mid = (l + r) >> 1;
    build(v + v, l, mid);
    build(v + v + 1, mid, r);
    int i = 0;
    int j = 0;
    int * a = rn[v + v];
    int * b = rn[v + v + 1];
    int c1 = cn[v + v];
    int c2 = cn[v + v + 1];
    rn[v] = mem + fr;
    int cnt = 0;
    while (i < c1 || j < c2) {
        if (i < c1 && j < c2 && a[i] == b[j]) {
            mem[fr++] = a[i];
            ++cnt;
            ++i;
            ++j;
        } else if (i >= c1 || j < c2 && a[i] > b[j]) {
            mem[fr++] = b[j];
            ++cnt;
            ++j;
        } else {
            mem[fr++] = a[i];
            ++i;
            ++cnt;
        }
    }
    fn[v] = mem + fr;
    for (int i = 0; i < cnt; i++) fn[v][i] = 0;
    cn[v] = cnt;
    fr += cnt;
//    printf("%d %d %d %d\n", v, l, r, cnt);
} 

void mod(int * f, int sz, int x, int y) {
    for (int i = x; i < sz; i |= i + 1) {
        f[i] += y;
    }
}

int gSum(int * f, int x) {
    int ret = 0;
    for (int i = x; i >= 0; i = (i & i + 1) - 1) {
        ret += f[i];
    }
    return ret;
}

int gSum(int * f, int l, int r) {
    return gSum(f, r - 1) - gSum(f, l - 1);
}

void add(int v, int ll, int rr, int x, int y) {
    while (true) {
        int l = -1;
        int r = cn[v];
        int * a = rn[v];
        int * f = fn[v];
        while (l < r - 1) {
            int mid = (l + r) >> 1;
            if (a[mid] > y) r = mid; else l = mid;
        }
        assert(l >= 0 && a[l] == y);
        mod(f, cn[v], l, 1);
        if (ll == rr - 1) break;
        int mid = (ll + rr) >> 1;
        if (x < mid) {
            rr = mid;
            v = v + v;
        } else {
            ll = mid;
            v = v + v + 1;
        }
    }
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

int get(int v, int ll, int rr, int l, int r, int y1, int y2) {
    if (rr <= l || r <= ll) return 0;
    if (l <= ll && rr <= r) {
        int * a = rn[v];
        int * f = fn[v];
        int hi = bse(a, cn[v], y2);
        int lo = bse(a, cn[v], y1);
        return gSum(f, lo, hi);
    }
    int mid = (ll + rr) >> 1;
    return get(v + v, ll, mid, l, r, y1, y2) + get(v + v + 1, mid, rr, l, r, y1, y2);
}

int get(int x1, int x2, int y1, int y2) {
//    printf("get %d %d %d %d\n", x1, x2, y1, y2);
    return get(1, 0, mx, x1, x2, y1, y2);
}

void add(int x, int y) {
//    printf("add %d %d\n", x, y);
    add(1, 0, mx, x, y);
}

int main() {
    n = ni();
    int q = ni();
    mx = 0;
    for (int i = 0; i < n; i++) {
        xs[i] = ni();
        ys[i] = ni();
        bx[i] = i;
        bxy[i] = i;
        if (mx < xs[i]) mx = xs[i];
    }
    ++mx;
    std::sort(bx, bx + n, cmpx);
    curb = 0;
    fr = 0;
    build(1, 0, mx);
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
        while (j < n && xs[bxy[j]] + ys[bxy[j]] <= ss) {
//            printf("%d\n", xs[bxy[j]] + ys[bxy[j]]);
            add(xs[bxy[j]], ys[bxy[j]]);
            ++j;
        }
        ansq[i] = get(xq[i], xq[i] + dq[i] + 1, yq[i], yq[i] + dq[i] + 1);
    }
    for (int i = 0; i < q; i++) {
        printf("%d\n", ansq[i]);
    }
}