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

const int N = 333333;

int win[N][3], from[N], to[N], he[N], ne[N], herev[N], nerev[N], deg[N], q[N], who[N];

void solve() {
  int n, m, st;  
  scanf("%d%d%d", &n, &m, &st);
  --st;
  for (int i = 0; i < n; i++) scanf("%d", who + i);
  for (int i = 0; i < m; i++) {
    scanf("%d%d", from + i, to + i);
    --from[i];
    --to[i];
    deg[from[i]]++;
  }
  for (int i = 0; i < n; i++) he[i] = -1, herev[i] = -1;
  for (int i = 0; i < m; i++) {
    nerev[i] = herev[to[i]];
    herev[to[i]] = i;
    ne[i] = he[from[i]];
    he[from[i]] = i;
  }
  int head = 0;
  int tail = 0;
  for (int i = 0; i < n; i++) if (deg[i] == 0) {
    q[tail++] = i;
    win[i][0] = 0;
    win[i][1] = 1;
  }
  while (head < tail) {
    int v = q[head++];
    if (he[v] >= 0) {
      win[v][0] = win[v][1] = who[v] ^ 1;
      for (int e = he[v]; e >= 0; e = ne[e]) {
        if (who[v] == 0) {
          win[v][0] = std::min(win[v][0], win[to[e]][1]);
          win[v][1] = std::min(win[v][1], win[to[e]][0]);
        } else {
          win[v][0] = std::max(win[v][0], win[to[e]][1]);
          win[v][1] = std::max(win[v][1], win[to[e]][0]);      
        }
      }
    }
//    printf("%d %d %d\n", v, win[v][0], win[v][1]);
    for (int e = herev[v]; e >= 0; e = nerev[e]) {
      deg[from[e]]--;
      if (deg[from[e]] == 0) q[tail++] = from[e];
    }
  }
  printf("%d\n", win[st][0]);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}