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

const int K = 1000;
const int M = 4122;

int f[M][M];

void add(int x, int y, int z) {
  for (int i = x; i < M; i |= i + 1) {
    for (int j = y; j < M; j |= j + 1) {
      f[i][j] += z;
    }
  }
}

int getsum(int x, int y) {
  int ret = 0;
  for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
    for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
      ret += f[i][j];
    }
  }
  return ret;
}

int main() {
  int X;
  scanf("%d", &X);
  int T;
  scanf("%d", &T);
  map<pair<int, int>, int> all;
  for (int i = 0; i < T; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    int x, y;
    scanf("%d%d", &x, &y);
    --x;
    --y;
    if (c == 'N') {
      all[make_pair(x, y)]++;
      if (x < X)
        add(x - y + K - 1, x + y, 1);
    } else if (c == 'L') {
      all[make_pair(x, y)]--;
      if (x < X)
        add(x - y + K - 1, x + y, -1);    
    } else if (c == 'S') {
      if (x >= X || y + 1 >= K || all[make_pair(x, y)] > 0 || all[make_pair(x, y + 1)] > 0) {
        puts("No");
      } else {
        printf("%d\n", getsum(x - y + K - 1, x + y) + getsum(x - (y + 1) + K - 1, x + (y + 1)));
      }
    }
  }
  int m1 = 1 << 30;
  int m2 = 1 << 30;
  for (int j = 0; j < 1000; j++) {
    for (int i = 0; i < K; i++) {
      int x = X + j;
      int y = i;
      pair<int, int> p(x, y);
      if (all.find(p) == all.end() || all[p] == 0) {
        int cur = getsum(x - y + K - 1, x + y);
        if (cur < m1) {
          m2 = m1;
          m1 = cur;
        } else if (cur < m2) {
          m2 = cur;
        }
      } else {
        add(x - y + K - 1, x + y, all[p]);
      }
    }
  }
  printf("%d\n", m1 + m2);
}