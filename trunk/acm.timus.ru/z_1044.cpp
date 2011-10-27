#include <iostream>
#include <string>

using namespace std;
long long dp[1000], ans;
int n;

int main() {
	cin >> n;
	n /= 2;
	memset(dp, 0, sizeof(dp));
	dp[0] = 1;
	for (int i=0; i<n; i++) {
		for (int j=100; j>=0; j--) {
			for (int t=1; t<10; t++) if (j-t>=0) {
				dp[j] += dp[j - t];
			}
		}
	}
	for (int i=0; i<=100; i++) {
		ans += dp[i] * dp[i];
	}
	cout << ans;
    return 0;
}
