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


const int N = 12345;

int f[N], nf[N], ff[200][N], s[N], mod;

void add(int & a, int b) {
  a += b;
  if (a >= mod) a -= mod;
}

int main() {
  int c = getchar();
  while (c <= 32) c = getchar();
  int n = 0;
  while (c > 32) {
    s[n++] = c;
    c = getchar();
  }
  scanf("%d", &mod);
  f[1] = 1 % mod;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j <= n; j++) {
      add(f[j], mod - ff[s[i]][j]);
      nf[j] = f[j];
    }
    for (int j = 0; j <= n; j++) { 
      add(nf[j + 1], ff[s[i]][j]);
    }
    for (int j = 0; j <= n; j++) {
      add(f[j], nf[j]);
    }
    for (int j = 0; j <= n; j++) ff[s[i]][j] = nf[j];
    for (int j = 0; j <= n; j++) printf("%d ", f[j]); puts("");
  }
  int ans = 0;
  for (int i = 0; i <= n; i++) add(ans, (long long) f[i] * i % mod * i % mod);
  printf("%d\n", (ans % mod + mod) % mod);
}
