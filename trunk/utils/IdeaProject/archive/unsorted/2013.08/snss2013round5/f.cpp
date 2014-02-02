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

const int INF = ~(1 << 31);

int head[1234567], a[1234567], d1[1234567], g[1234567];

void solve() {
  int n = ni();
  int have = 0;  
  for (int i = 0; i < n; i++) {
    head[i] = have;
    int k = ni();
    for (int j = 0; j < k; j++) {
      a[have + j] = ni();
      g[j + have] = i;
    }
    std::sort(a + have, a + have + k);
    have += k;
  }
  head[n] = have;
  for (int i = 0; i < have; i++) {
//    printf("      %d\n", a[i]);
    if (g[i] == 0) d1[i] = a[i]; else {
      int l = head[g[i] - 1] - 1;
      int r = head[g[i]];
      while (l < r - 1) {
        int mid = (l + r) >> 1;
        if (a[mid] > a[i]) r = mid; else l = mid;
      }
      if (l < head[g[i] - 1]) d1[i] = INF; else
        d1[i] = d1[l];
    }
//    printf("%d\n", d1[i]);
  }
/*  for (int i = have - 1; i >= 0; i--) {
    if (g[i] == n - 1) d2[i] = a[i]; else {
      int l = head[g[i] + 1] - 1;
      int r = head[g[i] + 2];
      while (l < r - 1) {
        int mid = (l + r) >> 1;
        if (a[mid] < a[i]) l = mid; else r = mid;
      }
      if (r >= head[g[i] + 2]) d2[i] = -INF; else d2[i] = d2[r];
    }
  }*/
  int ans = INF;
  for (int i = 0; i < have; i++) {
    if (g[i] != n - 1) continue;
    if (d1[i] == INF) continue;
    if (ans > a[i] - d1[i]) ans = a[i] - d1[i];
//    printf("   %d\n", a[i] - d1[i]);
  }
  if (ans == INF) puts("-1"); else
  printf("%d\n", ans);
}

int main() {
  int t = ni();
  for (int i = 0; i < t; i++) solve();
}
