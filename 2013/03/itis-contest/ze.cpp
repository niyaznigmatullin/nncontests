#include <iostream>
#include <cstdio>

using namespace std;

int dp[1234567];

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int s, n;
    cin >> s >> n;
    for (int i = 0; i <= s; i++) dp[i] = 1 << 30;
    dp[0] = 0;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        for (int j = x; j <= s; j++) if (dp[j] > dp[j - x] + 1) dp[j] = dp[j - x] + 1;
    }
    if (dp[s] > 1 << 29) cout << "NO\n"; else cout << "YES\n" << dp[s] << "\n";
}