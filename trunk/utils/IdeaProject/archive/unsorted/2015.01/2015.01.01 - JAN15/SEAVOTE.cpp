#include <cstdio>

int const N = 1234567;

int a[N];

void solve() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  int sum = 0;
  int zeros = 0;
  for (int i = 0; i < n; i++) {
    sum += a[i];
    if (a[i] == 0) ++zeros;
  }
  if (sum < 100) {
    puts("NO");
    return;
  }
  int left = sum - 100;
  if (left >= n - zeros) {
    puts("NO");
  } else {
    puts("YES");
  }
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}