#include <cstdio>
#include <ctime>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;

const int MOD = 1000000007;

const int N = 555555;

int fact[N], invf[N], inv[N], a[N], b[N], c[N];


int add(int a, int b) {
  a += b;
  if (a >= MOD) a -= MOD;
  return a;
}

int mul(int a, int b) {
  return (long long) a * b % MOD;
}

int read(int * a) {
  int c = getchar();
  while (c <= 32) c = getchar();
  int n = 0;
  while (c > 32) {
    a[n++] = c - '0';
    c = getchar();
  }
  return n;
}

int ch(int n, int k) {
  if (k < 0 || k > n) return 0;
  return mul(fact[n], mul(invf[k], invf[n - k]));
}

int main() {
  inv[1] = 1;
  for (int i = 2; i < N; i++) {
    inv[i] = mul(MOD - MOD / i, inv[MOD % i]);
  }
  fact[0] = invf[0] = 1;
  for (int i = 1; i < N; i++) {
    fact[i] = mul(fact[i - 1], i);
    invf[i] = mul(invf[i - 1], inv[i]);
  }
  int n = read(a);
  read(b);
  read(c);
  int ca = 0;
  int cb = 0;
  int cc = 0;
  int cn = 0;
  for (int i = 0; i < n; i++) {
    b[i] ^= a[i];
    c[i] ^= a[i];
    a[i] = 0;
    if (b[i] == 0 && c[i] == 0) {
      cn++;
    } else if (b[i] == 1 && c[i] == 0) {
      cb++;
    } else if (b[i] == 0 && c[i] == 1) {
      cc++;
    } else {
      ca++;
    }
  }
  if ((ca & 1) != (cb & 1) || (ca & 1) != (cc & 1)) {
    puts("0");
    return 0;
  }
  int mn = std::min(std::min(ca, cb), cc);
  int ans = 0;
  for (int i = -mn; i <= mn; i += 2) {
    int wa = ch(ca, (ca - std::abs(i)) / 2);
    int wb = ch(cb, (cb - std::abs(i)) / 2);
    int wc = ch(cc, (cc - std::abs(i)) / 2);
    ans = add(ans, mul(mul(wa, wb), wc));
  }
  for (int i = 0; i < cn; i++) ans = add(ans, ans);
  printf("%d\n", ans);
}