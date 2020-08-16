/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

int main() {
	int n = 20;
	vector<vector<long long>> c(n + 1, vector<long long>(n + 1));
	c[0][0] = 1;
	for (int i = 1; i <= n; i++) {
		c[i][0] = 1;
		for (int j = 1; j <= n; j++) {
			c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
		}
	}

	long long prev = 1;
	long long p3 = 1;
	for (int m = 1; m <= 20; m++) {
		long long ans = 0;
		for (int s = 0; 2 * s <= m; s++) {
			ans += c[m][2 * s] * c[2 * s][s];
		}
		cout << m << " " << ans << " " << 3 * prev - ans << endl;
		prev = ans; 
		p3 *= 3;
	}
}