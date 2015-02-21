#include <cstdio>

int const N = 12345;
int a[N], b[N];

int main() {
  freopen("knockout.in", "r", stdin);
  freopen("knockout.out", "w", stdout);
  int n, m, d;
  scanf("%d%d%d", &n, &m, &d);
  for (int i = 0; i < n; i++) {
    scanf("%d%d", a + i, b + i);
  }
  for (int i = 0; i < m; i++) {
    int x, y, nt;
    scanf("%d%d%d", &x, &y, &nt);
    int ans = 0;
    for (int j = 0; j < n; j++) {
      int t = nt;
      int pos;
      if (a[j] == b[j]) {
        pos = a[j];
      } else if (a[j] > b[j]) {
        int dif = a[j] - b[j];
        t %= 2 * dif;
        if (t >= dif) {
          pos = b[j] + t % dif;
        } else pos = a[j] - t;
      } else {
        int dif = b[j] - a[j];
        t %= 2 * dif;
        if (t >= dif) pos = b[j] - t % dif; else
          pos = a[j] + t;
      }
//      printf("%d\n", pos);
      if (pos >= x && pos <= y) ++ans;
    }
//    puts("");
    printf("%d\n", ans);
  }
}