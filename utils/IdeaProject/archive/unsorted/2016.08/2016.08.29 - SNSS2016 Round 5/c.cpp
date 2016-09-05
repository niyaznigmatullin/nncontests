#include <cstdio>

int a[1234567];

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
  }
  int num = 1 << 30;
  int den = 1;
  long long ans = 0;
  for (int i = n - 1; i >= 0; i--) {
    int x = a[i];
    int need = (int) (((long long) x * den + num - 1) / num);
    ans += need;
    num = x;
    den = need;
  }
  printf("%lld\n", ans);
}