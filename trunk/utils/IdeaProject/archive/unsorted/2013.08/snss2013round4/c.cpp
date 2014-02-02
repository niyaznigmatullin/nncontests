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

const char zz[3][6] = {{'.', '.', '.', '.', '.', '.'}, {'.', '[', ']', '[', ']', '.'}, {'.', '.', '.', '.', '.', '.'}};

char a[5000][5000];
bool was[5000][5000];
int n, m;

void dfs(int x, int y) {
  was[x][y] = true;
  for (int dx = -1; dx <= 1; dx++) {
    for (int dy = -1; dy <= 1; dy++) {
      if (dx == 0 && dy == 0) continue;
      int nx = x + dx;
      int ny = y + dy;
      if (nx < 0 || ny < 0 || nx >= n || ny >= m || was[nx][ny] || a[nx][ny] == '.') continue;
      dfs(nx, ny);
    }
  }
}

int main() {
  n = ni();
  m = ni();
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      a[i][j] = c;
    }
  }
  --n;
  if (n <= 0) {
    puts("0 0");
    return 0;
  }
  int ans1 = 0;
  for (int i = 0; i < m; i++) {
    if (a[n - 1][i] != '.' && !was[n - 1][i]) {
      dfs(n - 1, i);
      ++ans1;
    }
  }
  int ans2 = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j + 4 <= m; j++) {
      if (!was[i][j] && a[i][j] == '[' && a[i][j + 1] == ']' && a[i][j + 2] == '[' && a[i][j + 3] == ']') {
        bool ok = true;
        for (int di = 0; di < 3; di++) {
          for (int dj = 0; dj < 6; dj++) {
            if (zz[di][dj] != '.') continue;
            int x = i + di - 1;
            int y = j + dj - 1;
            if (x < 0 || y < 0 || x >= n || y >= m || (a[x][y] == '.')) {
              
            } else {
              ok = false;
            }
          }
        }
        if (ok) ++ans2;
      }
    }
  }
  printf("%d %d\n", ans1, ans2);
}
