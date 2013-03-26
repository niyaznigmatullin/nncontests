#include <cstdio>

void read(int * s, int & n){
    n = 0;
    int c = getchar();
    while (c < 'a' || c > 'z') c = getchar();
    while (c >= 'a' && c <= 'z') {
        s[n++] = c - 'a';
        c = getchar();
    }
}

const int INF = ~(1 << 31);

int s[11111], t[11111];
int link[33][11111], p[11111], dp[11111][1111];

int main() {
    freopen("necklace.in", "r", stdin);
    freopen("necklace.out", "w", stdout);
    int n;
    read(s, n);
    int m;
    read(t, m);
    p[0] = 0;
    int k = 0;
    for (int i = 1; i < m; i++) {
        while (k > 0 && t[k] != t[i]) k = p[k - 1];
        if (t[k] == t[i]) ++k;
        p[i] = k;
    }
    for (int i = 0; i < 26; i++) link[i][0] = 0;
    link[t[0]][0] = 1;
    for (int i = 1; i <= m; i++) {
        for (int j = 0; j < 26; j++) {        
            int k = i;
            while (k > 0 && t[k] != j) {
                k = p[k - 1];
            }
            if (t[k] == j) ++k;
            link[j][i] = k;
        }
    }
    for (int i = 0; i <= n; i++) for (int j = 0; j <= m; j++) dp[i][j] = INF;
    dp[0][0] = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {        
            if (dp[i][j] == INF) continue;
            if (dp[i + 1][j] > dp[i][j] + 1) dp[i + 1][j] = dp[i][j] + 1;
            int & cur = dp[i + 1][link[s[i]][j]];
            if (cur > dp[i][j]) cur = dp[i][j];
        }
    }
    int ans = INF;
    for (int i = 0; i < m; i++) {
        if (ans > dp[n][i]) ans = dp[n][i];
    }
    printf("%d\n", ans);
}