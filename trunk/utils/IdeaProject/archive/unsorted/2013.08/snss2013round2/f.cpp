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
  int m = ni();
  int a = ni();
  int b = ni();
    
  if (n > m) {
    std::swap(n, m);
    std::swap(a, b);
  }
  if (n == 1) {
    printf("%d\n", 2 * (m - 1));
    return 0;
  }
  if ((n & 1) && (m & 1)) {
    printf("%d\n", n * m + 1);
  } else {
    printf("%d\n", n * m);
  }
}