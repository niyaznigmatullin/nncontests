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

double f[55], g[55];

void solve() {
  int n;
  scanf("%d", &n);
  printf("%.6lf\n", f[n]);
}

int main() {
  f[1] = 1;
  g[1] = 1;
  double area = 1;
  for (int i = 2; i < 55; i++) {
    area *= 2;
    f[i] = f[i - 1] + area * .75;
    g[i] = area;
    f[i] -= g[i - 7] * .5;
  }
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}