#include <cstdio>
#include <algorithm>

int const N = 1234567;

int a[N];

void answer(int f) {
  if ((f & 1) == 1) {
    puts("Chef");
  } else {
    puts("Mom");
  }
}

void solve() {
  int n, k;
  scanf("%d%d", &n, &k);
  for (int i = 0; i < k; i++) scanf("%d", a + i);
  std::sort(a, a + k);
  long long sum = 0;
  for (int i = 0; i < k; i++) {
    long long x = a[i];
    if (x * (x - 1) / 2 - sum < x) {
      answer(a[i]);
      return;
    }
    sum += x;
  }
  answer(((long long) n * (n + 1) / 2 - sum + 1) & 1);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}