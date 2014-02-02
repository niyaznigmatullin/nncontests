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

double dp[1 << 20], dp2[1 << 20], a[42], b[42][42];

bool solve() {
  int n;
  cin >> n;
  if (n == 0) return false;
  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> b[i][j];
    }
  }
  for (int i = 0; i < 1 << n; i++) dp[i] = 1e20, dp2[i] = -1e20;
  dp2[0] = dp[0] = 0;
  for (int mask = 0; mask < 1 << n; mask++) {
    int id = -1;
    for (int i = 0; i < n; i++) {
      if (((mask >> i) & 1) == 0) {
        id = i;
        break;
      }
    }
    for (int j = id + 1; j < n; j++) {
      if (((mask >> j) & 1) == 1) continue;
      if (dp[mask] != 1e20) {
        double & ne = dp[mask | (1 << id) | (1 << j)];
        double cur = dp[mask] + a[j] * b[j][id] + a[id] * b[id][j];
        if (ne > cur) ne = cur;
      }
      if (dp2[mask] != -1e20) {
        double & ne = dp2[mask | (1 << id) | (1 << j)];
        double cur = dp2[mask] + a[j] * b[j][id] + a[id] * b[id][j];
        if (ne < cur) ne = cur;
      }
    }
//    printf("%d %.17lf %.17lf\n", mask, dp[mask], dp2[mask]);
  }
  printf("%.17f %.17f\n", dp[(1 << n) - 1], dp2[(1 << n) - 1]);
  return true;
}

int main() {
  while (solve());
}
