#include <cstdio>
#include <ctime>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;

const int N = 1 << 20;
const int INF = 1 << 30;

int a[N], tmin[N * 2], n;
pair<int, int> b[N];

int getmin(int l, int r) {
  --r;
  l += N;
  r += N;
  int ret = INF;
  while (l <= r) {
    if ((l & 1) == 1) ret = std::min(ret, tmin[l++]);
    if ((r & 1) == 0) ret = std::min(ret, tmin[r--]);
    l >>= 1;
    r >>= 1;
  }
  return ret;
}

void setx(int x, int y) {
  x += N;
  tmin[x] = y;
  while (x > 1) {
    x >>= 1;
    tmin[x] = std::min(tmin[x * 2], tmin[x * 2 + 1]);
  }
}

int go(int l, int r) {
  if (l >= r) return 0;
  int pos = lower_bound(b, b + n, make_pair(getmin(l, r), -1))->second;
  int ans = std::min(go(l, pos), go(pos + 1, r)) + 1;
  return ans;
}

int main() {
  scanf("%d", &n);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  for (int i = 0; i < n; i++) a[i] = -a[i];
  for (int i = 0; i < n; i++) b[i] = make_pair(a[i], i);
  std::sort(b, b + n);
  for (int i = 0; i < n; i++) setx(i, a[i]);
  printf("%d\n", go(0, n));
}
