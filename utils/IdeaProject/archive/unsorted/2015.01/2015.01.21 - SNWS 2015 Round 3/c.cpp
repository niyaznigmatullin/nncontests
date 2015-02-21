#include <bits/stdc++.h>

struct point {
  int x;
  int y;
  
  point() : x(0), y(0) {}
  point(int x, int y) : x(x), y(y) {}
  
  int distsq(point const & p) const {
    int dx = x - p.x;
    int dy = y - p.y;
    return dx * dx + dy * dy;
  }
};

double disttosegment(point p, point p1, point p2) {
  int a = p.distsq(p1);
  int b = p.distsq(p2);
  int c = p1.distsq(p2);
  if (a + c < b) return sqrt(1. * a);
  if (b + c < a) return sqrt(1. * b);
  double la = p2.y - p1.y;
  double lb = p1.x - p2.x;
  double lc = -p1.x * la - p1.y * lb;
  return std::abs(la * p.x + lb * p.y + lc) / sqrt(la * la + lb * lb);
}

point p[123];

double dp[123], next[123];

void solve() {
  int n, w;
  point p0;
  scanf("%d%d%d%d", &n, &w, &p0.x, &p0.y);
  std::fill(dp, dp + w + 1, 1e20);
  dp[0] = 0;
  for (int i = 0; i < n; i++) {
    int m, q;
    scanf("%d%d", &m, &q);
    for (int j = 0; j < m; j++) {
      scanf("%d%d", &p[j].x, &p[j].y);
    }
    double cur = 1e20;
    for (int j = 0; j + 1 < m; j++) {
      cur = std::min(cur, disttosegment(p0, p[j], p[j + 1]));
    }
    for (int f = 0; f <= w; f++) {
      next[f] = dp[f];
    }
    for (int f = 0; f <= w; f++) {
      int nf = std::min(w, f + q);
      next[nf] = std::min(next[nf], dp[f] + cur);
    }
    for (int f = 0; f <= w; f++) dp[f] = next[f];
  }
  if (dp[w] >= 1e19) puts("-1"); else 
    printf("%.17lf\n", dp[w]);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}