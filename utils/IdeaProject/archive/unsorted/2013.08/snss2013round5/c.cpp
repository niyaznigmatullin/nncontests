#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

int ni() {
  int c = getchar();
  while (c != '-' && (c < '0' || c > '9')) c = getchar();
  int sg = 0;
  if (c == '-') {
    sg = 1;
    c = getchar();
  }
  int x = 0;
  while (c >= '0' && c <= '9') {
    x = x * 10 + c - '0';
    c = getchar();
  }
  return sg ? -x : x;
}

int x[41], y[41];

long long vmul(int i, int j) {
  return (long long) x[i] * y[j] - (long long) y[i] * x[j];
}

int main() {
  while (true) {
    for (int i = 0; i < 8; i += 2) {
      x[i] = ni() * 2;
      y[i] = ni() * 2;
    }
    for (int i = 1; i < 8; i += 2) {
      x[i] = (x[i - 1] + x[(i + 1) & 7]) / 2;
      y[i] = (y[i - 1] + y[(i + 1) & 7]) / 2;
    }
    if (x[0] == 0 && y[0] == 0 && x[1] == 0 && y[1] == 0) break;
    long long area = 0;
    for (int i = 0; i < 8; i++) {
      area += vmul(i, (i + 1) & 7);
    }
    if (area < 0) area = -area;
    long long a1 = 0;
    long long a2 = 1LL << 50;
    long long diff = a2 - a1;
    for (int i = 0; i < 8; i++) {
      for (int j = i + 3; j < 8; j++) {
        if (j - i > 5) continue;
        long long area1 = 0;
        for (int k = i + 1; k <= j; k++) area1 += vmul(k - 1, k);
        area1 += vmul(j, i);
        if (area1 < 0) area1 = -area1;
        long long area2 = area - area1;
        if (area1 > area2) std::swap(area1, area2);
        if (area2 - area1 < diff) {
          diff = area2 - area1;
          a1 = area1;
          a2 = area2;
        }
      }
    }
    printf("%.10f %.10f\n", a1 * .125, a2 * .125);
  }
}
