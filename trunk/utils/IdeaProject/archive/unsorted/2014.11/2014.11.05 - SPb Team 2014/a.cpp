#include <cstdio>

int main() {
  freopen("abcd.in", "r", stdin);
  freopen("abcd.out", "w", stdout);
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) {
    int n;
    scanf("%d", &n);
    int a = n / 1000 * 10 + n % 1000 / 100;
    n %= 100;
    a = a * a + n * n;
    if (a % 7 == 1) puts("YES"); else puts("NO");
  }
}