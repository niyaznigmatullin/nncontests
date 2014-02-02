#include <cstdio>
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

int mul(int a, int b, int p) {
  return (long long) a * b % p;
}

int modpow(int a, int b, int p) {
  int ret = 1;
  while (b > 0) {
    if (b & 1) {
      ret = mul(ret, a, p);
    }
    a = mul(a, a, p);
    b >>= 1;
  }
  return ret;
}

int gen(int p) {
  for (int i = 2;; i++) {
    bool ok = true;
    for (int j = 2; j * j < p; j++) {
      if ((p - 1) % j != 0) continue;
      if (modpow(i, j, p) == 1) {
        ok = false;
        break;
      } 
      if (j * j != p - 1 && modpow(i, (p - 1) / j, p) == 1) {
        ok = false;
        break;
      }
    }
    if (ok) return i;
  }
}

int gcd(int a, int b, long long & x, long long & y) {
  if (b == 0) {
    x = 1;
    y = 0;
    return a;
  }
  long long nx, ny;
  int g = gcd(b, a % b, nx, ny);
  x = ny;
  y = nx - (a / b) * ny;
  return g;
}


int modinverse(int a, int mod) {
  long long x, y;
  int g = gcd(a, mod, x, y);
  assert(g == 1);
  x %= mod;
  if (x < 0) x += mod;
  return x;
}


int china(int a, int b, int p, int q) {
  int c = (b - a) % q;
  if (c < 0) c += q;
  c = mul(c, modinverse(p, q), q);
  return a + c * p;
}

int getlog(int x, int f, int p, int g, map<int, int> & z) {
  int cur = 1;
  for (int i = 0; i < f; i++) {
    int cc = mul(cur, x, p);
    if (z.find(cc) != z.end()) {
      int id = z[cc];
      return id * f - i;
    }
    cur = mul(cur, g, p);
  }
  assert(false);
}

int root(int c, int k, int p) {
  if (c == 0) return 0;
  int g = gen(p);
  int f = (int) sqrt((double) p) + 5;
  int cur = 1;
  for (int i = 0; i < f; i++) {
    cur = mul(cur, g, p);
  }
  map<int, int> z;
  int cur2 = 1;
  for (int i = 1; i <= f; i++) {
    cur2 = mul(cur2, cur, p);
    z[cur2] = i;
  }
  int k1 = getlog(c, f, p, g, z) % (p - 1);
  return modpow(g, mul(k1, modinverse(k, p - 1), p - 1), p);
}

void solve() {
  int n, k, c;
  scanf("%d%d%d", &n, &k, &c);
  int p = -1;
  for (int i = 2; i * i <= n; i++) {
    if (n % i == 0) {
      p = i;
      break;
    }
  }
  assert(p >= 0);
  int q = n / p;
  int a1 = root(c % p, k % (p - 1), p);
  int a2 = root(c % q, k % (q - 1), q);
  printf("%d\n", china(a1, a2, p, q));
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}