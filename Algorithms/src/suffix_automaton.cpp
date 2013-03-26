#include <cstdio>
#include <cassert>

const int N = 200001;
const int A = 111;
const int INF = ~(1 << 31);
int sl[N], len[N], fr, lfr;
int he[N], ne[2 * N], vto[2 * N], let[2 * N];

int newNode(int l) {
    len[fr] = l;
    he[fr] = -1;
    sl[fr] = -1;
    return fr++;
}

int copyNode(int v, int l) {
    len[fr] = l;
    sl[fr] = sl[v];
    if (he[v] < 0) he[fr] = -1; else {
        he[fr] = lfr++;
        for (int e = he[v], f = he[fr]; e >= 0; e = ne[e], f = ne[f]) {
            vto[f] = vto[e];
            let[f] = let[e];
            if (ne[e] < 0) ne[f] = -1; else ne[f] = lfr++;
        }
    }
    return fr++;
}

int get_link(int v, int c) {
    for (int e = he[v]; ; e = ne[e]) {
        int l = e < 0 ? INF : let[e];
        if (l == c) {
            return vto[e];
        }
        if (l > c) return -1;
    }
}

void set_link(int v, int c, int u) {
    for (int e = he[v], last = -1; ; e = ne[e]) {
        int l = e < 0 ? INF : let[e];
        if (l == c) {
            vto[e] = u;
            return;
        }
        if (l > c) {
            if (last < 0) he[v] = lfr; else ne[last] = lfr;
            ne[lfr] = e;
            let[lfr] = c;
            vto[lfr] = u;
            lfr++;
            return;
        }
        last = e;
    }   
}

int append(int v, int c) {
    int n = newNode(len[v] + 1);
    while (v >= 0 && get_link(v, c) < 0) {
        set_link(v, c, n);
        v = sl[v];
    }
    if (v < 0) {
        sl[n] = 0;
        return n;
    }
    int q = get_link(v, c);
    if (len[q] == len[v] + 1) {
        sl[n] = q;
        return n;
    }
    int cp = copyNode(q, len[v] + 1);
    while (v >= 0 && get_link(v, c) == q) {
        set_link(v, c, cp);
        v = sl[v];
    }
    sl[q] = sl[n] = cp;
    return n;
}

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

int n;
int a[N];

void solve() {
    for (int i = 0; i < n; i++) {
        a[i + n] = a[i] = ni();
    }
    lfr = fr = 0;
    int r = newNode(0);
    long long z = 0;
    for (int i = 0; i < n + n; i++) {
        r = append(r, a[i]);
        int k = len[r] - len[sl[r]];
        int d = i - n + 1;
        if (d > k) continue;
        if (d < 0) d = 0;
        z += k - d;
    }
    z = (long long) n * n - z;
    printf("%I64d\n", z);
}

int main() {
    while (true) {
        n = ni();
        if (n == 0) break;
        solve();
    }
}