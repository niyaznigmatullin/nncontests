#include <set>

#include <cstdio>

using namespace std;
int n;

int const N = 2222;
double D[2222 * 2222];
int id[N];
int g[N];
int was[N];

inline double & d(int x, int y) {
  if (x < y) std::swap(x, y);
  return D[x * n + y];
}

inline int mp(int i, int j) {
  if (i < j) std::swap(i, j);
  return i * n + j;
}

struct comp {
  bool operator()(int x, int y) const {
    return D[x] < D[y] || (D[x] == D[y] && x < y);
  }
};


int main() {
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      d(i, j) = c - '0';
    }
  }
  set<int, comp> q;
  for (int i = 0; i < n; i++)
    for (int j = 0; j < i; j++) {
      q.insert(mp(i, j));
    }
  for (int i = 0; i < n; i++) g[i] = 1;
  for (int i = 0; i < n; i++) id[i] = i;
  for (int it = 0; it + 1 < n; it++) {
    int v = *q.begin();
    q.erase(v);
    int i = v / n;
    int j = v % n;
    double dd = d(i, j);
    for (int k = 0; k < n; k++) {
      if (was[k] || k == i || k == j) continue;
      q.erase(mp(i, k));
      q.erase(mp(j, k));
    }
    for (int k = 0; k < n; k++) {
      if (was[k] || k == i || k == j) continue;
      double nd = (d(i, k) * g[i] + d(j, k) * g[j]) / (g[i] + g[j]);
      d(j, k) = nd;
      q.insert(mp(j, k));
    }
    was[i] = true;
    g[j] += g[i];
    printf("%d %d %.17lf\n", id[i] + 1, id[j] + 1, dd);
    id[j] = it + n;
//    for (auto & e : q) printf("%.17lf\n", d(e / n, e % n));
  }
}
