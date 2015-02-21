#include <bits/stdc++.h>

using namespace std;

int const N = 123456;

vector<int> edges[N], redges[N], cedges[N];
vector<int> topsort;
int color[N], was[N];
unsigned long long w[N], d[N];

unsigned long long gcd(unsigned long long a, unsigned long long b) {
  return b == 0 ? a : gcd(b, a % b);
}

void dfs(int v) {
  was[v] = true;
  for (int i : edges[v]) {
    if (!was[i]) dfs(i);
  }
  topsort.push_back(v);
}

void dfs2(int v, int c) {
  color[v] = c;
  for (int i : redges[v]) {
    if (color[i] < 0) dfs2(i, c);
  }
}

void solve() {
  int n, m;
  unsigned long long x;
  cin >> n >> m >> x;
  for (int i = 0; i < n; i++) {
    edges[i].clear();
    redges[i].clear();
  }
  for (int i = 0; i < m; i++) {
    int v, u;
    cin >> v >> u;
    --v;
    --u;
    edges[v].push_back(u);
    redges[u].push_back(v);
  }
  for (int i = 0; i < n; i++) cin >> d[i];
  std::fill(was, was + n, 0);
  std::fill(color, color + n, -1);
  topsort.clear();
  for (int i = 0; i < n; i++) {
    if (!was[i]) {
      dfs(i);
    }
  }
  reverse(topsort.begin(), topsort.end());
  int cc = 0;
  for (int i : topsort) {
    if (color[i] >= 0) continue;
    dfs2(i, cc);
    cc++;
  }
  for (int i = 0; i < cc; i++) cedges[i].clear();
  for (int i = 0; i < n; i++)
    for (int j : edges[i])
      if (color[i] != color[j])
        cedges[color[i]].push_back(color[j]);
  std::fill(w, w + cc, 0);
  for (int i = 0; i < n; i++) w[color[i]] = gcd(w[color[i]], d[i]);
  for (int i = 0; i < cc; i++)
    for (int j : cedges[i])
      w[j] = gcd(w[j], w[i]);
  for (int i = 0; i < n; i++) d[i] = w[color[i]];
  int ans = 0;
  for (int i = 0; i < n; i++) if (d[i] == x) ++ans;
  cout << ans << '\n';
}

int main() {
  cin.sync_with_stdio(0);
  int t;
  cin >> t;
  for (int i = 0; i < t; i++) solve();
}