#include <cstdio>

int const N = 1234567;

int a[N], b[N], c[N];

int gcd(int a, int b) {
  return b == 0 ? a : gcd(b, a % b);
}

void solve() {
  int n, q;
  scanf("%d%d", &n, &q);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  for (int i = 0; i < n; i++) {
    b[i] = a[i];
    if (i > 0) b[i] = gcd(b[i], b[i - 1]);
  }
  for (int i = n - 1; i >= 0; i--) {
    c[i] = a[i];
    if (i + 1 < n) c[i] = gcd(c[i], c[i + 1]);
  }
  for (int cq = 0; cq < q; cq++) {
    int l, r;
    scanf("%d%d", &l, &r);
    l -= 2;
    int left = l < 0 ? 0 : b[l];
    int right = r >= n ? 0 : c[r];
    printf("%d\n", gcd(left, right));
  }
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}