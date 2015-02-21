#include <cstdio>
#include <algorithm>

int const N = 123456;

int a[N], b[N], id[N];

bool byb(int i, int j) {
  return b[i] < b[j];
}

void solve() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d%d", a + i, b + i);
    id[i] = i;
  }
  std::sort(id, id + n, byb);
  int last = -(1 << 30);
  int ans = 0;
  for (int it = 0; it < n; it++) {
    int i = id[it];
    if (a[i] <= last) continue;
    last = b[i];
    ++ans;
  }
  printf("%d\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}