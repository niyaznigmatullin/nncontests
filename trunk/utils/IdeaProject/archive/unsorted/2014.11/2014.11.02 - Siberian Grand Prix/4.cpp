#include <cstdio>

int const MOD = 1000000007;


int modpow(int a, int b) {
  int ret = 1;
  while (b > 0) {
    if (b & 1) ret = (long long) ret * a % MOD;
    a = (long long) a * a % MOD;
    b >>= 1;
  }
  return ret;
}

int s[7][1 << 21];
int ans[1 << 21];

void fft(int * a, int l, int r) {
  if (l + 1 == r) return;
  int half = (r - l) >> 1;
  int mid = l + half;
  fft(a, l, mid);
  fft(a, mid, r);
  for (int i = l; i < mid; i++) {
    int x1 = a[i];
    int x2 = a[i + half];
    a[i] = x1 - x2;
    if (a[i] < 0) a[i] += MOD;
    a[i + half] = x1 + x2;
    if (a[i + half] >= MOD) a[i + half] -= MOD;
  }
}

void fftrev(int * a, int l, int r) {
  if (l + 1 == r) return;
  int half = (r - l) >> 1;
  int mid = l + half;
  fftrev(a, l, mid);
  fftrev(a, mid, r);
  for (int i = l; i < mid; i++) {
    int x1 = a[i];
    int x2 = a[i + half];
    a[i] = x1 + x2;
    if (a[i] >= MOD) a[i] -= MOD;
    a[i + half] = x1 - x2;
    if (a[i + half] < 0) a[i + half] += MOD;
  }
}

int main() {
  int n, m, k;
  scanf("%d%d%d", &n, &m, &k);
  for (int i = 0; i < m; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    int f = 0;
    while (c > 32) {
      s[i][f++] = c == '1';
      c = getchar();
    }
    fft(s[i], 0, 1 << n);
  }
  for (int q = 0; q < k; q++) {
    int v, u;
    scanf("%d%d", &v, &u);
    int * f1 = s[v];
    int * f2 = s[u];
    for (int i = 0; i < 1 << n; i++) ans[i] = (long long) f1[i] * f2[i] % MOD;
    fftrev(ans, 0, 1 << n);
    int invn = modpow(1 << n, MOD - 2);
    for (int i = 0; i < 1 << n; i++) {
      ans[i] = (long long) ans[i] * invn % MOD;
      ans[i] = ans[i] > 0 ? 1 : 0;
    }
    for (int mask = (1 << n) - 1; mask >= 0; mask--) {
      for (int i = 0; !ans[mask] && i < n; i++) {
        if (((mask >> i) & 1) == 1) continue;
        ans[mask] |= ans[mask | (1 << i)];
      }
    }
    for (int i = 0; i < 1 << n; i++) putchar('0' + (int) ans[i]);
    puts("");
  }
}
