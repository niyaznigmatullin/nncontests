#include <cstdio>
#include <algorithm>

long long d[123456];

int main() {
  long long n;
  scanf("%lld", &n);
  int cn = 0;
  for (long long i = 2; i * i <= n; i++) {
    if (n % i != 0) continue;
    d[cn++] = i;
    if (i * i != n) d[cn++] = n / i;
  }
  long long ans = n;
  for (int i = 0; i < cn; i++) {
    long long f = n;
    while (f % d[i] == 0) f /= d[i];
    if (f < d[i]) {
      ans = std::min(ans, d[i]);
    }
  }
  printf("%lld\n", ans);
}