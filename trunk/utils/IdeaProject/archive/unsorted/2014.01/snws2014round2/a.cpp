#include <cstdio>
#include <ctime>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;

const int N = 1234567;
int best;
long long answ;

int he[N], ne[N], ss[N], ff[N], down[N], cdown[N];
pair<int, int> cc[N];

void dfs(int v, int p) {
  down[v] = 0;
  cdown[v] = 1;
  for (int e = he[v]; e >= 0; e = ne[e]) {
    if (ff[e] != p) {
      dfs(ff[e], v);
      if (down[ff[e]] + 1 > down[v]) {
        down[v] = down[ff[e]] + 1;
        cdown[v] = cdown[ff[e]];
      } else if (down[ff[e]] + 1 == down[v]) {
        cdown[v] += cdown[ff[e]];
      }
    }    
  }
  int cn = 0;
  cc[cn++] = make_pair(0, 1);
  cc[cn++] = make_pair(0, 1);
  for (int e = he[v]; e >= 0; e = ne[e]) {
    if (ff[e] != p) {
      cc[cn++] = make_pair(down[ff[e]] + 1, cdown[ff[e]]);
    }
  }
  std::sort(cc, cc + cn);
  long long ways = 0;
  int mm = cc[cn - 1].first + cc[cn - 2].first;
  if (cc[cn - 1].first == cc[cn - 2].first) {
    int i = cn - 1;
    long long got = 0;
    while (i >= 0 && cc[i].first == cc[cn - 1].first) {
      ways += got * cc[i].second;
      got += cc[i].second;
      --i;
    }  
  } else {
    int i = cn - 2;
    if (cc[i].first == 0) ways = 1; else
    while (i >= 0 && cc[i].first == cc[cn - 2].first) {
      ways += cc[i].second;
      --i;
    }
    ways *= cc[cn - 1].second;
  }  
  if (mm > best) {
    best = mm;
    answ = ways;
  } else if (mm == best) answ += ways;
}

int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i + 1 < n; i++) scanf("%d%d", ss + i, ff + i);
  for (int i = 0; i + 1 < n; i++) {
    --ss[i];
    --ff[i];
    ss[i + n - 1] = ff[i];
    ff[i + n - 1] = ss[i];    
  }
  for (int i = 0; i < n; i++) he[i] = -1;
  for (int i = 0; i < n + n - 2; i++) {
    ne[i] = he[ss[i]];
    he[ss[i]] = i;
  }
  dfs(0, -1);
  cout << best + 1 << " " << answ << endl;
}
