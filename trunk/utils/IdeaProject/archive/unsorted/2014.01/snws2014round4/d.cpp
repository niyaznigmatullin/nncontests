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

const int INF = 1 << 30;

const int N = 1234567;

int q[N];
int d[N], a[N];

int main() {
  int n, k;
  scanf("%d%d", &n, &k);
  for (int i = 0; i < k; i++) {
    scanf("%d", a + i);
  }
  int mm = 0;
  for (int i = 0; i < k; i++) if (mm < a[i]) mm = a[i];  
  for (int i = 0; i < mm; i++) d[i] = INF;
  d[0] = 0;
  int head = 0;
  int tail = 0;
  q[tail++] = 0;
  while (head < tail) {
    int v = q[head++];
    for (int i = 0; i < k; i++) {
      int u = (v + a[i]) % mm;
      int add = v + a[i] >= mm ? 0 : 1;
      if (d[u] > d[v] + add) {
        d[u] = d[v] + add;
        q[tail++] = u;
      }
    }
  }
  for (int i = 0; i < n; i++) {
    long long q;
    scanf("%lld", &q);
    if (d[q % mm] == INF) puts("-1"); else {
      long long ans = q / mm + d[q % mm];
      printf("%lld\n", ans);
    }
  }
}