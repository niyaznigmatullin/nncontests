#include <bits/stdc++.h>

int const INF = 1 << 30;
int const N = 1234;

int n, k;

int he[N], ne[N], to[N], from[N], dp[N][N], curdp[N], next[N], w[N], size[N];

void dfs(int v, int pv) {
  size[v] = 1;
  for (int e = he[v]; e >= 0; e = ne[e]) {
    int i = to[e];
    if (i == pv) continue;
    dfs(i, v);
    size[v] += size[i];
  }
  int cn = 0;
  curdp[0] = 0;
  for (int e = he[v]; e >= 0; e = ne[e]) {
    int i = to[e];
    if (i == pv) continue;
    for (int j = 0; j <= cn + size[i]; j++) next[j] = INF;
    for (int p1 = 1; p1 <= size[i]; p1++) {
      for (int p2 = 0; p2 <= cn; p2++) {
        for (int kill = 0; kill <= p1 && kill <= p2; kill++) {
          if (curdp[p2] != INF && dp[i][p1] != INF)
            next[p1 + p2 - 2 * kill] = std::min(next[p1 + p2 - 2 * kill], dp[i][p1] + p1 * w[e] + curdp[p2] - k * kill);
        }
      }
    }
    cn += size[i];
    for (int j = 0; j <= cn; j++) curdp[j] = next[j];
  }
  curdp[size[v]] = INF;
  for (int i = size[v] - 1; i >= 0; i--) curdp[i] = std::min(curdp[i], curdp[i + 1]);
  for (int i = 1; i <= size[v]; i++) curdp[i] = std::min(curdp[i], curdp[i - 1] + k);
  for (int i = 0; i <= size[v]; i++) dp[v][i] = curdp[i];
/*  printf("v = %d\n", v);
  for (int i = 0; i <= size[v]; i++) printf("%d ", dp[v][i]);
  puts("");(*/
}

void solve() {
  scanf("%d%d", &n, &k);
  for (int i = 0; 1 + i < n; i++) {
    scanf("%d%d%d", from + i, to + i, w + i);        
    from[i + n - 1] = to[i];
    to[i + n - 1] = from[i];
    w[i + n - 1] = w[i];
  }  
  std::fill(he, he + n, -1);
  for (int i = 0; i < n + n - 2; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  dfs(0, -1);
  int ans = INF;
  for (int i = 0; i <= n; i++) ans = std::min(ans, dp[0][i]);
  printf("%d\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
