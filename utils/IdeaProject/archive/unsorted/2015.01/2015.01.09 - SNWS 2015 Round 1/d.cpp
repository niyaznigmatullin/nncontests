#include <bits/stdc++.h>


int main() {
  int n;
  scanf("%d", &n);
  if (n == 1) {
    puts("1");
    return 0;
  }
  double ans = 3.;
  for (int i = 3; i <= n; i++) ans = ans * i / (i - 1) + 1;
  printf("%.18lf\n", ans);
  return 0;
}