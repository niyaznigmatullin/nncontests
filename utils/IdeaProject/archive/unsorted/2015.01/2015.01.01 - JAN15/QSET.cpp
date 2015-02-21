#include <functional>
#include <vector>
#include <cstdio>

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

struct node {
  int pref[3];
  int suf[3];
  long long cn;
  int sum;
  
  node() : cn(0), sum(0) {
    std::fill(pref, pref + 3, 0);
    std::fill(suf, suf + 3, 0);
  }
  
  node(int x) : cn(x % 3 == 0 ? 1 : 0), sum(x % 3) {
    std::fill(pref, pref + 3, 0);
    std::fill(suf, suf + 3, 0);
    pref[x % 3]++;
    suf[x % 3]++;
  }
};


function<node(node, node)> mergeF = [](node const & a, node const & b) {
  node ret;
  for (int i = 0; i < 3; i++)
    ret.cn += (long long) a.suf[i] * b.pref[(3 - i) % 3];
  ret.cn += a.cn;
  ret.cn += b.cn;
  ret.sum = (a.sum + b.sum) % 3;
  for (int i = 0; i < 3; i++) {
    ret.pref[i] = a.pref[i] + b.pref[(i - a.sum + 3) % 3];
    ret.suf[i] = b.suf[i] + a.suf[(i - b.sum + 3) % 3];
  }
  return ret;
};

node const one = node();

int main() {
  int n, m;
  scanf("%d%d", &n, &m);
  SegmentTree<node> tree(n, mergeF, one);
  for (int i = 0; i < n; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    tree.apply(i, [c](node const & a) { return node((c - '0') % 3); });
  }
  for (int i = 0; i < m; i++) {
    int type, a, b;
    scanf("%d%d%d", &type, &a, &b);
    --a;
    if (type == 1)
      tree.apply(a, [b](node const & a) { return node(b % 3); });
    else
      printf("%lld\n", tree.calc(a, b).cn);
  }
}
