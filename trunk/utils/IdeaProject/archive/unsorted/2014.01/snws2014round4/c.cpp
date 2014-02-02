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

const int N = 2234567;

int np[N], moe[N];

long long get(int n) {
  long long ans = 0;
  for (int g = 1; g <= n; g++) {
    ans += moe[g] * ((long long) (n / g) * (n / g));
  }
  return ans;
}

int main() {
  for (int i = 0; i < N; i++) moe[i] = 1;
  for (int i = 2; i < N; i++) {
    if (!np[i]) {
      for (int j = i; j < N; j += i) {
        np[j] = true;
        moe[j] = -moe[j];
      } 
    }
  }
  for (int i = 2; i * i < N; i++) {
    int f = i * i;
    for (int j = f; j < N; j += f) {
      moe[j] = 0;
    }
  }
  int n, k;
  scanf("%d%d", &n, &k);
  if ((k & 1) == 0) {
    puts("0");
    return 0;
  }
  k = (k - 1) / 2;
  if (k == 0) {
    puts("-1");
    return 0;
  }
  long long ans = get(n / k) - get(n / (k + 1));
  ans *= 2;
  if (n == k) ++ans;
  if (ans > 1000000000000LL) puts("-1"); else cout << ans << endl;
}
