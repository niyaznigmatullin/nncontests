#include <cstdio>
#include <algorithm>

int f[1234][1234];

int main() {
  int a, b, c, d;
  scanf("%d%d%d%d", &a, &b, &c, &d);
  int w = 5;
  int h = std::max(std::max(a, b), c) + 1 + d;
  for (int i = 0; i < h; i++)
    for (int j = 0; j < w; j++) f[i][j] = '.';
  for (int i = 0; i < a; i++)
    f[h - d - 1 - i - 1][0] = '#';
  for (int i = 0; i < b; i++)
    f[h - d - 1 - i - 1][2] = '#';
  for (int i = 0; i < c; i++)
    f[h - d - 1 - i - 1][4] = '#';
  for (int i = 0; i < d; i++) {
    f[h - d + i][2] = '#';
  }
  for (int i = 0; i < 5; i++) {
    f[h - d - 1][i] = '#';
  }
  for (int i = 0; i < h; i++) {
    for (int j = 0; j < w; j++) {
      putchar(f[i][j]);
    }
    puts("");
  }
}
