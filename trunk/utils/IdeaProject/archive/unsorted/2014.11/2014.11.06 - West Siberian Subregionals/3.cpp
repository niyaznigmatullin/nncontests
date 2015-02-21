#include <cstdio>

int a[12345];

int main() {
  int c = getchar();
  while (c <= 32) c = getchar();
  while (c > 32) {
    a[c]++;
    c = getchar();
  }
  while (c <= 32) c = getchar();
  while (c > 32) {
    a[c]--;
    c = getchar();
  }
  bool ans = true;
  for (int i = 0; i < 256; i++) if (a[i] != 0) ans = false;
  puts(ans ? "YES" : "NO");
}