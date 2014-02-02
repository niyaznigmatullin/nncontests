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

const int N = 55;

int s[N][N], up1[N][N], up2[N][N], down1[N][N], down2[N][N];

void solve() {
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      s[i][j] = c;
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (s[i][j] != 'X') up1[i][j] = up2[i][j] = 0; else {        
        up1[i][j] = i > 0 && j > 0 ? up1[i - 1][j - 1] + 1 : 1;
        up2[i][j] = i > 0 && j + 1 < m ? up2[i - 1][j + 1] + 1 : 1;
      }
    }
  }
  for (int i = n - 1; i >= 0; i--) {
    for (int j = 0; j < m; j++) {
      if (s[i][j] != 'X') down1[i][j] = down2[i][j] = 0; else {
        down1[i][j] = i + 1 < n && j > 0 ? down1[i + 1][j - 1] + 1 : 1;
        down2[i][j] = i + 1 < n && j + 1 < m ? down2[i + 1][j + 1] + 1 : 1;
      }
    }
  }
  int ans = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      ans = std::max(std::min(std::min(up1[i][j], up2[i][j]), std::min(down1[i][j], down2[i][j])), ans);
    }    
  }
  printf("%d\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}