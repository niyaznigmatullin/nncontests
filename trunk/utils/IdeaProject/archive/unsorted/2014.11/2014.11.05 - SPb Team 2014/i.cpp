#include <cstdio>
#include <algorithm>
#include <cmath>

double const PI = acos(-1.);
double const INF = 1e30;
double const EPS = 1e-8;
int const N = 6000;
double px[N], py[N], vx[N], vy[N], d[N];
int a[N], b[N], c[N], st[N], fi[N], q[N], was[N], id[N];

int comp(double a, double b) {
  double d = a - b;
  if (d < 0) d = -d;
  if (d < EPS) return 0;
  return a < b ? -1 : 1;
}

double vmul(int i, int j) {
  return vx[i] * vy[j] - vx[j] * vy[i];
}

double smul(int i, int j) {
  return vx[i] * vx[j] + vy[i] * vy[j];
}

int main() {
  freopen("saferoute.in", "r", stdin);
  freopen("saferoute.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    int x1, y1, x2, y2;
    scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
    a[i] = y2 - y1;
    b[i] = x1 - x2;
    c[i] = -a[i] * x1 - b[i] * y1;
  }
  for (int i = 0; i < 2; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    px[i] = x;
    py[i] = y;
  }  
  int cn = 2;
  for (int i = 0; i < n; i++) {
    for (int j = i + 1; j < n; j++) {
      int z = a[i] * b[j] - b[i] * a[j];
      if (z == 0) continue;
      double cx = 1. * (b[i] * c[j] - b[j] * c[i]) / z;
      double cy = 1. * (c[i] * a[j] - a[i] * c[j]) / z;
      bool found = false;
      for (int k = 0; k < cn; k++) {
        if (comp(cx, px[k]) == 0 && comp(cy, py[k]) == 0) {
          found = true;
          break;
        }
      }
      if (!found) {
        px[cn] = cx;
        py[cn++] = cy;
      }
    }
  }
  for (int i = 0; i < cn; i++) id[i] = i;
  for (int i = 0; i < cn; i++)
    for (int j = i + 1; j < cn; j++) {
      int c1 = comp(px[i], px[j]);
      int c2 = comp(py[i], py[j]);
      if (c1 > 0 || (c1 == 0 && c2 > 0)) {
        std::swap(px[i], px[j]);
        std::swap(py[i], py[j]);
        std::swap(id[i], id[j]);
      }
    }
  int start = -1;
  int finish = -1;
  for (int i = 0; i < cn; i++) if (id[i] == 0) start = i; else if (id[i] == 1) finish = i;
  int cs = 0;
  for (int i = 0; i < n; i++) {
    int cc = 0;
    for (int j = 0; j < cn; j++) {
      double f = a[i] * px[j] + b[i] * py[j] + c[i];
      if (comp(f, 0) == 0) {
        q[cc++] = j;
      }
    }
    for (int j = 0; j + 1 < cc; j++) {
      st[cs] = q[j];
      fi[cs] = q[j + 1];
      cs++;
      st[cs] = q[j + 1];
      fi[cs] = q[j];
      cs++;      
    }
  }
  for (int i = 0; i < cs; i++) {
    d[i] = INF;
  }
  for (int i = 0; i < cs; i++) {
    if (st[i] == start) d[i] = 0;
  }
  for (int i = 0; i < cs; i++) vx[i] = px[fi[i]] - px[st[i]], vy[i] = py[fi[i]] - py[st[i]];
  while (true) {
    int mi = -1;
    for (int i = 0; i < cs; i++) {
      if (was[i] || d[i] == INF) continue;
      if (mi < 0 || d[mi] > d[i]) mi = i;
    }
    if (mi < 0) break;
    was[mi] = true;
    for (int i = 0; i < cs; i++) {
      if (st[i] != fi[mi]) continue;
      double ang = atan2(vmul(mi, i), smul(mi, i));
      if (ang < 0) ang = -ang;
      if (d[i] > d[mi] + ang) d[i] = d[mi] + ang;
    }
  }
  double ans = INF;
  for (int i = 0; i < cs; i++) {
    if (fi[i] == finish) {
      if (ans > d[i]) ans = d[i];
    }
  }
  if (ans == INF) puts("-1"); else {
    printf("%.17lf\n", ans / PI * 180);
  }
}