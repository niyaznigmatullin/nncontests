#include <bits/stdc++.h>

using namespace std;

int const N = 22222;

vector<int> edges[N];
int deg[N];
int q[N];
int ans[N];
unsigned int bs[N][N >> 5];
unsigned int color[N >> 5];
int main() {
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < m; i++) {
    int v, u;
    scanf("%d%d", &v, &u);
    --v;
    --u;
    edges[v].push_back(u);
    deg[u]++;
  }
  int head = 0;
  int tail = 0;
  for (int i = 0; i < n; i++) if (deg[i] == 0) q[tail++] = i;
  while (head < tail) {
    int v = q[head++];
    for (int i : edges[v]) {
      --deg[i];
      if (deg[i] == 0) q[tail++] = i;
    }
  }
  int all = (n >> 5) + 2;
  for (int i = n - 1; i >= 0; i--) {
    int v = q[i];
    bs[v][v >> 5] |= 1U << (v & 31);
    for (int to : edges[v]) {
      for (int j = 0; j < all; j++) {
        bs[v][j] |= bs[to][j];
      }
    }
  }
  for (int i = 0; i < n; i++) {
    int x;
    scanf("%d", &x);
    color[i >> 5] |= (unsigned) x << (i & 31);
  }
  int ac = 0;
  for (int i = 0; i < n; i++) {
    int v = q[i];
    if (~(color[v >> 5] >> (v & 31)) & 1) {
      for (int j = 0; j < all; j++) color[j] ^= bs[v][j];
      ans[ac++] = v;
    }
  }
  std::sort(ans, ans + ac);
  printf("%d %d\n", n, ac);
  for (int i = 0; i < ac; i++) {
    if (i > 0) putchar(' ');
    printf("%d", ans[i] + 1);
  }
  puts("");
}