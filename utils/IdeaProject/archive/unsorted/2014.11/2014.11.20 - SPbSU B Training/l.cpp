#include <cstdio>
#include <algorithm>

int const INF = 1 << 30;

int const N = 222222;

int wh[N], from[N], to[N], q[N], d[N], ne[N], he[N], inq[N], ans[N], last[N];

int main() {
  freopen("island2.in", "r", stdin);
  freopen("island2.out", "w", stdout);
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) he[i] = -1;
  for (int i = 0; i < n; i++) scanf("%d", wh + i);
  for (int i = 0; i < m; i++) {
    scanf("%d%d", from + i, to + i);
    --from[i];
    --to[i];
    from[i + m] = to[i];
    to[i + m] = from[i];
  }
  for (int i = 0; i < m + m; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  int head = 0;
  int tail = 0;
  q[tail++] = 0;
  for (int i = 0; i < n; i++) d[i] = INF;
  d[0] = 0;
  inq[0] = true;
  while (head < tail) {
    int v = q[head++];
    if (head == N) head = 0;
    inq[v] = false;
    for (int e = he[v]; e >= 0; e = ne[e]) {
      int cost = wh[v] == wh[to[e]] ? 0 : (v & 1) ? 2 : 1;
      if (d[to[e]] > d[v] + cost) {
        d[to[e]] = d[v] + cost;
        if (!inq[to[e]]) {
          inq[to[e]] = true;
          q[tail++] = to[e];
          if (tail == N) tail = 0;
        }
        last[to[e]] = e;
      }
    }
  }
  if (d[n - 1] == INF) {
    puts("impossible");
    return 0;
  }
  int ac = 0;
  for (int v = n - 1; v > 0; v = from[last[v]]) {
    ans[ac++] = v;
  }
  ans[ac++] = 0;
  std::reverse(ans, ans + ac);
  printf("%d %d\n", d[n - 1], ac);
  for (int i = 0; i < ac; i++) {
    if (i > 0) putchar(' ');
    printf("%d", ans[i] + 1);
  }
  puts("");
}