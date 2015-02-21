#include <bits/stdc++.h>

int const N = 333;

int rc[N], edges[N][N], salary[N], rem[N], was[N];

void dfs(int v) {
  if (rem[v]) return;
  was[v] = true;
  for (int i = 0; i < rc[v]; i++) {
    int to = edges[v][i];
    if (!was[to]) dfs(to);
  }
}

void solve() {
  int n, C;
  scanf("%d%d", &n, &C);
  int root = -1;
  std::fill(rc, rc + n, 0);
  for (int i = 0; i < n; i++) {
    int k;
    scanf("%d%d", salary + i, &k);
    for (int j = 0; j < k; j++) {
      int x;
      scanf("%d", &x);
      edges[x][rc[x]++] = i;
    }
    if (k == 0) root = i;
  }
  int ans = 1 << 30;
  int ansi = -1;
  for (int i = 0; i < n; i++) {
    rem[i] = true;
    std::fill(was, was + n, 0);
    dfs(root);
    int sum = 0;
    for (int j = 0; j < n; j++) if (!was[j]) sum += salary[j];
    if (sum >= C) {
      if (sum <= ans) {
        ans = sum;
        ansi = i;
      }
    }
    rem[i] = false;
  }
  printf("%d\n", ansi);
}
  
int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
