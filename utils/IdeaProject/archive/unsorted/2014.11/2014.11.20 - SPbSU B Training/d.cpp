#include <cstdio>
#include <algorithm>
#include <ctime>
#include <cstdlib>

int const N = 4000123;
int const MOD = 1743;

short a[N];

int go(int l, int r, int k) {
  if (r - l + 1 == 1) return a[l];
  int i = l;
  int j = r;
  int x = a[rand() % (r - l + 1) + l];
  while (i <= j) {
    while (a[i] < x) ++i;
    while (a[j] > x) --j;
    if (i <= j) {
      int t = a[i];
      a[i] = a[j];
      a[j] = t;
      ++i;
      --j;
    }
  }
  if (j - l + 1 >= k) return go(l, j, k); else
    if (i - l >= k) return a[k + l - 1]; else
      return go(i, r, k - (i - l));
}

int main() {
  srand(time(NULL));
  freopen("kth.in", "r", stdin);
  freopen("kth.out", "w", stdout);
  int n, k;
  scanf("%d%d", &n, &k);
  for (int i = 0; i < n; i++) {
    int j = (i + 1) % MOD;
    a[i] = (1577 + 1345 * j + 77 * j * j + 132 * j * j % MOD * j) % MOD;
  }
  printf("%d\n", go(0, n - 1, k));
}
