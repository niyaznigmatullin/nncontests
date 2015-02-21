#include <algorithm>
#include <cstdio>

int n;

int const N = 2222;
double D[2222 * 2222];
int h[N * N];
int wh[N * N];
int id[N];
int g[N];
int was[N];

inline double & d(int x, int y) {
  if (x < y) std::swap(x, y);
  return D[x * n + y];
}

inline int mp(int i, int j) {
  if (i < j) std::swap(i, j);
  return i * n + j;
}

int fr;

bool cmp(int x, int y) {
  return D[x] < D[y] || (D[x] == D[y] && x < y);
}

void up(int x) {
  while (x > 1) {
    int px = x >> 1;
    if (cmp(h[x], h[px])) {
      std::swap(h[x], h[px]);
      wh[h[x]] = x;
      wh[h[px]] = px;
      x = px;
    } else break;
  }
}

void down(int x) {
  while (true) {
    int mx = x;
    if (x * 2 <= fr && cmp(h[x * 2], h[mx])) mx = x * 2;
    if (x * 2 + 1 <= fr && cmp(h[x * 2 + 1], h[mx])) mx = x * 2 + 1;
    if (mx == x) break;
    std::swap(h[mx], h[x]);
    wh[h[mx]] = mx;
    wh[h[x]] = x;
    x = mx;
  }
}

void add(int x) {  
  wh[x] = ++fr;
  h[fr] = x;
  up(fr);
}

void rem(int x) {
  x = wh[x];
  std::swap(h[x], h[fr]);
  wh[h[x]] = x;
  wh[h[fr]] = fr;
  --fr;
  up(x);
  down(x);
}

int main() {
  fr = 0;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      d(i, j) = c - '0';
    }
  }
  for (int i = 0; i < n; i++)
    for (int j = 0; j < i; j++) {
      add(mp(i, j));
    }
  for (int i = 0; i < n; i++) g[i] = 1;
  for (int i = 0; i < n; i++) id[i] = i;
  for (int it = 0; it + 1 < n; it++) {
    int v = h[1];
    rem(v);
    int i = v / n;
    int j = v % n;
    double dd = d(i, j);
    for (int k = 0; k < n; k++) {
      if (was[k] || k == i || k == j) continue;
      rem(mp(i, k));
      rem(mp(j, k));
    }
    for (int k = 0; k < n; k++) {
      if (was[k] || k == i || k == j) continue;
      double nd = (d(i, k) * g[i] + d(j, k) * g[j]) / (g[i] + g[j]);
      d(j, k) = nd;
      add(mp(j, k));
    }
    was[i] = true;
    g[j] += g[i];
    printf("%d %d %.17lf\n", id[i] + 1, id[j] + 1, dd);
    id[j] = it + n;
//    for (auto & e : q) printf("%.17lf\n", d(e / n, e % n));
  }
}
