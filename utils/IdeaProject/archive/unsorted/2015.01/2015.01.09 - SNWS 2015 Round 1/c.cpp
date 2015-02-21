#include <bits/stdc++.h>

int const N = 2234567;


int to[N], from[N], w[N], he[N], ne[N];
long long mdown[N];
long long ans;


void dfs(int v, int pv) {
  long long m1 = 0;
  long long m2 = 0;
  for (int e = he[v]; e >= 0; e = ne[e]) {
    if (to[e] == pv) continue;
    dfs(to[e], v);
    long long cur = mdown[to[e]] + w[e];
    if (m1 < cur) {
      m2 = m1;
      m1 = cur;
    } else if (m2 < cur) {
      m2 = cur;
    }
  }
  if (m1 + m2 > ans) ans = m1 + m2;
  mdown[v] = m1;
}

int main() {
  int n;
  scanf("%d", &n);
  ans = -(1LL << 60);
  for (int i = 0; i < n - 1; i++) {
    scanf("%d%d%d", from + i, to + i, w + i);
    --from[i];
    --to[i];
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
  std::cout << ans << "\n";
}