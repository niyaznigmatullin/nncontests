#include <functional>
#include <bits/stdc++.h>
#include <vector>

using namespace std;

template<typename T>
struct SegmentTree {
  int n;
  vector<T> a;
  function<T(T, T)> f;
  T one;

  SegmentTree(int cap, function<T(T, T)> const & f, T const & one) : n(getSize(cap)), a(2 * n, one), f(f), one(one) {
    
  }
  
  void apply(int x, function<T(T)> const & g) {
    x += n;
    a[x] = g(a[x]);
    while (x > 1) {
      x >>= 1;
      a[x] = f(a[x * 2], a[x * 2 + 1]);
    }
  }
  
  T calc(int l, int r) {
    --r;
    l += n;
    r += n;
    T left = one;
    T right = one;
    while (l <= r) {
      if ((l & 1) == 1) left = f(left, a[l++]);
      if ((r & 1) == 0) right = f(a[r--], right);
      l >>= 1;
      r >>= 1;
    }
    return f(left, right);
  }
  
  private:
  int getSize(int n) {
    int ret = 1;
    while (ret < n) ret *= 2;
    return ret;
  }
};

int const N = 123456;
long long xs[N];
int id[N];

struct bomb {
  long long x;
  long long r;
  int id;
  
  bomb() : x(0), r(0), id(-1) {}
  bomb(long long x, long long r, int id) : x(x), r(r), id(id) {}
  
};

bomb b[N];
int ans[N];

void solve() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%lld%lld", &b[i].x, &b[i].r);
    b[i].id = i;
    id[i] = i;
  }
  std::sort(b, b + n, [](bomb const & a, bomb const & b) { return a.x < b.x; });
  for (int i = 0; i < n; i++) xs[i] = b[i].x;
  SegmentTree<int> toleft(n, function<int(int, int)>([](int a, int b) { return a > b ? b : a; }), n);
  for (int i = 0; i < n; i++) toleft.apply(i, function<int(int)>([i](int j) { return i; }));
  std::sort(id, id + n, [](int i, int j) { return b[i].x - b[i].r < b[j].x - b[j].r; });
  for (int it = 0; it < n; it++) {
    int i = id[it];
    int left = std::lower_bound(xs, xs + n, b[i].x - b[i].r) - xs;
    int right = std::upper_bound(xs, xs + n, b[i].x + b[i].r) - xs;
    int val = toleft.calc(left, right);
    if (val > i) val = i;
    toleft.apply(i, function<int(int)>([val](int i) { return val; }));
  }
  
  SegmentTree<int> toright(n, function<int(int, int)>([](int a, int b) { return a < b ? b : a; }), -1);
  for (int i = 0; i < n; i++) toright.apply(i, function<int(int)>([i](int j) { return i; }));
  std::sort(id, id + n, [](int i, int j) { return b[i].x + b[i].r > b[j].x + b[j].r; });
  for (int it = 0; it < n; it++) {
    int i = id[it];
    int left = std::lower_bound(xs, xs + n, b[i].x - b[i].r) - xs;
    int right = std::upper_bound(xs, xs + n, b[i].x + b[i].r) - xs;
    int val = toright.calc(left, right);
    if (val < i) val = i;
    toright.apply(i, function<int(int)>([val](int i) { return val; }));
  }
  for (int i = 0; i < n; i++) ans[b[i].id] = toright.calc(i, i + 1) - toleft.calc(i, i + 1) + 1;
  for (int i = 0; i < n; i++) {
    if (i > 0) putchar(' ');
    printf("%d", ans[i]);
  }
  puts("");
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
