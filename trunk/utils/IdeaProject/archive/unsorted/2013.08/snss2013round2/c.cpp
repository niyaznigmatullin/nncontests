#include <cmath>
#include <cstdio>

#include <iostream>

using namespace std;

const int N = 333;
double x[N], y[N], xx[N], yy[N], dd[N], dd2[N], d[N][N];
int pp[N], g[N][N];
int md;
int n;
int was[N];

bool dfs(int v) {
  if (was[v] == md) return false;
  was[v] = md;
  for (int i = 0; i < n; i++) {
    if (!g[v][i]) continue;
    if (pp[i] < 0 || dfs(pp[i])) {
      pp[i] = v;
      return true;
    }
  }
  return false;
}

int main() {
  double t;
  cin >> n >> t;
  for (int i = 0; i < n; i++) {
    cin >> x[i] >> y[i];
    dd[i] = std::min(std::min(x[i], 1 - x[i]), std::min(y[i], 1 - y[i]));
  }
  for (int i = 0; i < n; i++) {
    cin >> xx[i] >> yy[i];
    dd2[i] = std::min(std::min(xx[i], 1 - xx[i]), std::min(yy[i], 1 - yy[i]));
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      double dx = x[i] - xx[j];
      double dy = y[i] - yy[j];
      d[i][j] = 2 * sqrt(dx * dx + dy * dy) + dd[i] + dd2[j];
    }
  }
  double l = 0;
  double r = 1e10;
  md = 0;
  for (int it = 0; it < 100; it++) {
    double mid = (l + r) * .5;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        g[i][j] = (d[i][j] < mid * t);
      }
    }
    for (int i = 0; i < n; i++) pp[i] = -1;
    int cn = 0;
    for (int i = 0; i < n; i++) {
      ++md;
      if (dfs(i)) ++cn;
    }
    if (cn == n) r = mid; else l = mid;
  }
  printf("%.17lf\n", r);
}