#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

int ni() {
  int c = getchar();
  while (c != '-' && (c < '0' || c > '9')) c = getchar();
  int sg = 0;
  if (c == '-') {
    sg = 1;
    c = getchar();
  }
  int x = 0;
  while (c >= '0' && c <= '9') {
    x = x * 10 + c - '0';
    c = getchar();
  }
  return sg ? -x : x;
}

int a[1234567];

int main() {
  int m = ni();
  int n = ni();
  for (int i = 0; i < n; i++) a[i] = ni();
  int id = -1;
  for (int i = 0; i < n; i++) {
    int cur = 0;
    for (int j = 3; j >= 0; j--) {
      if (i - j >= 0) cur += a[i - j];
    }
    if (cur > m) {
      id = i;
      break;
    }
  }
  if (id < 0) printf("%d\n", n - 1); else {
    printf("%d\n", std::max(0, id - 1));
  }
}