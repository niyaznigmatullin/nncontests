#include <bits/stdc++.h>

int a[1234][1234];

int main() {
    int n, m, k;
    scanf("%d%d%d", &n, &m, &k);
    for (int i = 1; 2 * i <= k + 1; i++) {
        for (int v = 0; v < n; v++) a[v][(v + i) % n] = a[(v + i) % n][v] = 1;
    }
    if (k & 1)
        for (int i = 0; i + 1 < n; i += 2) a[i][i + 1] = a[i + 1][i] = 0;
    int all = 0;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++) all += a[i][j];
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++) if (all < m && a[i][j] == 0) {
            a[i][j] = 1;
            all++;
        }
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++) if (a[i][j] == 1) printf("%d %d\n", i + 1, j + 1);
}