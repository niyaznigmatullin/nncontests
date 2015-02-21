#include <bits/stdc++.h>

using namespace std;

int const INF = 1 << 30;
int const N = 223456;
int he[N], to[N], from[N], t[N], ne[N], d[N];

void solve() {
  int n, dd, c;
  scanf("%d%d%d", &n, &dd, &c);
  --c;
  std::fill(he, he + n, -1);
  for (int i = 0; i < dd; i++) {
    scanf("%d%d%d", to + i, from + i, t + i);
    --to[i];
    --from[i];
  }
  for (int i = 0; i < dd; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  set<pair<int, int> > q;
  q.insert(make_pair(0, c));
  std::fill(d, d + n, INF);
  d[c] = 0;
  int ans = 0;
  int cn = 0;
  while (!q.empty()) {
    int v = q.begin()->second;
    ans = q.begin()->first;
    q.erase(q.begin());
    ++cn;
    for (int e = he[v]; e >= 0; e = ne[e]) {
      if (d[to[e]] > d[v] + t[e]) {
        q.erase(make_pair(d[to[e]], to[e]));
        d[to[e]] = d[v] + t[e];
        q.insert(make_pair(d[to[e]], to[e]));
      }
    }
  }
  printf("%d %d\n", cn, ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
