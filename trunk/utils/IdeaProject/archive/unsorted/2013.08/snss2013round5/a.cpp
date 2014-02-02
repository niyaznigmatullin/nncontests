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

pair<int, int> a[1234567];

int main() {
  int n = ni();
  int k = ni();
  for (int i = 0; i < n; i++) {
    int x = ni();
    a[i] = make_pair(x, i);
  }
  std::sort(a, a + n);
  int ans = 0;
  for (int i = 0; i < n; ) {
    int j = i;
    while (j < n && a[i].first == a[j].first) ++j;
    while (i < j) {
      int l = i;
      int r = j;
      while (l < r - 1) {
        int mid = (l + r) >> 1;
        if (a[mid].second - a[i].second + 1 - k <= mid - i + 1) {
          l = mid;
        } else {
          r = mid;
        }
      }
      if (l - i + 1 > ans) ans = l - i + 1;
      ++i;
    }
  }
  printf("%d\n", ans);
}
