#include <cstdio>

int f(int x) {
  int l = 0;
  int r = 40000;
  if (r > x + 1) r = x + 1;
  while (l < r - 1) {
    int mid = (l + r) >> 1;
    if (mid * mid <= x) l = mid; else r = mid;
  }
  return l;
}

int main() {
  int ans = 0;
  for (int i = 0; i < 100000000; i++) {
    ans += f(i);
  }
  printf("%d\n", ans);
}
