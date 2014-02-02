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

int a[1111];

int main() {
  int n = ni();
  for (int i = 0; i < n; i++) {
    a[ni()]++;
  }
  int ans = 0;
  for (int i = 1; i <= 100; i++) {
    for (int j = i; j <= 100; j++) {
      for (int k = j; k <= 100; k++) {
        if (2 * k >= 3 * j && 2 * j >= 3 * i) {
          --a[i];
          --a[j];
          --a[k];
          if (a[i] >= 0 && a[j] >= 0 && a[k] >= 0) {
            ++ans;
            continue;
          }
          ++a[i];
          ++a[j];
          ++a[k];
        }
      }
    }
  }
  printf("%d\n", ans);
}
