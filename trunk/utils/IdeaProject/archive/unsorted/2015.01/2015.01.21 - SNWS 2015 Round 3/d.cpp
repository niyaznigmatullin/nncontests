#include <bits/stdc++.h>

void solve() {
  int n;
  scanf("%d", &n);
  int left = 0;
  int right = 0;
  int last = 0;
  for (int i = 0; i < n; i++) {
    int x;
    scanf("%d", &x);
    int nleft = 1 << 30;
    int nright = 0;
    for (int j = -1; j <= 1; j += 2) {
      int nx = x * j;
      int d = std::abs(nx - last);
      if (d < 1 || d > 3) continue;
      int first = nx > last ? d : 0;
      int second = nx > last ? 0 : d;      
      nleft = std::min(nleft, std::max(left + first, left - last + second));
      nright = std::max(nright, std::max(right + first, right - last + second));
    }
    if (nleft > nright) {
      puts("0");
      return;
    }
    left = nleft;
    right = nright;
    last = x;
//    printf("%d %d\n", left, right);
  }
  printf("%d\n", (right - left + 1) * (last == 0 ? 1 : 2));
}


int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}