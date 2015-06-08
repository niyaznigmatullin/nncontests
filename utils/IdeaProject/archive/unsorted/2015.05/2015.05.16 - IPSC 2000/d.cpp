#include <bits/stdc++.h>

using namespace std;

int main() {
    int n, m, c;
    scanf("%d%d%d", &n, &m, &c);
    for (int i = 0; i < n; i++) {
        fill(a[i], a[i] + n, INF);
        a[i][i] = 0;
    }
    for (int i = 0; i < m; i++) {
        int v, u, w;
        scanf("%d%d%d", &v, &u, &w);
        --v;
        --u;
        a[v][u] = a[u][v] = std::min(a[v][u], w);
    }
    for (int k = 0; k < n; k++)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) a[i][j] = std::min(a[i][j], a[i][k] + a[k][j]);
    
}