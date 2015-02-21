#include <cstdio>
#include <algorithm>

int const N = 123456;

int d1[N], d2[N];

int main() {
  freopen("gcm.in", "r", stdin);
  freopen("gcm.out", "w", stdout);
  int a, b;
  scanf("%d%d", &a, &b);
  int cn1 = 0;
  for (int i = 1; i * i <= a; i++) {
    if (a % i != 0) continue;
    d1[cn1++] = i;
    if (i * i != a)
      d1[cn1++] = a / i;
  }
  int cn2 = 0;
  for (int i = 1; i * i <= b; i++) {
    if (b % i != 0) continue;
    d2[cn2++] = i;
    if (i * i != b)
      d2[cn2++] = b / i;
  }
  long long ansd = 1LL << 62;
  long long ans1 = -1;
  long long ans2 = -1;
  int gcd = std::__gcd(a, b);
  for (int i = 0; i < cn1; i++)
    for (int j = 0; j < cn2; j++) {
      long long f = (long long) d1[i] * d2[j];
      long long g = (long long) a * b / f;
      if (std::__gcd(f, g) != gcd) continue;
      if (f > g) std::swap(f, g);
      if (g - f < ansd) {
        ansd = g - f;
        ans1 = f;
        ans2 = g;
      }
    }
  printf("%I64d %I64d\n", ans1, ans2);
}