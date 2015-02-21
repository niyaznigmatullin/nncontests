#include <bits/stdc++.h>

using namespace std;

int const INF = 1 << 29;

int add(int a, int b) {
  if (a == INF || b == INF) return INF;
  a += b;
  if (a >= INF) return INF;
  return a;
}

int getSum(int v, int left, int right, int l, int r) {
  if (right <= l || r <= left) return 0;
  if (l <= left && right <= r) return getC(v);
  relax(v);
  int mid = (l + r) >> 1;
  return add(getSum(2 * v, left, mid, l, r), getSum(2 * v + 1, mid, right, l, r));
}


int getSum(int l, int r) {
  return getSum(1, 0, K, l, r);
}

int getMax(int v, int left, int right, int l, int r) {
  if (right <= l || r <= left) return -INF;
  if (l <= left && right <= r) return getM(v);
  relax(v);
  int mid = (l + r) >> 1;
  return std::max(getMax(2 * v, left, mid, l, r), getMax(2 * v + 1, mid, right, l, r));
}
  
int getMax(int l, int r) {
  return getMax(1, 0, K, l, r);
}

void mul(int v, int left, int right, int l, int r) {
  if (right <= l || r <= left) return;
  if (l <= left && right <= r) {
    tom[v]++;
    return;
  }
  relax(v);
  mul(2 * v, left, mid, l, r);
  mul(2 * v + 1, mid, right, l, r);
  c[v] = add(getC(2 * v), getC(2 * v + 1));
  mx[v] = std::max(getM(2 * v), getM(2 * v + 1));
}

void mul(int l, int r) {
  mul(1, 0, K, l, r);
}

int getKth(int k) {
  int l = 0;
  int r = K;
  int v = 1;
  while (l < r - 1) {
    int mid = (l + r) >> 1;
    if (getC(v * 2) >= k) {
      r = mid;
    } else {
      k -= getC(v * 2);
      l = mid;
    }
  }
  return l;
}

void addE(int v, int left, int right, int x, int y) {
  if (right <= l || r <= left) return;
  if (left + 1 == right) {
    mx[v] = c[v] = add(getM(v), y);
    return;
  }
  relax(v);
  int mid = (left + right) >> 1;
  if (x < mid) addE(2 * v, left, mid, x, y); else
      addE(2 * v + 1, mid, right, x, y);
  c[v] = add(getC(2 * v), getC(2 * v + 1));
  mx[v] = std::max(getM(2 * v), getM(2 * v + 1));   
}

void setE(int x, int y) {
  setE(1, 0, K, x, y);
}

void build(int len) {
  K = 1;
  while (K < len) K *= 2;
  for (int i = 0; i < len; i++) mx[i + K] = c[i + K] = 1;
  for (int i = K - 1; i > 0; i--) {
    c[i] = c[i * 2] + c[i * 2 + 1];
    mx[i] = std::max(mx[i * 2], mx[i * 2 + 1]);
  }
}

void solve() {
  int n, m;
  scanf("%d%d", &n, &m);
  build(n);
  for (int i = 0; i < m; i++) {
    int ch = getchar();
    while (ch <= 32) ch = getchar();
    int l, r;
    scanf("%d%d", &l, &r);    
    int cl = getKth(l);
    int cr = getKth(r);
    --cr;
    ++cl;
    if (ch == 'Q') {
      printf("%d\n", std::max(std::max(getMax(cl, cr + 1), 
    } else {
    
    }
  }
}


int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
