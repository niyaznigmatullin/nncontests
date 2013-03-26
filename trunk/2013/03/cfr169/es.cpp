#include <cstdio>

int ss[123123], ff[123123], ne[232323], he[123123], val[123123];


void dfs(int v, int p, int d, int add) {
    if (d < 0) return;
    val[v] += add;
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (p == ff[e]) continue;
        dfs(ff[e], v, d - 1, add);   
    }
}

int main() {
    int n, q;
    scanf("%d %d\n", &n, &q);
    for (int i = 0; i + 1 < n; i++) {
        scanf("%d %d", ss + i, ff + i);
        --ss[i];
        --ff[i];
        ss[i + n - 1] = ff[i];
        ff[i + n - 1] = ss[i];
    }
    for (int i = 0; i < n; i++) he[i] = -1, val[i] = 0;
    for (int i = 0; i < (n - 1) * 2; i++) {
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    for (int i = 0; i < q; i++) {
        int type, v;
        scanf("%d %d", &type, &v);
        --v;
        if (type == 0) {
            int add, d;
            scanf("%d %d", &add, &d);
            dfs(v, -1, d, add);
        } else {
            printf("%d\n", val[v]);
        }
    }
}