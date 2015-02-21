#include <cstdio>

int const N = 1234;
int a[N];
int ac[N];
int ans[N];
int s[N];

unsigned int bs[N][N >> 5];
unsigned int cur[N >> 5];

int num(int c) {
  if (c >= '0' && c <= '9') return c - '0';
  return c - 'a' + 10;
}

int main() {
  freopen("filter.in", "r", stdin);
  freopen("filter.out", "w", stdout);
  int m, f;
  scanf("%d%d", &m, &f);
  for (int i = 0; i < f; i++) scanf("%d", a + i);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    int c = getchar();
    int len = 0;
    while (c <= 32) c = getchar();
    while (c > 32) {
      s[len++] = c;
      c = getchar();
    }
    for (int j = 0; j < m; j++) {
      if ((num(s[j >> 2]) >> (j & 3)) & 1) bs[i][j >> 5] |= (unsigned int) 1 << j;
    }
  }
  int q;
  scanf("%d", &q);
  for (int i = 0; i < q; i++) {
    int x;
    scanf("%d", &x);
    for (int j = 0; j <= (m >> 5); j++) cur[j] = 0;
    for (int j = 0; j < f; j++) {
      int bit = (long long) x * a[j] % m;
      cur[bit >> 5] |= (unsigned int) 1 << bit;
    }
    for (int j = 0; j < n; j++) {
      if (ans[j]) continue;
      bool has = true;
      for (int k = 0; k <= (m >> 5); k++) {
        if ((cur[k] & bs[j][k]) != cur[k]) {
          has = false;
          break;
        }
      }
      if (has) ans[j] = true;
    }
  }
  int cn = 0;
  for (int i = 0; i < n; i++) if (ans[i]) ac[cn++] = i;
  printf("%d", cn);
  for (int i = 0; i < cn; i++) printf(" %d", ac[i]);
  puts("");
}