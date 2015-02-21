#include <bits/stdc++.h>

int main() {
  int ans = 0;
  for (int i = 0; i < 100000000; i++) {
    int d = sqrt(i);
    while (d * d < i) ++d;
    while (d * d > i) --d;
    ans += d;
  }
  printf("%d\n", ans);
}
