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

int a[12345];

int main() {
  int n = ni();
  for (int i = 0; i < n; i++) a[i] = ni();
  int ans = 0;
  for (int i = 0; i < n; i++) {
    int mi = 1 << 30;
    for (int j = i; j < n; j++) {
      if (mi > a[j]) mi = a[j];
      if (mi * (j - i + 1) > ans) ans = mi * (j - i + 1);
    }
  }
  printf("%d\n", ans);
}
