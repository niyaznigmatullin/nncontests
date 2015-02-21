#include <cstdio>

int main() {
  int c = getchar();
  while (c <= 32) c = getchar();
  int two = 0;
  int ans = 0;
  int n = 0;
  while (c > 32) {
    if (c == '1') ans += two; else two++;
    ++n;
    c = getchar();
  }
  int ans2 = two * (n - two) - ans;
  if (ans2 < ans) ans = ans2;
  printf("%d\n", ans);
}