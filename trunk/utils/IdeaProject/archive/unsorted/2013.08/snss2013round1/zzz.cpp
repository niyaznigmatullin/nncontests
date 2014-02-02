#include <cstdio>

const int N = 1234567;
const int INF = 1 << 30;
int ne[N], he[N], flow[N], w[N], cap[N], ss[N], ff[N], cv, ce, rev[N], lst[N], q[N], d[N], was[N], phi[N];
int a[333][333];

int adde(int v, int u, int cc, int pp) {
  ss[ce] = v;
  ff[ce] = u;
  cap[ce] = cc;
  flow[ce] = 0;
  w[ce] = pp;
  return ce++;
}

int addedge(int v, int u, int cc, int pp) {
  int e1 = adde(v, u, cc, pp);
  int e2 = adde(u, v, 0, -pp);
  rev[e1] = e2;
  rev[e2] = e1;
  return e1;
}

int mincost(int src, int tar) {
  for (int i = 0; i < cv; i++) he[i] = -1;
  for (int i = 0; i < ce; i++) {
    ne[i] = he[ss[i]];
    he[ss[i]] = i;
  }
  bool dd = true;
  while (dd) {
    dd = false;
    for (int i = 0; i < ce; i++) {
      if (cap[i] > flow[i] && phi[ff[i]] > phi[ss[i]] + w[i]) {
        dd = true;
        phi[ff[i]] = phi[ss[i]] + w[i];
      }
    }
  }
  int ans = 0;
  while (true) {
    for (int i = 0; i < cv; i++) d[i] = INF;
    for (int i = 0; i < cv; i++) was[i] = false;
    d[src] = 0;
    while (true) {
      int mv = -1;
      for (int i = 0; i < cv; i++) {
        if (was[i] || d[i] == INF) continue;
        if (mv < 0 || d[i] < d[mv]) mv = i;
      }
      if (mv < 0) break;
      was[mv] = true;
      for (int e = he[mv]; e >= 0; e = ne[e]) {
        if (cap[e] > flow[e] && d[ff[e]] > d[ss[e]] + phi[ss[e]] - phi[ff[e]] + w[e]) {
          d[ff[e]] = d[ss[e]] + phi[ss[e]] - phi[ff[e]] + w[e];
          lst[ff[e]] = e;
        }
      }
    }
    if (d[tar] == INF) break;
    ans += d[tar] + phi[tar] - phi[src];
    for (int v = tar; v != src; v = ss[lst[v]]) {
      flow[lst[v]]++;
      flow[rev[lst[v]]]--;
    }
    for (int i = 0; i < cv; i++) phi[i] += d[i];
  }
  return ans;
}

int main() {
  freopen("assignment.in", "r", stdin);
  freopen("assignment.out", "w", stdout);
  int n;
  scanf("%d", &n);
  cv = 2 + n + n;
  ce = 0;
  for (int i = 0; i < n; i++) {
    addedge(n + n, i, 1, 0);
    addedge(i + n, n + n + 1, 1, 0);
    for (int j = 0; j < n; j++) {
      int x;
      scanf("%d", &x);
      a[i][j] = addedge(i, j + n, 1, x);
    }
  }
  printf("%d\n", mincost(n + n, n + n + 1));
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (flow[a[i][j]] > 0) printf("%d %d\n", i + 1, j + 1);
    }
  }
}