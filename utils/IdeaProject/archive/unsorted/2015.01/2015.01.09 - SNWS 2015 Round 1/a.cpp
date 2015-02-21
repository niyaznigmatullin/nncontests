#include <bits/stdc++.h>

int const INF = 1 << 30;
int const C = 11;
int const S = C * 111;
int dp1[C][S], dp2[C][S];

int f(int x) {
  if (x >= 85) return 8;
  if (x >= 80) return 7;
  if (x >= 75) return 6;
  if (x >= 70) return 5;
  return 4;
}

void solve() {
  int a, b;
  scanf("%d%d", &a, &b);
  printf("%.17lf %.17lf\n", dp1[b][a * b] * .5 / b, dp2[b][a * b] * .5 / b);
}

int main() {
  for (int i = 0; i < C; i++) {
    std::fill(dp1[i], dp1[i] + S, INF);
    std::fill(dp2[i], dp2[i] + S, -INF);
  } 
  dp1[0][0] = dp2[0][0] = 0;
  for (int i = 1; i < C; i++) {
    for (int j = 0; j < S; j++) {
      for (int last = 60; last <= 100; last++) {
        if (j - last < 0) continue;
        if (dp1[i - 1][j - last] != INF && dp1[i - 1][j - last] + f(last) < dp1[i][j]) {
          dp1[i][j] = dp1[i - 1][j - last] + f(last);
        }
        if (dp2[i - 1][j - last] != -INF && dp2[i - 1][j - last] + f(last) > dp2[i][j]) {
          dp2[i][j] = dp2[i - 1][j - last] + f(last);
        }
      }
    }
  }
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}