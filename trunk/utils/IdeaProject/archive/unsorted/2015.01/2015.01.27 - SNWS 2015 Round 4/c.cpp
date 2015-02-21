#include <bits/stdc++.h>

using namespace std;

double ea[523456];

void solve() {
  int n, d;
  scanf("%d%d", &n, &d);
  vector<int> x(n), y(n);
  for (int i = 0; i < n; i++) scanf("%d%d", &x[i], &y[i]);
  int cn = 0;
  ea[cn++] = 0;
  ea[cn++] = 2 * M_PI;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (i == j) continue;
      double ang = atan2(x[j] - x[i], y[j] - y[i]);
      if (ang < 0) ang += 2 * M_PI;
      ea[cn++] = ang;
      ang = atan2(y[j] - y[i], x[j] - x[i]);
      if (ang < 0) ang += 2 * M_PI;
      ea[cn++] = ang;
    }
  }
  std::sort(ea, ea + cn);
  for (int i = 0; i < cn; i++) printf("%.5f ", ea[i]);
  puts("");
  double ans = 0;
  for (int i = 1; i < cn; i++) {
    double ang = (ea[i] + ea[i - 1]) * .5;
    double ca = cos(ang);
    double sa = sin(ang);
    int id = -1;
    double maxx = -1e20;
    for (int j = 0; j < n; j++) {
      double cx = x[j] * ca - y[j] * sa;
      if (cx > maxx) {
        maxx = cx;
        id = j;
      }
    }
    ans += x[id] * (sin(ea[i]) - sin(ea[i - 1])) + y[id] * (cos(ea[i]) - cos(ea[i - 1]));
  }
  ans *= 2;
  ans = 1. - ans / d;
  ans /= 2 * M_PI;
  printf("%.17f\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}