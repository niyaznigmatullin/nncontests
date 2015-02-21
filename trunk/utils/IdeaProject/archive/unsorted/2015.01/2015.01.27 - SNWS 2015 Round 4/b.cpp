#include <bits/stdc++.h>

using namespace std;

int const N = 123456;

pair<int, int> a[N];

int main() {
  int m, s;
  scanf("%d%d", &m, &s);
  --s;
  int n = 0;
  int curMoney = 0;
  for (int i = 0; i < m; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    if (i == s) {
      curMoney = x;
    } else {
      a[n++] = {y, x};
    }
  }
  std::sort(a, a + n);
  int towns = 1;
  for (int i = 0; i < n; i++) {
    if (a[i].first <= curMoney && a[i].first <= a[i].second) {
      curMoney += a[i].second - a[i].first;
      ++towns;
    }
  }
  printf("%d\n%d\n", curMoney, towns);
}