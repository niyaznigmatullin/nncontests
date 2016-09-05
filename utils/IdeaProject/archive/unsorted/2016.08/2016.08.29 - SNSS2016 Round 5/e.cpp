#include <bits/stdc++.h>

using namespace std;

int const N = 222;
int a[N][N];
double d[N][N];
double pr[N];

int main() {
  int n, m, st, fin;
  cin >> n >> m >> st >> fin;
  --st;
  --fin;
  for (int i = 0; i < n; i++) cin >> pr[i];
  for (int i = 0; i < n; i++) pr[i] = -pr[i];
  for (int i = 0; i < m; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    --x;
    --y;
    a[x][y] = a[y][x] = 1;
  }
  set<pair<double, int> > q;
  if (pr[st] > 0) {
    d[st][st] = 1.;
    q.insert({-1., n * st + st});
  } else {
    d[n][st] = 1 - std::max(-pr[st], 0.);
    q.insert({-1., n * n + st});
  }
  while (!q.empty()) {
    pair<double, int> vv = *q.begin();
    q.erase(q.begin());
    int v = vv.second % n;
    int w = vv.second / n;
    double prob = d[w][v];
    double def = w == n ? 0 : pr[w];
    for (int to = 0; to < n; to++) {
      if (!a[v][to]) continue;
      double cur = 1.;
      int nw = w;
      if (pr[to] < 0) {
        cur = 1 - std::max(-(pr[to] + def), 0.);
      } else if (pr[to] > def) {
        nw = to;
      }
      cur *= prob;
      if (d[nw][to] < cur) {
        d[nw][to] = cur;
        q.insert({-d[nw][to], nw * n + to});
      }
    }
  }
  double ans = 0;
  for (int i = 0; i <= n; i++) {
    ans = std::max(ans, d[i][fin]);
  }
  printf("%.18f\n", ans);
}
