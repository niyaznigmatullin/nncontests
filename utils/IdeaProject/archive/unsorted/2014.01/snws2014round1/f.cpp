#include <cstdio>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <algorithm>
#include <cassert>
#include <memory.h>

using namespace std;
const int N = 1234567;
const int M = 77777;

int d[N], ans[N];

int main() {
  int n;
  scanf("%d", &n);
  int ac = 0;
  for (int i = 2; i <= M && i < n; i++) {
    int cn = 0;
    int m = n;
    while (m > 0) {
      d[cn++] = m % i;
      m /= i;
    }
    bool ok = true;
    for (int j = 0, k = cn - 1; j < k; j++, k--) {
      if (d[j] != d[k]) {
        ok = false;
        break;
      }
    }
    if (ok) {
      ans[ac++] = i;
    }
  }
  for (int i = 1; i <= M && i < n; i++) {
    if ((n - i) % i != 0) continue;
    int dd = (n - i) / i;
    if (dd <= i) continue;
    if (dd >= n) continue;
    int cn = 0;
    int m = n;
    while (m > 0) {
      d[cn++] = m % dd;
      m /= dd;
    }
    bool ok = true;
    for (int j = 0, k = cn - 1; j < k; j++, k--) {
      if (d[j] != d[k]) {
        ok = false;
        break;
      }
    }
    if (ok) {
      ans[ac++] = dd;
    }
  }
  std::sort(ans, ans + ac);
  ac = std::unique(ans, ans + ac) - ans;
  for (int i = 0; i < ac; i++) {
    if (i > 0) putchar(' ');
    printf("%d", ans[i]);
  }
  puts("");
}