#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <cmath>
using namespace std;

int ni() {
  int c = getchar();
  while (c != '-' && (c < '0' || c > '9')) c = getchar();
  int sg = 0;
  if (c == '-') {
    sg = 1;
    c = getchar();
  }
  int x = 0;
  while (c >= '0' && c <= '9') {
    x = x * 10 + c - '0';
    c = getchar();
  }
  return sg ? -x : x;
}

int cmp(double a, double b) {
  double d = a - b;
  if (d < 0) d = -d;
  if (d < 1e-8) return 0;
  return a < b ? -1 : 1;
}
struct point {
  double x, y, z;
  
  point() : x(0), y(0), z(0) {}
  point(double x, double y, double z) : x(x), y(y), z(z) {}
  
  point operator+(point const & p) const {
    return point(x + p.x, y + p.y, z + p.z);
  }
  point operator-(point const & p) const {
    return point(x - p.x, y - p.y, z - p.z);
  }
  point operator*(double d) const {
    return point(x * d, y * d, z * d);
  }
  double smul(point const & p) const {
    return x * p.x + y * p.y + z * p.z;
  }
  
  double len() const {
    return sqrt(x * x + y * y + z * z);
  }
};

double a[55][55];
point p1[55], p2[55];

double dtos(point const & c, int i) {
  double l = 0;
  double r = 1;
  for (int it = 0; it < 60; it++) {
    double m1 = (l + l + r) / 3;
    double m2 = (l + r + r) / 3;
    point c1 = p1[i] + (p2[i] - p1[i]) * m1;
    point c2 = p1[i] + (p2[i] - p1[i]) * m2;
    if ((c1 - c).len() > (c2 - c).len()) {
      l = m1;
    } else {
      r = m2;
    }
  }
  return (c - (p1[i] + (p2[i] - p1[i]) * l)).len();
}


double dtos2(point const & c, int i) {
  point v1 = c - p1[i];
  point v2 = c - p2[i];
  point v3 = p2[i] - p1[i];
  double smul = v1.smul(v3);
  smul /= v3.len() * v3.len();
  if (cmp(smul, 0) < 0 || cmp(smul, 1) > 1) return std::min(v1.len(), v2.len());
  return std::min((c - (p1[i] + v3 * smul)).len(), std::min(v1.len(), v2.len()));
}


int main() {
  int n = ni();
  point p0, q0;
  
    {
      double x = ni();
      double y = ni();
      double z = ni();
      p0 = {x, y, z};
    }
    {
      double x = ni();
      double y = ni();
      double z = ni();
      q0 = {x, y, z};
    }
  for (int i = 0; i < n; i++) {
    {
      double x = ni();
      double y = ni();
      double z = ni();
      p1[i] = {x, y, z};
    }
    {
      double x = ni();
      double y = ni();
      double z = ni();
      p2[i] = {x, y, z};
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      double l = 0;
      double r = 1;
      for (int it = 0; it < 60; it++) {
        double m1 = (l + l + r) / 3;
        double m2 = (l + r + r) / 3;
        point c1 = p1[i] + (p2[i] - p1[i]) * m1;
        point c2 = p1[i] + (p2[i] - p1[i]) * m2;
        if (dtos(c1, j) > dtos(c2, j)) {
          l = m1;
        } else {
          r = m2;
        }
      }
      a[i][j] = dtos(p1[i] + (p2[i] - p1[i]) * l, j);
//      printf("%d %d %.17lf\n", i, j, a[i][j]);
    }
  }
  for (int k = 0; k < n; k++) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        double dist = a[i][k] + a[k][j];
        if (a[i][j] > dist) a[i][j] = dist;
      }
    }
  }
  double ans = 1e20;
  for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++) {
      double cur = dtos(p0, i) + dtos(q0, j) + a[i][j];
      if (cur < ans) ans = cur;
    }
  double dd = (q0 - p0).len();
  if (dd < ans) ans = dd;
  printf("%.17lf\n", ans);
}
