#include <cstdio>
#include <algorithm>

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

const int N = 222;
const int M = 123456;
int z[N], ss[M], ff[M], id[N], mz[N], w[M], a[N][N];

bool bz(int x, int y) {
    return z[x] < z[y];
}

int main() {
    int n = ni();
    int m = ni();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) 
            a[i][j] = INF;
        a[i][i] = 0;
    }
    for (int i = 0; i < m; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
        w[i] = ni() * 2;
        a[ss[i]][ff[i]] = w[i];
        a[ff[i]][ss[i]] = w[i];
    }
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (a[i][k] == INF || a[k][j] == INF) continue;
                int val = a[i][k] + a[k][j];
                if (a[i][j] > val) a[i][j] = val;
            }
    }
    int ans = INF;
    for (int i = 0; i < n; i++) {
        int cur = 0;
        for (int j = 0; j < n; j++) if (cur < a[i][j]) cur = a[i][j];
        if (ans > cur) ans = cur;
    }
    printf("%.18lf\n", ans * .5);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            id[j] = j;
            z[j] = (a[ff[i]][j] + w[i] - a[ss[i]][j]) >> 1;            
        }
        std::sort(id, id + n, bz);
        mz[n] = -INF;
        for (int j = n - 1; j >= 0; j--) {
            mz[j] = mz[j + 1];
            if (mz[j] < a[ss[i]][id[j]]) mz[j] = a[ss[i]][id[j]];
        }
        int m2 = -INF;
        int last = 0;
        for (int it = 0; it < n; it++) {
            int v = id[it];
            if (m2 != -INF) {
                int zz = (mz[it] + w[i] - m2) >> 1;
                printf("%d\n", zz);
                if (zz >= 0 && zz <= w[i] && zz <= z[v] && zz >= last) {
                    int cur = m2 + w[i] - zz;
                    if (cur < mz[it] + zz) cur = mz[it] + zz;
                    if (cur < ans) ans = cur;
                }
            }
            if (m2 < a[ff[i]][v]) m2 = a[ff[i]][v];
            if (z[v] >= 0 && z[v] <= w[i] && mz[it + 1] != -INF) {
                int cur = m2 + w[i] - z[v];
                if (cur < mz[it + 1] + z[v]) cur = mz[it + 1] + z[v];
                if (cur < ans) ans = cur;
            }
            last = z[v];
        }
    }
    printf("%.17lf\n", ans * .5);
}
