#include <bits/stdc++.h>

void solve() {
  int n, k;
  scanf("%d%d", &n, &k);
  long long cur = 1 % n;
  for (int it = 0; it < 100; it++) {
    if (cur == 0) {
      printf("%d\n", it);
      return;
    }
    cur = cur * k % n;
  }
  puts("-1");
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}