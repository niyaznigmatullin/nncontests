#include <cstdio>

int main() {
  int n;
  scanf("%d", &n);
  int ans = 0;
  for (int i = 3; i <= n; i++) {
    n -= i;
    ++ans;
  }
  printf("%d\n", ans);
}