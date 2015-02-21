#include <cstdio>
#include <algorithm>
#include <utility>
#include <iostream>

using namespace std;

int const N = 123456;

pair<int, int> a[N], b[N];

int main() {
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    a[i] = make_pair(x, y);
  }
  for (int i = 0; i < m; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
    b[i] = make_pair(x, y);
  }
  std::sort(a, a + n);
  std::sort(b, b + m);
  long long ans1 = 0;
  long long ans2 = 0;
  long long sum1 = 0;
  for (int i = 0; i < n; i++) sum1 += a[i].second;
  long long sum2 = 0;
  for (int i = 0; i < m; i++) sum2 += b[i].second;
  long long ans3 = sum1 * sum2;
  for (int i = 0, j = 0; i < n; i++) {
    while (j < m && b[j].first <= a[i].first) sum2 -= b[j++].second;
    ans1 += (long long) sum2 * a[i].second;
  }
  for (int i = 0, j = 0; i < m; i++) {
    while (j < n && a[j].first <= b[i].first) sum1 -= a[j++].second;
    ans2 += (long long) sum1 * b[i].second;
  }
  ans3 -= ans1;
  ans3 -= ans2;
  cout << ans2 << ' ' << ans3 << ' ' << ans1 << endl;
}
