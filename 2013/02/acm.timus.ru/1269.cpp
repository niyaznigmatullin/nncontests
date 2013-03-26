#include <cstdio>
#include <memory.h>


const int MAXN = 111111;
const int INF = (1 << 30);
int he[MAXN], ne[MAXN], lto[MAXN], let[MAXN], de[MAXN], fr, frn, q[MAXN], sl[MAXN], toterm[MAXN];
bool term[MAXN];
char s[1111111];

int readln() {    
    gets(s);
    int n = 0;
    for (int i = 0; s[i]; i++) {
        int c = s[i];
        if (c >= '0' && c <= '9') {
            n = n * 10 + c - '0';
        }
    }
    return n;
}

int get_link(int v, int c) {
    for (int e = he[v]; ; e = ne[e]) {
        int letter = e < 0 ? INF : let[e];
        if (letter > c) return -1;
        if (letter == c) return lto[e];
    }
}

void set_link(int v, int c, int t) {
    for (int e = he[v], last = -1; ; e = ne[e]) {
        int letter = e < 0 ? INF : let[e];
        if (letter == c) {
            lto[e] = t;
            return;
        }
        if (letter > c) {
            ne[fr] = e;
            if (last < 0) he[v] = fr; else ne[last] = fr;
            lto[fr] = t;
            let[fr] = c;
            fr++;
            return;
        }
        last = e;
    } 
}

int go(int v, int c) {
    int z = get_link(v, c);
    if (z >= 0) return z;
    int ret = frn++;
    set_link(v, c, ret);
    de[ret] = de[v] + 1;
    return ret;
}

int main() {
    int n = readln();
    fr = 0;
    frn = 1;
    memset(term, 0, sizeof term);
    memset(he, -1, sizeof he);
    memset(sl, -1, sizeof sl);
    de[0] = 0;
    for (int i = 0; i < n; i++) {
        gets(s);
        int v = 0;
        for (int j = 0; s[j]; j++) {
            v = go(v, s[j]);
        }
        term[v] = true;
    }
    int head = 0;
    int tail = 1;
    q[head] = 0;
    while (head < tail) {
        int v = q[head++];
        for (int e = he[v]; e >= 0; e = ne[e]) {
            int u = lto[e];
            int vv = sl[v];
            while (vv >= 0 && get_link(vv, let[e]) < 0) vv = sl[vv];
            if (vv < 0) sl[u] = 0; else sl[u] = get_link(vv, let[e]);
            q[tail++] = u;
        }
        toterm[v] = -1;
        if (term[v]) {
            toterm[v] = v;
        } else if (sl[v] >= 0) {
            toterm[v] = toterm[sl[v]];
        }
    }
    int m = readln();
    for (int i = 0; i < m; i++) {
        gets(s);
        int v = 0;
        int ans = INF;
        for (int j = 0; s[j]; j++) {
            while (v > 0 && get_link(v, s[j]) < 0) {
                v = sl[v];
            }
            v = get_link(v, s[j]);
            if (v < 0) v = 0;
            if (toterm[v] >= 0) {
                int vv = toterm[v];
                if (j - de[vv] < ans) ans = j - de[vv];
            }
        }
        if (ans != INF) {
            printf("%d %d\n", i + 1, ans + 2);
            return 0;
        }
    }
    puts("Passed");
}
