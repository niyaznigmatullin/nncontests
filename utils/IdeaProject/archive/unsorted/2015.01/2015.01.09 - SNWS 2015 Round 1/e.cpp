#include <bits/stdc++.h>

int const DX[] = {1, 0, -1, 0};
int const DY[] = {0, 1, 0, -1};
int const N = 33;
bool water[N][N];

int pv[N * N];
int cn[N * N];

int get(int x) {
  return pv[x] == x ? x : (pv[x] = get(pv[x]));
}

void solve() {
  int n, m, s, q;
  scanf("%d%d%d%d", &n, &m, &s, &q);
  for (int i = 0; i < n; i++)
    std::fill(water[i], water[i] + m, false);
  for (int i = 0; i < q; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    int cx = 0;
    int cy = 0;
    while (c > 32) {
      water[cx][cy] = true;
      if (c == 'd') cx += 1; else
      if (c == 'u') cx -= 1; else
      if (c == 'l') cy -= 1; else 
      cy += 1;
      c = getchar();
    }
    water[cx][cy] = true;
  }
  for (int i = 0; i < n * m ; i++) pv[i] = i;
  for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++)
      if (!water[i][j])
      for (int dir = 0; dir < 4; dir++) {
        int x = i + DX[dir];
        int y = j + DY[dir];
        if (x < 0 || y < 0 || x >= n || y >= m || water[x][y]) continue;
        pv[get(x * m + y)] = get(i * m + j);
      }
  std::fill(cn, cn + n * m, 0);
  for (int i = 0; i < n; i++) 
    for (int j = 0; j < m; j++) {
      if (!water[i][j]) cn[get(i * m + j)]++;
    } 
  int ans = 0;
  for (int i = 0; i < n * m; i++)
    if (cn[i] >= s) ++ans;
  printf("%d\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}