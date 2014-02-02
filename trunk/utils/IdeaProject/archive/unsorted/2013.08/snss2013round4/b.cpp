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

const int md = 1000000007;

int dp[12345][123];

int main() {
  int n = ni();
  dp[0][0] = 1;
  for (int i = 0; i < n; i++) {
    for (int m = 0; m < 1 << 4; m++) {
      for (int j = 0; j < 4; j++) {
        if (i == 0 && j == 0) continue;
        if (j == 0 && ((m >> 1) & 1) == 1) continue;
        if (j == 2 && ((m >> 3) & 1) == 1) continue;
        dp[i + 1][m | (1 << j)] += dp[i][m];
        dp[i + 1][m | (1 << j)] %= md;
      }
    }
  }
  printf("%d\n", dp[n][15]);
}
