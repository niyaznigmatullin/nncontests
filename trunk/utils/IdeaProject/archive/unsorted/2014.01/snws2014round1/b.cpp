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

const int N = 222;

int dp[N][N];
const int INF = 1 << 20;

void solve() {
  int n, b, p;
  scanf("%d%d%d", &n, &b, &p);
  for (int i = 0; i <= b; i++)
    for (int j = 0; j <= p; j++) dp[i][j] = -INF;
  dp[0][0] = 0;
  for (int i = 0; i < n; i++) {
    int v, x, y;
    scanf("%d%d%d", &v, &x, &y);
    for (int j = b - x; j >= 0; j--)
      for (int k = p - y; k >= 0; k--) {
        if (dp[j][k] != -INF && dp[j + x][k + y] < dp[j][k] + v) {
          dp[j + x][k + y] = dp[j][k] + v;
        }
      }
  }
  int ans = 0;
  for (int i = 0; i <= b; i++)
    for (int j = 0; j <= p; j++)
      if (ans < dp[i][j]) ans = dp[i][j];
  printf("%d\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}