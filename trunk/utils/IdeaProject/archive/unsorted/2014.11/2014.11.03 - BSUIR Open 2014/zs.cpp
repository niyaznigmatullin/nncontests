#include <cstdio>

int const N = 1234567;

int a[N], b[N], c[N];

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < 1 << n; i++) scanf("%d", a + i);
  for (int i = 0; i < 1 << n; i++) scanf("%d", b + i);
  for (int i = 0; i < 1 << n; i++)
    for (int j = 0; j < 1 << n; j++) c[i | j] += a[i] * b[j];
  for (int i = 0; i < 1 << n; i++) printf("%d ", c[i]);
  puts("");
}