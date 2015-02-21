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

long long const INV2 = modpow(2, MOD - 2);

int a[1 << 21], b[1 << 21];

void fft(int * a, int l, int r) {
  if (l + 1 == r) return;
  int half = (r - l) >> 1;
  int mid = l + half;
  fft(a, l, mid);
  fft(a, mid, r);
  for (int i = l; i < mid; i++) {
    int x1 = a[i];
    int x2 = a[i + half];
    a[i] = (x1 + x2) % MOD;
    a[i + half] = (x1) % MOD;
  }
}

void fftrev(int * a, int l, int r) {
  if (l + 1 == r) return;
  int half = (r - l) >> 1;
  int mid = l + half;
  for (int i = l; i < mid; i++) {
    int x1 = a[i];
    int x2 = a[i + half];
    a[i] = x2;
    a[i + half] = (x1 - x2 + MOD) % MOD;
  }
  fftrev(a, l, mid);
  fftrev(a, mid, r);
}

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < 1 << n; i++) {
    scanf("%d", a + i);
  }
  for (int i = 0; i < 1 << n; i++) {
    scanf("%d", b + i);
  }
  fft(a, 0, 1 << n);
  fft(b, 0, 1 << n);
  for (int i = 0; i < 1 << n; i++) {
    a[i] = ((long long) a[i] * b[i] % MOD);
  }
  fftrev(a, 0, 1 << n);
  int invn = modpow(1 << n, MOD - 2);
  printf("%d\n", invn);
//  for (int i = 0; i < 1 << n; i++) a[i] = ((long long) a[i] * invn % MOD);
  for (int i = 0; i < 1 << n; i++) {
    if (i > 0) putchar(' ');
    printf("%d", a[i]);
  }
  puts("");
}
