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

const int N = 1234567;
const long long INF = 1LL << 60;

int he[N], ne[4 * N], from[4 * N], to[4 * N], w[4 * N], cheese[N];
long long d[N];

int main() {
  int n, m, start, finish;
  scanf("%d%d%d%d", &n, &m, &start, &finish);  
  --start;
  --finish;
  for (int i = 0; i < n; i++) scanf("%d", cheese + i);
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", from + i, to + i, w + i);
    --from[i];
    --to[i];
    from[i + m] = to[i];
    to[i + m] = from[i];
    w[i + m] = w[i];
  } 
  for (int i = 0; i < n; i++) he[i] = -1;
  for (int i = 0; i < m + m; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  set<pair<long long, int> > q;
  q.insert(make_pair(INF, finish));
  for (int i = 0; i < n; i++) d[i] = -INF;
  d[finish] = INF;
  while (!q.empty()) {
    pair<long long, int> vs = *(q.rbegin());
    q.erase(vs);
    int v = vs.second;
    long long dv = vs.first;
    for (int e = he[v]; e >= 0; e = ne[e]) {
      int u = to[e];
      long long du = std::min<long long>(dv, w[e]) - cheese[u];
      if (du > d[u]) {
        q.erase(make_pair(d[u], u));
        d[u] = du;
        q.insert(make_pair(d[u], u));
      }
    }
  }  
  if (d[start] < 0) puts("-1"); else
    printf("%lld\n", d[start]);
}
