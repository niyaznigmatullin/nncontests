#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

int ni() {
  int c = getchar();
  while (c != '-' && (c < '0' || c > '9')) c = getchar();
  int sg = 0;
  if (c == '-') {
    sg = 1;
    c = getchar();
  }
  int x = 0;
  while (c >= '0' && c <= '9') {
    x = x * 10 + c - '0';
    c = getchar();
  }
  return sg ? -x : x;
}

int best;

void go(vector<int> a) {
  int sz = a.size();
  if (sz == 1) {
    if (std::abs(21 - a[0]) < std::abs(21 - best) || (std::abs(21 - a[0]) == std::abs(21 - best) && a[0] < best)) {
      best = a[0];
    }
    return;
  }
  for (int i = 0; i < sz; i++) {
    for (int j = 0; j < sz; j++) {
      if (i == j) continue;
      {
        vector<int> b;
        for (int k = 0; k < sz; k++) if (i != k && j != k) b.push_back(a[k]);
        b.push_back(a[i] + a[j]);
        go(b);
      }
      {
        vector<int> b;
        for (int k = 0; k < sz; k++) if (i != k && j != k) b.push_back(a[k]);
        b.push_back(a[i] * a[j]);
        go(b);
      }
      {
        vector<int> b;
        for (int k = 0; k < sz; k++) if (i != k && j != k) b.push_back(a[k]);
        b.push_back(a[i] - a[j]);
        go(b);
      }
      if (a[j] != 0 && a[i] % a[j] == 0) {
        vector<int> b;
        for (int k = 0; k < sz; k++) if (i != k && j != k) b.push_back(a[k]);
        b.push_back(a[i] / a[j]);
        go(b);
      }
    }
  }
}

int main() {
  vector<int> a;
  for (int i = 0; i < 4; i++) {
    int x = ni();
    a.push_back(x);  
  }
  best = 1 << 30;
  go(a);
  printf("%d\n", best);
}