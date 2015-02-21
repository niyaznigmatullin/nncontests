#include <cstdio>
#include <algorithm>

int const N = 333;
int V;
int s[N][N], bad[N][N];


bool checkwin1(int x1, int x2, int y1, int y2) {
  for (int i = x1; i <= x2; i++) {
    for (int j = y1; j <= y2; j++) {
      if (s[i][j] != 'X') continue;
      for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
          if (dx == 0 && dy == 0) continue;
          bool allhas = true;
          int x = i;
          int y = j;
          for (int f = 0; f < 5; f++) {
            if (s[x][y] != 'X') {
              allhas = false;
              break;
            }
            x += dx;
            y += dy;
          }
          if (allhas) {
            return true;
          }
        }
      }
    }
  }
  return false;
}


bool checkwin(int x1, int x2, int y1, int y2) {
  ++V;
  for (int i = x1; i <= x2; i++) {
    for (int j = y1; j <= y2; j++) {
      if (s[i][j] != 'X') continue;
      for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
          if (dx == 0 && dy == 0) continue;
          if (i + dx * 4 < 0 || j + dy * 4 < 0) continue;
          int x = i;
          int y = j;
          int cnd = 0;
          int cnf = 0;
          for (int f = 0; f < 5; f++) {
            if (s[x][y] == 'X') ++cnd;
            if (s[x][y] == '.') ++cnf;
            x += dx;
            y += dy;
          }
          if (cnd == 4 && cnf == 1) {
            x = i;
            y = j;
            for (int f = 0; f < 5; f++) {
              if (s[x][y] == '.') bad[x][y] = V;
              x += dx;
              y += dy;
            }
          }
        }
      }
    }
  }
  int cn = 0;
  for (int i = std::max(x1 - 5, 0); i <= x2 + 5; i++) {
    for (int j = std::max(y1 - 5, 0); j <= y2 + 5; j++) {
      if (bad[i][j] == V) ++cn;
    }
  }
  return cn > 1;
}

int main() {
  freopen("tictactoe.in", "r", stdin);
  freopen("tictactoe.out", "w", stdout);
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i <= n + 12; i++)  
    for (int j = 0; j <= m + 12; j++)
      s[i][j] = '.';
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      s[i + 6][j + 6] = c;
    }
  }   
  n += 12;
  m += 12;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (s[i][j] != '0') continue;
      for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
          if (dx == 0 && dy == 0) continue;
          int x = i;
          int y = j;
          int cnd = 0;
          int cnf = 0;
          for (int f = 0; f < 5; f++) {
            if (s[x][y] == '0') ++cnd;
            if (s[x][y] == '.') ++cnf;
            x += dx;
            y += dy;
          }
          if (cnd == 4 && cnf == 1) {
            x = i;
            y = j;
            for (int f = 0; f < 5; f++) {
              if (s[x][y] == '.') bad[x][y] = true;
              x += dx;
              y += dy;
            }
          }
        }
      }
    }
  }
  int cnbad = 0;
  int ibad = -1;
  int jbad = -1;
  for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++) if (bad[i][j]) {
      ++cnbad;
      ibad = i;
      jbad = j;
    }
  V = 10;
  int ans = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (s[i][j] != '.') continue;
      s[i][j] = 'X';
      if (checkwin1(std::max(0, i - 7), std::min(n - 1, i + 7), 
                   std::max(0, j - 7), std::min(m - 1, j + 7))) {
        ++ans;
      }
      s[i][j] = '.';
    }
  }
  if (ans > 0) {
    printf("%d\n", ans);
    return 0;
  }
  if (cnbad > 1) {
    puts("0");
    return 0;
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (s[i][j] != '.') continue;
      s[i][j] = 'X';
      if ((!(ibad >= 0 && (i != ibad || j != jbad)) && 
                   checkwin(std::max(0, i - 7), std::min(n - 1, i + 7), 
                   std::max(0, j - 7), std::min(m - 1, j + 7)))) {
        ++ans;
      }
      s[i][j] = '.';
    }
  }
  printf("%d\n", ans);
}