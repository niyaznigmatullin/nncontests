#include <cstdio>

int a[1234567];

int main() {
  freopen("right.in", "r", stdin);
  freopen("right.out", "w", stdout);
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  for (int i = 0; i < m; i++) {
    int x;
    scanf("%d", &x);
    int l = -1;
    int r = n;
    while (l < r - 1) {
      int mid = (l + r) >> 1;
      if (a[mid] > x) r = mid; else l = mid;
    }
    if (l < 0 || a[l] != x) l = -1;
    printf("%d\n", l + 1);
  }
}