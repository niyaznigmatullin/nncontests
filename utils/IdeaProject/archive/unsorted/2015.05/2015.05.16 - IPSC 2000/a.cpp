#include <bits/stdc++.h>

int const dx[] = {0, 1, 1, 1};
int const dy[] = {1, -1, 0, 1};

int s[222][222];

int winx = 0;
int wino = 0;
    
void solve() {
    int n, m, k;
    scanf("%d%d%d", &m, &n, &k);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            s[i][j] = c;
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (s[i][j] != 'x' && s[i][j] != 'o') continue;
            for (int dir = 0; dir < 4; dir++) {
                bool all = true;
                for (int e = 0; e < k; e++) {
                    int x = i + dx[dir] * e;
                    int y = j + dy[dir] * e;
                    if (x < 0 || y < 0 || x >= n || y >= m || s[x][y] != s[i][j]) {
                        all = false;
                        break;
                    }
                }
                if (all) {
                  if (s[i][j] == 'x') {
                      winx++;
                  } else {
                      wino++;
                  }
                  return;
                }
            }
        }
    }
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
    printf("%d:%d\n", winx, wino);
}
