#include <bits/stdc++.h>

int const N = 1 << 20;

int d(int x) {
  int ans = 0;
  while (x > 0) {
    ans++;
    x /= 10;
  }
  return ans;
}

int main() {
  using namespace std;
  long long m;
  int n;
  cin >> m >> n;
  int mod = 1 << n;
  long long ans = 0;
  for (int i = 1; i < N && i <= m; i++) {
    int start = i - 6;
    if (start < 1) start = 1;
    int cur = 0;
    for (int j = start; j <= i; j++) {
      int f = d(j);
      for (int e = 0; e < f; e++) cur = cur * 10 & (mod - 1);
      cur = (cur + j) & (mod - 1);
    }
    if (cur == 0) ++ans;
  }
  if (m > N) {
    ans += (m - N) / mod + 1;
  }
  printf("%lld\n", ans);
}
