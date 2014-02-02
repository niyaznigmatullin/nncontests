#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
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

int compare(double a, double b) {
  double d = a - b;
  if (d < 0) d = -d;
  if (d < 1e-7) return 0;
  return a < b ? -1 : 1;
}

double dpd[20][1 << 18], dpw[20][1 << 18], p1[20], p2[20];

int main() {
  int n = ni();
  for (int i = 0; i < n; i++) {
    scanf("%lf", p1 + i);
  } 
  for (int i = 0; i < n; i++) {
    scanf("%lf", p2 + i);
  } 
  for (int mask = 0; mask < 1 << 2 * n; mask++) {
    int cn1 = 0;
    int cn2 = 0;
    for (int i = 0; i < 2 * n; i++) {
      if (((mask >> i) & 1) == 1) {
        if (i & 1) ++cn2; else ++cn1;
      }
    }
    dpw[2 * n][mask] = cn1 > cn2 ? 1 : 0;
    dpd[2 * n][mask] = cn1 == cn2 ? 1 : 0;
  }
  for (int move = 2 * n - 1; move >= 0; move--) {
    double pr = (move & 1) ? p2[move >> 1] : p1[move >> 1];
    for (int mask = 0; mask < 1 << 2 * n; mask++) {
      if (((mask >> move) & 1) == 0) {
        dpw[move][mask] = 1 - dpw[move + 1][mask] - dpd[move + 1][mask];
        dpd[move][mask] = dpd[move + 1][mask];
        continue;
      }
      double bestw = -1;
      double bestd = -1;
      for (int i = 1 - (move & 1); i < 2 * n; i += 2) {
        if (((mask >> i) & 1) == 1) {
          double curl = pr * dpw[move + 1][mask & ~(1 << i)] + (1 - pr) * dpw[move + 1][mask];
          double curd = pr * dpd[move + 1][mask & ~(1 << i)] + (1 - pr) * dpd[move + 1][mask];
          double curw = 1 - curl - curd;
          if (compare(bestw, curw) < 0 || compare(bestw, curw) == 0 && compare(bestd, curd) < 0) {
            bestw = curw;
            bestd = curd;
          }
        }
      }
      dpw[move][mask] = bestw;
      dpd[move][mask] = bestd;
    }
  }
  printf("%.17f %.17f\n", dpw[0][(1 << 2 * n) - 1], dpd[0][(1 << 2 * n) - 1]);
}
