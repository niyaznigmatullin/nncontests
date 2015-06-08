struct centroid {
    int v;
    int d;
};



int const N = 1234567;

int from[N], to[N];


void dfs(int v, int pv) {
    sz[v] = 1;
    for (int e = he[v]; e >= 0; e = ne[e]) {
    if (banned[to[e]] || to[e] == pv) continue;
dfs(to[e], v);
sz[v] += sz[to[e]];
    }
}


void dfs2(int v, int pv, int cv, int d) {
    cs[v].push_back({cv, d});
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (banned[to[e]] || to[e] == pv) continue;
        dfs2(to[e], v, cv, d + 1);
    }
}
int go(int v) {
    dfs(v, -1);
    int all = sz[v];
    {
    int pv = -1;
    
    while (true) {
        bool changed = false;
for (int e = he[v]; e >= 0; e = ne[e]) {
    if (!banned[to[e]]) {
        if (sz[to[e]] * 2 >= all) {
            pv = v;
            v = to[e];
            changed = true;
            break;
        }
    }
}
    if (!changed) break;
    }
    }
    
    dfs2(v, -1, v, 0);
banned[v] = true;
    for (int e = he[v]; e >= 0; e = ne[e]) {
    if (!banned[to[e]]) {
        go(to[e]);
    }
    }
}
int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i + 1 < n; i++) {
        scanf("%d%d", from + i, to + i);
        --from[i];
    --to[i];
    from[i + n - 1] = to[i];
    to[i + n - 1] = from[i];
    }
    for (int i = 0; i < n; i++) he[i] = -1;
    for (int i = 0; i < n + n - 2; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
    }
    go(0);
}