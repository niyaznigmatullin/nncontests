#include <cstdio>
#include <algorithm>
#include <cassert>

int a[1234567];

int main() {
  freopen("merlin.in", "r", stdin);
  freopen("merlin.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  std::sort(a, a + n);
  long long sum = 0;
  for (int i = 0; i < n; i++) sum += a[i];
  for (int k = 0; k < n; k++) {
    int v = a[n - k - 1];
    if (v <= sum / (n - k)) {
      printf("%d\n", k);
      return 0;
    }
  }
  assert(false);
}