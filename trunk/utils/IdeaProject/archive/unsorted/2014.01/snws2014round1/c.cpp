#include <cstdio>
#include <iostream>
#include <set>
#include <algorithm>

using namespace std;

const long long INF = (1LL << 60);
const int N = 777777;

int from[N], to[N], w[N], ne[N], he[N], fire[N];
long long d[N], dfire[N];

int main() {
  int n, m, k;
  scanf("%d%d%d", &n, &m, &k);
  for (int i = 0; i < m; i++) {
    scanf("%d%d%d", from + i, to + i, w + i);
    --from[i];
    --to[i];
    from[i + m] = to[i];
    to[i + m] = from[i];
    w[i + m] = w[i];
  }
  for (int i = 0; i < k; i++) {
    scanf("%d", fire + i);
    --fire[i];
  }
  for (int i = 0; i < n; i++) he[i] = -1;
  for (int i = 0; i < m + m; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  int start, finish;
  scanf("%d", &start);
  --start;
  finish = 0;
  set<pair<long long, int> > q;
  for (int i = 0; i < n; i++) d[i] = INF;
  for (int i = 0; i < k; i++) {
    d[fire[i]] = 0;
    q.insert(make_pair(d[fire[i]], fire[i]));
  }
  while (!q.empty()) {
    pair<long long, int> dv = *(q.begin());
    q.erase(q.begin());
    int v = dv.second;
    for (int e = he[v]; e >= 0; e = ne[e]) {
      if (d[to[e]] > d[v] + w[e]) {
        q.erase(make_pair(d[to[e]], to[e]));
        d[to[e]] = d[v] + w[e];
        q.insert(make_pair(d[to[e]], to[e]));
      }
    }
  }
  for (int i = 0; i < n; i++) dfire[i] = d[i];
  for (int i = 0; i < n; i++) d[i] = INF;
  d[start] = 0;
  q.insert(make_pair(d[start], start));
  while (!q.empty()) {
    pair<long long, int> dv = *(q.begin());
    q.erase(q.begin());
    int v = dv.second;
    for (int e = he[v]; e >= 0; e = ne[e]) {
      if (d[to[e]] > d[v] + w[e]) {
        q.erase(make_pair(d[to[e]], to[e]));
        d[to[e]] = d[v] + w[e];
        q.insert(make_pair(d[to[e]], to[e]));
      }
    }
  }
  if (d[finish] < dfire[finish]) {
    cout << d[finish] << endl;
  } else {
    puts("-1");
  }
}
