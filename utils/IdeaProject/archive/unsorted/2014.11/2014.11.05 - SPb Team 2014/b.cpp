#include <cstdio>

int a[123456];

int main() {
  freopen("chess.in", "r", stdin);
  freopen("chess.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
  }
  int k = 0;
  while (k < n && a[k] >= k + 1) ++k;
  printf("%d\n", k);
  for (int i = 0; i < k; i++) printf("%d %d\n", i + 1, i + 1);
}