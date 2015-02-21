#include <cstdio>

int const MOD = 1000000007;

void add(int & a, int b) {
  a += b;
  if (a >= MOD) a -= MOD;
}

  int dp[2][1001];
  int ways[2][1001];

int main() {
  int n;
  scanf("%d", &n);
  char s[1001][1001];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      s[i][j] = c;
    }
  }
  int q;
  scanf("%d", &q);
  for (int i = 0; i < q; i++) {
    int r1, c1, r2, c2;
    scanf("%d%d%d%d", &r1, &c1, &r2, &c2);
    if (r1 == 0 && r2 == n - 1 || c1 == 0 && c2 == n - 1 || r1 == 0 && c1 == 0 || r2 == n - 1 && c2 == n - 1) {
      puts("0 0");
      continue;
    }      
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (row >= r1 && row <= r2 && col >= c1 && col <= c2) {
          dp[row & 1][col] = -1;
          ways[row & 1][col] = 0;
        } else {
          if (row == 0 && col == 0) {
            dp[row & 1][col] = s[row][col] == 'C';
            ways[row & 1][col] = 1;
          } else {
            int best = -1;
            if (row > 0 && dp[row - 1 & 1][col] > best) {
              best = dp[row - 1 & 1][col];
              ways[row & 1][col] = ways[row - 1 & 1][col];
            }
            if (col > 0 && dp[row & 1][col - 1] > best) {
              best = dp[row & 1][col - 1];
              ways[row & 1][col] = ways[row & 1][col - 1];
            } else if (dp[row & 1][col - 1] == best) {
              add(ways[row & 1][col], ways[row & 1][col - 1]);
            }
            if (best < 0) {
              ways[row & 1][col] = 0;
              dp[row & 1][col] = -1;
              continue;
            }
            if (s[row][col] == 'C') ++best;
            dp[row & 1][col] = best;
          }
        }
      }
    }
    int b = dp[n - 1 & 1][n - 1];
    if (b < 0) b = 0;
    printf("%d %d\n", b, ways[n - 1 & 1][n - 1]);
  }
}
