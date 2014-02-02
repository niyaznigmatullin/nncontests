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

const int N = 12345;

int xx[N], yy[N];
double d[N];
int b[N], e[N], was[N];

double len(double x, double y) {
  return sqrt(x * x + y * y);
}

bool isin(int x, int i) {
  return (b[i] <= x && x <= e[i] && b[i] <= e[i]) || (e[i] <= x && x <= b[i] && e[i] <= b[i]);
}

int main() {
  int n = ni();
  int st = ni();
  int fi = ni();
  int sx = ni();
  int sy = ni();
  if (st == fi) {
    puts("0");
    return 0;
  }
  for (int i = 0; i < n; i++) {
    xx[i] = ni();
    yy[i] = ni();
    b[i] = ni();
    e[i] = ni();
  }
  double INF = 1e20;
  for (int i = 0; i < n; i++) {
    if (sx == xx[i] && sy == yy[i] && isin(st, i)) {
      st = e[i];
    }
  }
  for (int i = 0; i < n; i++) {
    if (isin(st, i)) {
      d[i] = len(sx - xx[i], sy - yy[i]);
    } else {
//      d[i] = len(len(sx - xx[i], sy - yy[i]), st - b[i]);
      d[i] = INF;
    }
  }
  while (true) {
    int mi = -1;
    for (int i = 0; i < n; i++) {
      if (was[i] || d[i] == INF) continue;
      if (mi < 0 || d[i] < d[mi]) mi = i;
    }
    if (mi < 0) break;
    was[mi] = true;
    for (int i = 0; i < n; i++) {
      if (isin(e[mi], i)) {
        double dd = len(xx[mi] - xx[i], yy[mi] - yy[i]);
        if (d[i] > d[mi] + dd) {
          d[i] = d[mi] + dd;
        }
      } else {
//        double dd = len(len(xx[mi] - xx[i], yy[mi] - yy[i]),e[mi] - b[i]);
//        if (d[i] > d[mi] + dd) d[i] = d[mi] + dd;
      }
    }
  }
  double ans = INF;
  for (int i = 0; i < n; i++) {
    if (e[i] == fi) {
      if (ans > d[i])
        ans = d[i];
    }
  }
  if (ans == INF) puts("-1"); else printf("%.17f\n", ans);
}
