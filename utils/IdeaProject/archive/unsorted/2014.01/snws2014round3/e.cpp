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
int a[N], b[N], c[N], id[N];

int compare(double a, double b) {
  double d = a - b;
  if (d < 0) d = -d;
  if (d < 1e-8) return 0;
  return a < b ? -1 : 1;
} 

bool byc(int i, int j) {
  return c[i] < c[j];
}

void solve() {
  int n;
  int r0;
  scanf("%d%d", &n, &r0);
  for (int i = 0; i < n; i++) {
    scanf("%d%d%d", a + i, b + i, c + i);
    id[i] = i;
  }
  std::sort(id, id + n, byc);
  double r = r0;
  for (int it = 0; it < n; it++) {
    int i = id[it];
    if (compare(r, c[i] * .5) <= 0) {
      puts("Fail");
      return;
    }
    double v = 4. / 3 * M_PI * r * r * r;
    v += 1. * a[i] * b[i] * c[i];
    r = pow(v * .75 / M_PI, 1. / 3);
  }
  puts("Success");
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}