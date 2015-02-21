#include <bits/stdc++.h>

using namespace std;

int const N = 888;

int s[N][N], mark[N][N];

int str[N], ans[N * N];

void solve() {
  int n, m, k;
  scanf("%d%d%d", &k, &n, &m);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      s[i][j] = c;
      mark[i][j] = false;
    }
  }
  for (int i = 0; i < k; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    int len = 0;
    while (c > 32) {
      str[len++] = c;
      c = getchar();
    }
    set<set<pair<int, int> > > f;
    for (int x = 0; x < n; x++) {
      for (int y = 0; y < m; y++) {
        for (int dx = -1; dx <= 1; dx++) {
          for (int dy = -1; dy <= 1; dy++) {
            if (dx == 0 && dy == 0) continue;
            int cx = x;
            int cy = y;
            bool ok = true;
            for (int k = 0; k < len; k++) {
              if (cx < 0 || cy < 0 || cx >= n || cy >= m || str[k] != s[cx][cy]) {
                ok = false;
                break;
              }
              cx += dx;
              cy += dy;
            }
            if (ok) {
              set<pair<int, int> > d;
              for (int k = 0; k < len; k++) {
                d.insert(make_pair(x + dx * k, y + dy * k));
                mark[x + dx * k][y + dy * k] = true;
              }
              f.insert(d);
            }
          }
        }
      }
    }
    if (f.empty()) {
      puts("no solution");
      return;
    }
    if (f.size() > 1) {
      puts("ambiguous");
      return;
    }    
  }
  int ac = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) if (!mark[i][j]) ans[ac++] = s[i][j];
  }
  if (ac == 0) {
    puts("empty spell");
  } else {
    for (int i = 0; i < ac; i++) putchar(ans[i]);
    puts("");
  }
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
