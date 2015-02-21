#include <bits/stdc++.h>

using namespace std;

int const N = 1234567;
int a[N], b[N], c[N];

void solve() {
  int n, s, p;
  scanf("%d%d%d", &n, &s, &p);
  for (int i = 0; i < p; i++) scanf("%d", a + i);
  for (int i = 0; i < n - p; i++) scanf("%d", b + i);
  std::sort(a, a + p);
  std::sort(b, b + (n - p));
  int k;
  scanf("%d", &k);
  for (int i = 0; i < k; i++) scanf("%d", c + i);
  while (k < n) c[k++] = 0;
  std::sort(c, c + n);
  int l = 0;
  int r = p + 1;
  while (l < r - 1) {
    int mid = (l + r) >> 1;
    int cn = 0;
    int mn = 1 << 30;
    for (int i = 0; i < mid; i++) mn = std::min(mn, a[p - mid + i] + c[n - i - 1]);
    int cur = 0;
    for (int i = n - p - 1; i >= 0; i--) {
      if (b[i] + c[cur] > mn) ++cn; else ++cur;
    }
    if (s < mid + cn) r = mid; else l = mid;
  }
  printf("%d\n", l);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
