#include <bits/stdc++.h>

int const N = 1234567;

int a[N], b[N], c[N], f[N];

int main() {
  using namespace std;
  int n;
  scanf("%d", &n);
  int mx = 0;
  for (int i = 0; i < n; i++) {
    scanf("%d", a + i);
    if (mx < a[i]) mx = a[i];
  }
  mx++;
  std::fill(f, f + mx, -1);
  for (int i = n - 1; i >= 0; i--) {
    c[i] = n;
    for (int j = a[i]; j < mx; j += a[i]) {
      if (f[j] >= 0) {
        c[i] = std::min(f[j], c[i]);
      }
    }    
    if (c[i] == n) c[i] = a[i]; else c[i] = a[c[i]];
    f[a[i]] = i;
  }
  std::fill(f, f + mx, -1);
  for (int i = 0; i < n; i++) b[n - i - 1] = c[i];
  std::reverse(a, a + n);
  for (int i = n - 1; i >= 0; i--) {
    c[i] = n;
    for (int j = a[i]; j < mx; j += a[i]) {
      if (f[j] >= 0) {
        c[i] = std::min(f[j], c[i]);
      }
    }    
    if (c[i] == n) c[i] = a[i]; else c[i] = a[c[i]];
    f[a[i]] = i;
  }
  long long ans = 0;
  for (int i = 0; i < n; i++) ans += (long long) b[i] * c[i];
  cout << ans << endl;
}
