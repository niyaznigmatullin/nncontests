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

const int N = 123456;

int xx[N], yy[N], ss[N];
double r[N];

double dist(double x, double y) {
  return x * x + y * y;
}

void solve() {
  int b, n;
  scanf("%d%d", &n, &b);
  int xj, yj;
  scanf("%d%d", &xj, &yj);
  for (int i = 0; i < n; i++) {
    scanf("%d%d%d", xx + i, yy + i, ss + i);
  }
  double sum = 0;
  for (int i = 0; i < n; i++) {
    r[i] = ss[i] / dist(xx[i] - xj, yy[i] - yj);
    sum += r[i];
  }
  for (int i = 0; i < n; i++) {
    if (r[i] > 6 * (b + sum - r[i])) {
      printf("%d\n", i + 1);
      return;
    }
  }
  puts("0");
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
