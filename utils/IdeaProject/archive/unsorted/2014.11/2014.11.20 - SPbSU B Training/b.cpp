#include <cstdio>

int const N = 1234567;

int from[N], to[N], he[N], ne[N], q[N], deg[N];

int main() {
  freopen("topsort.in", "r", stdin);
  freopen("topsort.out", "w", stdout);
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < m; i++) {
    scanf("%d%d", from + i, to + i);
    --from[i];
    --to[i];
    ++deg[to[i]];
  }
  for (int i = 0; i < n; i++) he[i] = -1;
  for (int i = 0; i < m; i++) {
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  int head = 0;
  int tail = 0;
  for (int i = 0; i < n; i++) if (deg[i] == 0) q[tail++] = i;
  while (head < tail) {
    int v = q[head++];
    for (int e = he[v]; e >= 0; e = ne[e]) {
      --deg[to[e]];
      if (deg[to[e]] == 0) q[tail++] = to[e];
    }
  }
  if (head != n) puts("-1"); else {
    for (int i = 0; i < n; i++) {
      if (i > 0) putchar(' ');
      printf("%d", q[i] + 1);
    }
    puts("");
  }
}