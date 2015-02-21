#include <cstdio>
#include <cassert>
#include <algorithm>

int const M1 = 1000000007;
int const M2 = 999999937;
int const P = 33533;

int const N = 1234567;

long long h1[N], h2[N];
long long POW1[N], POW2[N];
int s[N];

long long hash(int l, int r) {
  long long ch1 = h1[r - 1];
  long long ch2 = h2[r - 1];
  if (l > 0) {
    ch1 -= POW1[r - l] * h1[l - 1] % M1;
    if (ch1 < 0) ch1 += M1;
    ch2 -= POW2[r - l] * h2[l - 1] % M2;
    if (ch2 < 0) ch2 += M2;
  }
  return (ch1 << 32) | ch2;
}

void solve() {
  int c = getchar();
  while (c <= 32) c = getchar();
  int n = 0;
  while (c > 32) {
    s[n++] = c;
    c = getchar();
  }
  long long ch1 = 0;
  long long ch2 = 0;
  for (int i = 0; i < n; i++) {
    ch1 = (ch1 * P + s[i]) % M1;
    ch2 = (ch2 * P + s[i]) % M2;
    h1[i] = ch1;
    h2[i] = ch2;
  }
  for (int k = 2; k <= n + 5; k++) {
    bool ok = true;
    for (long long len = k - 1; ok && len + 1 < n; len = (len + 1) * k - 1) {      
      for (int i = len + 1; i < n && i < (len + 1) * k - 1; i += len + 1) {
        int to = std::min<long long>(n, i + len);
        if (hash(0, to - i) != hash(i, to)) {
          ok = false;
          break;
        }
      }
    }
    if (ok) {
      printf("%d\n", k);
      return;
    }
  }
  assert(false);
}

int main() {
  POW1[0] = POW2[0] = 1;
  for (int i = 1; i < N; i++) {
    POW1[i] = POW1[i - 1] * P % M1;
    POW2[i] = POW2[i - 1] * P % M2;
  }
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();  
}
