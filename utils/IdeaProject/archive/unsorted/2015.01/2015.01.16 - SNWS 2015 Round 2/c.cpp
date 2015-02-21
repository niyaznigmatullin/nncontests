#include <bits/stdc++.h>

int const N = 1234;
int const K = 10;

int a[N][K], b[K][N], c[N][K], tmp[K][K], ans[K][K];

bool solve() {
  int n, k;
  scanf("%d%d", &n, &k);
  if (n == 0 && k == 0) return false;
  for (int i = 0; i < n; i++)
    for (int j = 0; j < k; j++) scanf("%d", a[i] + j);
  for (int i = 0; i < k; i++)
    for (int j = 0; j < n; j++) scanf("%d", b[i] + j);
  for (int i = 0; i < k; i++)
    for (int j = 0; j < k; j++) {
      c[i][j] = 0;
      for (int e = 0; e < n; e++) {
        c[i][j] = (c[i][j] + b[i][e] * a[e][j]) % 6;
      }
    }
  int pp = n * n - 1;
  for (int i = 0; i < k; i++) ans[i][i] = 1;
  while (pp > 0) {
    if (pp & 1) {
      for (int i = 0; i < k; i++)
        for (int j = 0; j < k; j++) {
          tmp[i][j] = 0;
          for (int e = 0; e < k; e++) tmp[i][j] = (tmp[i][j] + ans[i][e] * c[e][j]) % 6;
        }
      for (int i = 0; i < k; i++)
        for (int j = 0; j < k; j++) {
          ans[i][j] = tmp[i][j];
        }
    }
    for (int i = 0; i < k; i++)
      for (int j = 0; j < k; j++) {
        tmp[i][j] = 0;
        for (int e = 0; e < k; e++) tmp[i][j] = (tmp[i][j] + c[i][e] * c[e][j]) % 6;
      }
    for (int i = 0; i < k; i++)
      for (int j = 0; j < k; j++) {
        c[i][j] = tmp[i][j];
      }
    pp >>= 1;
  }
  for (int i = 0; i < n; i++)
    for (int j = 0; j < k; j++) {
      c[i][j] = 0;
      for (int e = 0; e < k; e++) c[i][j] = (c[i][j] + a[i][e] * ans[e][j]) % 6;
    }
  int sum = 0;
  for (int i = 0; i < n; i++)
    for (int j = 0; j < n; j++) {
      int cur = 0;
      for (int e = 0; e < k; e++) {
        cur = (cur + c[i][e] * b[e][j]) % 6;
      }
      sum += cur;
    }
  printf("%d\n", sum);
  return true;
}

int main() {
  while (solve());
}
