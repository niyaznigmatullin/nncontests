#include <cstdio>
#include <cassert>


int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    int sg = c == '-';
    if (sg) c = getchar();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

const int N = 111;
const int M = 22222;
int ss[M], ff[M], w[M], he[N], ne[M], p[N], d[M];

int get(int x) {
    return x == p[x] ? x : (p[x] = get(p[x]));
}

void dfs(int v, int dd, int p) {
    d[v] = dd;
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (ff[e] == p) continue;
        dfs(ff[e], dd + w[e], v);
    }
}


int main() {
    int n = ni();
    int m = ni();
    int s = ni();
    for (int i = 0; i < n; i++)  {
        he[i] = -1;
        p[i] = i;
    }
    for (int i = 0; i < m; i++) {
        ff[i] = ni() - 1;
        ss[i] = ni() - 1;
        w[i] = ni();
        ff[i + m] = ss[i];
        ss[i + m] = ff[i];
        w[i + m] = w[i];
    }
    for (int i = 0; i < m; i++) {
        if (get(ss[i]) == get(ff[i])) {
            puts("YES");
            return 0;
        }
        p[get(ss[i])] = get(ff[i]);
    }
    m += m;
    for (int i = 0; i < m; i++) {
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    int ans = 0;
    for (int i = 0; i < n; i++) {
        dfs(i, 0, i);
        for (int j = 0; j < n; j++) if (ans < d[j]) ans = d[j];
    }
    if (ans >= s) {
        puts("YES");
    } else {
        puts("NO");
    }
}