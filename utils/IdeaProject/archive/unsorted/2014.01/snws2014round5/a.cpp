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

const int N = 1234567;

int s[N], c[N], k[N], id[N];

bool compsc(int i, int j) {
  if (s[i] != s[j]) return s[i] < s[j];
  return c[i] < c[j];
}

int main() {
  int n;
  scanf("%d", &n);
  int mink = 1 << 30;
  for (int i = 0; i < n; i++) {
    scanf("%d%d%d", s + i, c + i, k + i);
    mink = std::min(mink, k[i]);
  }
  long long ans1 = 0;
  for (int i = 0; i < n; i++) {
    if (s[i] >= mink) {
      ans1 += 100LL * (k[i] - s[i] - 1) + 100 - c[i];
      s[i] = 0;
      c[i] = 0;
    }
    id[i] = i;
  }
  long long all = 0;
  for (int i = 0; i < n; i++) {
    all += 100LL * (k[i] - s[i] - 1) + 100 - c[i];
  }
  long long cur = 0;
  std::sort(id, id + n, compsc);
  long long ans = 1LL << 62;
  for (int it = 0; it < n; it++) {
    int i = id[it];
    all -= 100LL * (k[i] - s[i] - 1) + 100 - c[i];
    cur += 100LL * s[i] + c[i];
    ans = std::min(ans, (long long) n * (100LL * s[i] + c[i]) - cur + all);
  }
  cout << ans + ans1 << endl;
}
