#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
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

const double PI = acos(-1.);
const double EPS = 1e-8;
const int N = 1234567;
const int INF = 1 << 30;
double ax[N], ay[N], bx[N], by[N], ac[N], bc[N], ad[N], bd[N];
int p[N], id[N];

int compare(double a, double b) {
  double d = a - b;
  if (d < 0) d = -d;
  if (d / std::max((double) 1, std::max(std::abs(a), std::abs(b))) < EPS) return 0;
  return a < b ? -1 : 1;
}

void make(double * ax, double * ay, double * ac, double * ad, int n) {
  double sx = 0;
  double sy = 0;
  for (int i = 0; i < n; i++) {
    sx += ax[i];
    sy += ay[i];
  }
  sx /= n;
  sy /= n;
  for (int i = 0; i < n; i++) {
    ax[i] -= sx;
    ay[i] -= sy;
  }
  for (int i = 0; i < n; i++) {
    int j = (i + 1) % n;
    double d1 = (ax[i] * ax[i] + ay[i] * ay[i]);
    double d2 = (ax[j] * ax[j] + ay[j] * ay[j]);
    double dd = d2 < EPS ? -2 : d1 / d2;
    double ang = (compare(ax[i], 0) == 0 && compare(ay[i], 0) == 0) || (compare(ax[j], 0) == 0 && compare(ay[j], 0) == 0) ? 0 : atan2(ay[j], ax[j]) - atan2(ay[i], ax[i]);
    while (ang < 0) ang += 2 * PI;
    while (ang >= 2 * PI) ang -= 2 * PI;
    ac[i] = dd;
    ad[i] = ang;
  }
}

int main() {
  int n = ni();
  for (int i = 0; i < n; i++) {
    ax[i] = ni();
    ay[i] = ni();
  }
  for (int i = 0; i < n; i++) {
    bx[i] = ni();
    by[i] = ni();
    id[i] = i;
  }
  make(ax, ay, ac, ad, n);
  make(bx, by, bc, bd, n);
  int k = -1;
  p[0] = -1;
  for (int i = 1; i < n; i++) {
    while (k > 0 && (compare(ac[i], ac[k + 1]) != 0 || compare(ad[i], ad[k + 1]) != 0)) k = p[k];
    if (compare(ac[i], ac[k + 1]) == 0 && compare(ad[i], ad[k + 1]) == 0) ++k;
    p[i] = k;
  }
//  for (int i = 0; i < n; i++) printf("%.5f %.5f\n", ac[i], ad[i]);
//  for (int i = 0; i < n; i++) printf("%.5f %.5f\n", bc[i], bd[i]);
  int ans = INF;
  k = -1;
  for (int i = 0; i < 2 * n; i++) {
    double cc = bc[i % n];
    double cd = bd[i % n];
    while (k > 0 && (k + 1 >= n || (compare(cc, ac[k + 1]) != 0 && compare(cd, ad[k + 1]) != 0))) {
      k = p[k];
    }
    if (k + 1 < n && compare(cc, ac[k + 1]) == 0 && compare(cd, ad[k + 1]) == 0) ++k;
    if (k == n - 1) {
      ans = std::min(ans, id[i - n + 1]);
    }
  }
/*  for (int i = 1, j = n - 1; i < j; i++, j--) {
    std::swap(bx[i], bx[j]);
    std::swap(by[i], by[j]);
    std::swap(id[i], id[j]);
  }
  make(bx, by, bc, bd, n);
//  for (int i = 0; i < n; i++) printf("%.5f %.5f\n", bc[i], bd[i]);
  k = -1;
  for (int i = 0; i < 2 * n; i++) {
    double cc = bc[i % n];
    double cd = bd[i % n];
    while (k > 0 && (k + 1 >= n || (compare(cc, ac[k + 1]) != 0 && compare(cd, ad[k + 1]) != 0))) {
      k = p[k];
    }
    if (k + 1 < n && compare(cc, ac[k + 1]) == 0 && compare(cd, ad[k + 1]) == 0) ++k;
    if (k == n - 1) {
      ans = std::min(ans, id[i - n + 1]);
    }
  }*/
  if (ans == INF)
    puts("0");
  else
    printf("%d\n", ans + 1);
}