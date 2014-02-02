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

int a[12345], dp[1234567];

void solve() {
  int n, m;
  scanf("%d%d", &n, &m);
  m = (m + 1) / 2;
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  int sum = 0;
  for (int i = 0; i < n; i++) sum += a[i];
  if (sum < m) {
    puts("-1");
    return;
  }
  int cs = 0;  
  for (int i = 0; i <= sum; i++) dp[i] = false;
  dp[0] = true;
  for (int i = 0; i < n; i++) {
    int x = a[i];
    for (int j = cs; j >= 0; j--) {
      if (!dp[j]) continue;
      dp[j + x] = true;
    }
    cs += x;
  }
  int x = m;
  while (!dp[x]) ++x;
  printf("%d\n", x);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}