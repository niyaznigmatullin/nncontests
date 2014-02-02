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

int ans[N];

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    int x;
    scanf("%d", &x);
    int ac = 0;
    for (int j = 2; j * j <= x; j++) {
      if (x % j != 0) continue;
      int cn = 0;
      while (x % j == 0) {        
        x /= j;
        ++cn;
      }
      int z = j;
      for (int f = 1; f <= cn; f <<= 1) {
        if (f & cn) {
          ans[ac++] = z;
        }
        z = z * z;
      }
    }
    if (x > 1) ans[ac++] = x;
    std::sort(ans, ans + ac);
    for (int j = 0; j < ac; j++) {
      if (j > 0) putchar(' ');
      printf("%d", ans[j]);
    }
    puts("");
  }
}