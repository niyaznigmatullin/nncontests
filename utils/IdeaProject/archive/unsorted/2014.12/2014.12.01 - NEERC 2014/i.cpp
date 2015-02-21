#include <cstdio>
#include <vector>
#include <cstring>

using namespace std;

typedef pair <int,int> pii;

struct RangeTree {
  const static int INF = int(1e8);
  int kk;
  vector <int> b;
  
  RangeTree(int n) {
    kk = 1;
    while (kk < n) {
      kk *= 2;
    }
    b.assign(2 * kk + 10, -INF);
  }
  
  int getMax(int L, int R) {
    int res = -INF;
    L += kk;
    R += kk;
    while (L <= R) {
      if (L % 2 == 1) {
        res = max(res, b[L++]);
      }
      if (R % 2 == 0) {
        res = max(res, b[R--]);
      }
      L /= 2;
      R /= 2;
    }
    return res;
  }
  
  void change(int x, int val) {
    x += kk;
    b[x] = max(b[x], val);
    while (x > 1) {
      x /= 2;
      b[x] = max(b[2 * x], b[2 * x + 1]);
    }
  }
};

void updateDP(vector <RangeTree> & toLeft, vector <RangeTree> & toRight, int L, int R, int val) {
  toLeft[R].change(L, val);
  toRight[L].change(R, val);
}


int main() {
  freopen("improvements.in", "r", stdin);
  freopen("improvements.out", "w", stdout);
  int n;
  scanf("%d", &n);
  vector <int> x(n);
  for (int i = 0; i < n; ++i) {
    scanf("%d", &x[i]);
  }
  
  vector <RangeTree> toLeft;
  vector <RangeTree> toRight;
  for (int i = 0; i <= n + 1; ++i) {
    toLeft.emplace_back(n + 2);
    toRight.emplace_back(n + 2);
  }
  
  updateDP(toLeft, toRight, 0, n+1, 0);
  for (int i = 0; i < n; ++i) {
    for (int L = 0; L < x[i]; ++L) {
      updateDP(toLeft, toRight, L, x[i], toRight[L].getMax(x[i] + 1, n + 1) + 1);
    }
    for (int R = x[i] + 1; R <= n + 1; ++R) {
      updateDP(toLeft, toRight, x[i], R, toLeft[R].getMax(0, x[i] - 1) + 1);
    }
  }
  int res = 0;
  for (int L = 0; L <= n + 1; ++L) {
    res = max(res, toRight[L].getMax(0, n + 1));
  }
  printf("%d\n", res);
  return 0;
}
