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

long long d[40];

long long go(int n, int k) {
  if (n <= 0) return 0;
  long long ret = 0;
  if (n >= (1 << k)) {
    ret += d[k];
    n -= (1 << k);
    ret += (1 << (k + 1)) - 1;
  }
  ret += go(n, k - 1);
  return ret;
}

int main() {
  int l = ni();
  int r = ni();
  d[1] = 1;
  for (int i = 2; i < 40; i++) d[i] = d[i - 1] * 2 + (1LL << i) - 1;
  printf("%lld\n", go(r, 30) - go(l - 1, 30));
}
