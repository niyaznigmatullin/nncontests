#include <cstdio>
#include <set>
#include <utility>
#include <algorithm>

using namespace std;

int F[42];

int const N = 555;
int const MAXN = 333;

int w[N], h[N], edges[N][N];
int d[N][MAXN], was[N][MAXN];
int ce[N];


int main() {
  for (int i = 0; i < 3; i++) scanf("%d", F + i);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d%d", w + i, h + i);
    scanf("%d", ce + i);
    for (int j = 0; j < ce[i]; j++) {
      scanf("%d", edges[i] + j);
      --edges[i][j];
    }
  }

  set<pair<int, int > > q;  
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < MAXN; j++) {
      if (i == 0) {
        if (j <= w[0]) {
          d[i][j] = h[0];
          q.insert(make_pair(h[0], i * MAXN + j));
        }
      }
    }
  }
  while (!q.empty()) {
    auto v = *q.begin();
    q.erase(q.begin());
    int mi = v.second / MAXN;
    int mj = v.second % MAXN;
    for (int e = 0; e < ce[mi]; e++) {
      int to = edges[mi][e];
      int nj = std::min(mj, w[to]);
      int nw = std::min(d[mi][mj], h[to]);
      if (d[to][nj] < nw) {
        q.erase(make_pair(d[to][nj], to * MAXN + nj));
        d[to][nj] = nw;
        q.insert(make_pair(d[to][nj], to * MAXN + nj));
      }
    }
  }
  int ans = 0;
  int ansa = 0;
  int ansb = 0;
  int ansc = 0;
  for (int q = 1; q < MAXN; q++) {
    int r = d[n - 1][q];
    if (r <= 0) continue;
    for (int it1 = 0; it1 < 3; it1++) {
      for (int it2 = 0; it2 < 3; it2++) {
        if (it1 == it2) continue;
        int it3 = it1 ^ it2 ^ 3;
        int a = std::min(q, F[it1]);
        int b = std::min(r, F[it2]);
        int c = F[it3];
        if (ans < a * b * c) {
          ans = a * b * c;
          ansa = a;
          ansb = b;
          ansc = c;
        }
      }
    }
  }
  printf("%d %d %d\n", ansa, ansb, ansc);
}