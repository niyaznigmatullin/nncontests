#include <cstdio>
#include <algorithm>

int F[42];

int const N = 555;
int const MAXN = 333;

int w[N], h[N], edges[N][N];
int d[N][MAXN], was[N][MAXN];
int ce[N];

int main() {
  for (int i = 0; i < 3; i++) scanf("%d", F + i);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d%d", w + i, h + i);
    scanf("%d", ce + i);
    for (int j = 0; j < ce[i]; j++) {
      scanf("%d", edges[i] + j);
      --edges[i][j];
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < MAXN; j++) {
      if (i == 0) {
        if (j <= w[0]) d[i][j] = h[0]; else d[i][j] = -1;
      } else {
        d[i][j] = -1;
      }
    }
  }
  for (int mj = MAXN - 1; mj > 0; mj--) {
    while (true) {
      int mi = -1;
      for (int i = 0; i < n; i++) {
        int j = mj;
        if (was[i][j] || d[i][j] == -1) continue;
        if (j + 1 < MAXN && d[i][j] <= d[i][j + 1]) continue;
        if (mi < 0 || (d[mi][mj] < d[i][j])) {
          mi = i;
        }
      }
      if (mi < 0) break;
      was[mi][mj] = true;
      for (int e = 0; e < ce[mi]; e++) {
        int to = edges[mi][e];
        int nj = std::min(mj, w[to]);
        int nw = std::min(d[mi][mj], h[to]);
        if (d[to][nj] < nw) {
          d[to][nj] = nw;
        }
      }
    }
  }
  int ans = 0;
  int ansa = 0;
  int ansb = 0;
  int ansc = 0;
  for (int q = 1; q < MAXN; q++) {
    int r = d[n - 1][q];
    if (r <= 0) continue;
    for (int it1 = 0; it1 < 3; it1++) {
      for (int it2 = 0; it2 < 3; it2++) {
        if (it1 == it2) continue;
        int it3 = it1 ^ it2 ^ 3;
        int a = std::min(q, F[it1]);
        int b = std::min(r, F[it2]);
        int c = F[it3];
        if (ans < a * b * c) {
          ans = a * b * c;
          ansa = a;
          ansb = b;
          ansc = c;
        }
      }
    }
  }
  printf("%d %d %d\n", ansa, ansb, ansc);
}