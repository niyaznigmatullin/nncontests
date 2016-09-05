
#include <bits/stdc++.h>
int const N = 1234567;

int id[N];
int x[N], y[N];
void doit(int n, int *up, int *low) {
  for (int i = 0; i < n; i++) id[i] = i;
  std::sort(id, id + n, [](int i, int j) {
    if (x[i] != x[j]) return x[i] < x[j];
    return y[i] < y[j];
  });
  for (int it = 0; it < n; ) {
    int jt = it;
    while (jt < n && x[id[it]] == x[id[jt]]) {
      ++jt;
    }
    int cn = jt - it;
    int right = cn;
    while (it < jt) {
      --right;
      up[id[it]] = right;
      low[id[it]] = cn - right - 1;
      ++it;
    }
  }
}


int up[N], ri[N], low[N], le[N];
std::pair<int, int> f[N];
int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d%d", x + i, y + i);
  for (int i = 0; i < n; i++) {
    f[i] = {x[i], y[i]};
  }
  std::sort(f, f + n);
  n = (int) (std::unique(f, f + n) - f);
  for (int i = 0; i < n; i++) {
    x[i] = f[i].first;
    y[i] = f[i].second;
  }
  doit(n, up, low);
  for (int i = 0; i < n; i++) std::swap(x[i], y[i]);
  doit(n, ri, le);
  unsigned long long ans = 0;
  for (int i = 0; i < n; i++) {
    ans += (unsigned long long) 2 * up[i] * ri[i] * low[i] * le[i];
  }
  printf("%llu\n", ans);
}