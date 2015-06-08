#include <bits/stdc++.h>

using namespace std;

int const N = 5555;

int a[N][N];
int f[N];
int st[N];

void solve() {
    int n, m;
    scanf("%d%d", &n, &m);
    int q;
    scanf("%d", &q);
    int ans = 0;
    for (int i = 0; i < q; i++) {
        int x1, y1, x2, y2;
        scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
        if (x1 > x2) swap(x1, x2);
        if (y1 > y2) swap(y1, y2);
        ans = max(ans, (x2 - x1) * (y2 - y1));
        if (x1 > 0) x1--;
        if (x2 < n) x2++;
        if (y1 > 0) --y1;
        if (y2 < m) ++y2;
        a[x1][y1]++;
        a[x2][y1]--;
        a[x1][y2]--;
        a[x2][y2]++;
    }
    for (int i = 0; i < n; i++)
        for (int j = 1; j < m; j++) a[i][j] += a[i][j - 1];
    for (int i = 1; i < n; i++)
        for (int j = 0; j < m; j++) a[i][j] += a[i - 1][j];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) if (a[i][j] == 0) f[j]++; else f[j] = 0;
        int cn = 0;
        for (int j = 0; j <= m; j++) {
            while (cn > 0 && f[st[cn - 1]] >= f[j]) {
                --cn;
                int prev = cn > 0 ? st[cn - 1] : -1;
                ans = max(ans, (j - prev - 1) * f[st[cn]]);
            }
            st[cn++] = j;
        }
    }
    printf("%d\n", ans);
}

int main() {
    int t;
//    scanf("%d", &t);
t = 1;
    while (t--) {
        solve();
    }
}
