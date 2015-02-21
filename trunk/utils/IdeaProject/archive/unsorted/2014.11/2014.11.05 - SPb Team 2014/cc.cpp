#include <cstdio>
#include <algorithm>
 
int const N = 12345;
 
int a[N], c[N];
 
int main() {
  freopen("conquest.in", "r", stdin);
  freopen("conquest.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d%d", a + i, c + i);
  for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++) if (a[i] > a[j] || (a[i] == a[j] && c[i] > c[j])) {
      std::swap(a[i], a[j]);
      std::swap(c[i], c[j]);
    }
  int eat = 0;
  int ans = 0;
  for (int i = n - 1; i >= 0; i--) {
    int cur = eat;
    for (int j = 0; j < i; j++) {
      cur += a[j];
    }
    for (int f = 0; f <= a[i]; f++) {
      if (cur + f > a[i] - f) {
        ans += f * c[i];
        eat += f;
        break;
      }
    }
  }
  printf("%d\n", ans);
}