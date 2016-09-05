#include <cstdio>
#include <algorithm>
#include <vector>

using namespace std;

int const N = 1234567;

long long const INF = 1LL << 60;

long long dp[N];
vector<int> a[N];
int days[N];
int main() {
  int n, m;
  scanf("%d%d", &n, &m);
  for (int i = 0; i < m; i++) scanf("%d", days + i);
  for (int i = 0; i < m; i++) {
    a[i] = vector<int> (n);
    for (int j = 0; j < n; j++) {
      int x;
      scanf("%d", &x);
      a[i][j] = x;
    }
  }
  dp[0] = 0;
  for (int i = 1; i <= n; i++) dp[i] = -INF;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (i + days[j] <= n) {
        dp[i + days[j]] = std::max(dp[i + days[j]], dp[i] + a[j][i]);
      }
    }
    dp[i + 1] = std::max(dp[i + 1], dp[i]);
  }
  printf("%lld\n", dp[n]);
}