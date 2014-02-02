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

const int N = 1234567;

int ss[N], ff[N], ne[N], he[N], de[N], cc[N];

int dfs(int v, int p, int cur) {
  int cn = 0;
  for (int e = he[v]; e >= 0; e = ne[e]) {
    if (ff[e] == p) continue;
    ++cn;
  }
  int ret = 0;
  for (int e = he[v]; e >= 0; e = ne[e]) {
    if (ff[e] == p) continue;
    ret += dfs(ff[e], v, cur);
  }
  cur -= ret + cn;
  return std::max(0, -cur);
}

int main() {
    int n = ni();
    for (int i = 0; i < n - 1; i++) {
      scanf("%d%d", ss + i, ff + i);
      --ss[i];
      --ff[i];
      ss[i + n - 1] = ff[i];
      ff[i + n - 1] = ss[i];
    }
    for (int i = 0; i < n; i++) he[i] = -1;
    for (int i = 0; i < n + n - 2; i++) {
      ne[i] = he[ss[i]];
      he[ss[i]] = i;
    }
    int l = -1;
    int r = n + 1;
    while (l < r - 1) {
      int mid = (l + r) >> 1;
      if (dfs(0, -1, mid) == 0) r = mid; else l = mid;
    }
    printf("%d\n", r);
}