#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <iostream>
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

const int N = 123456;
int he[N], ne[N], ss[N], w[N], ff[N];
long long d1[N], d2[N];

const int md = 1000000007;

void dfs(int v) {
  for (int e = he[v]; e >= 0; e = ne[e]) {
    dfs(ff[e]);    
    d1[v] = (d1[v] + w[e] * d1[ff[e]]) % 2;
    d2[v] = (d2[v] + w[e] * d2[ff[e]]) % md;    
  }
  if (he[v] < 0) d1[v] = d2[v] = 1;
}

int main() {
  int n = ni();
  int m = 0;
  for (int i = 1; i < n; i++) {
    ss[m] = ni();
    ff[m] = i;
    ++m;
  }
  for (int i = 0; i < n - 1; i++) w[i] = ni();
  for (int i = 0; i < n; i++) he[i] = -1;
  for (int i = 0; i < n - 1; i++) {
    ne[i] = he[ss[i]];
    he[ss[i]] = i;
  }
  dfs(0);
  if (d1[0] & 1) {
    printf("%d\n", (int) d2[0]);
  } else {
    puts("Pass");
  }
}
