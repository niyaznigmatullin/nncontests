#include <cstdio>
#include <algorithm>

int a[123456];

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  std::sort(a, a + n);
  long long s;
  scanf("%lld", &s);
  long long cur = 0;
  long long ans = 0;
  int id = 0;
  while (cur < s && cur + 1 < a[n - 1]) {
    while (id + 1 < n && cur + 1 >= a[id + 1]) ++id;
    cur += a[id];
    ++ans;
  }
  if (cur < s) {
    long long dif = s - cur;
    dif = (dif + a[n - 1] - 1) / a[n - 1];
    ans += dif;
  }
  printf("%lld\n", ans);
}