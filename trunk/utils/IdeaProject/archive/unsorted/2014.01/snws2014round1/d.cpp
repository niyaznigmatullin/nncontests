#include <cstdio>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;

const int N = 555;

int xx[N], yy[N], id[N];
int dp[N][N], w[N][N];

bool compang(int i, int j) {
  if (xx[i] > 0 && xx[j] < 0) return true;
  if (xx[i] < 0 && xx[j] > 0) return false;
  long long vmul = 1LL * xx[i] * yy[j] - 1LL * xx[j] * yy[i];
  return vmul > 0;
}

long long area(long long x1, long long y1, long long x2, long long y2, long long x3, long long y3) {
  x2 -= x1;
  y2 -= y1;
  x3 -= x1;
  y3 -= y1;
  long long ret = x2 * y3 - y2 * x3;
  if (ret < 0) ret = -ret;
  return ret;
}

long long turn(long long x1, long long y1, long long x2, long long y2, long long x3, long long y3) {
  x2 -= x1;
  y2 -= y1;
  x3 -= x1;
  y3 -= y1;
  return x2 * y3 - y2 * x3;  
}

bool intr(int x1, int y1, int x2, int y2, int x3, int y3, int x0, int y0) {
  long long a = area(x1, y1, x2, y2, x3, y3);
  long long a1 = area(x1, y1, x2, y2, x0, y0);
  long long a2 = area(x1, y1, x0, y0, x3, y3);
  long long a3 = area(x0, y0, x2, y2, x3, y3);
  return a == a1 + a2 + a3;
}

int main() {
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n + m; i++) 
    scanf("%d%d", xx + i, yy + i);
  if (n + m == 1) {
    if (xx[0] == 0 && yy[0] == 0) {
      if (n == 1) puts("1"); else puts("-1");
      return 0;
    }
    if (n == 1) puts("1"); else puts("0");
    return 0;
  }
  for (int i = 0; i < n + m; i++) id[i] = i;
  sort(id, id + n + m, compang);
  for (int it = 0; it < n + m; it++) 
    for (int jt = it + 1; jt < n + m; jt++) {
      int i = id[it];
      int j = id[jt];
      for (int k = 0; k < n + m; k++) {
        if (i != k && j != k && intr(0, 0, xx[i], yy[i], xx[j], yy[j], xx[k], yy[k])) {
          w[it][jt] += k < n ? 1 : -1;          
        }
      }
      w[it][jt] += j < n ? 1 : -1;
    }
  int ans = 0;
  if (n > 0) ans = 1;
  for (int i = 0; i < n + m; i++)
    for (int j = i + 1; j < n + m; j++) {
      dp[i][j] = w[i][j] + (id[i] < n ? 1 : -1);
    }
  for (int i = 0; i < n + m; i++) {
    for (int j = i + 1; j < n + m; j++) {
      for (int k = 0; k < i; k++) {
        if (turn(xx[id[k]], yy[id[k]], xx[id[i]], yy[id[i]], xx[id[j]], yy[id[j]]) < 0) continue;
        if (dp[i][j] < dp[k][i] + w[i][j]) {
          dp[i][j] = dp[k][i] + w[i][j];
        }
      }
      if (ans < dp[i][j]) ans = dp[i][j];
    }
  }
  printf("%d\n", ans);  
}