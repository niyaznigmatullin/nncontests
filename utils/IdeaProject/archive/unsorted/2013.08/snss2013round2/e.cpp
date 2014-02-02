#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <iostream>
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

int main() {
  int n = ni();
  long long ans = 0;
  while (n > 0) {
    int dd = -1;
    for (int j = 2; j * j <= n; j++) {
      if (n % j == 0) {
        dd = j;
        break;
      }
    }
    if (dd < 0) dd = n;
    n -= n / dd;
    ans += dd - 1;
  }
  printf("%lld\n", ans);
}
