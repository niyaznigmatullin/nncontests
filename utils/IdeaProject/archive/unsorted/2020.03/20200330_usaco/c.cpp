/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int MOD;

void add(int &a, int b) {
	a += b;
	if (a >= MOD) a -= MOD;
}

int mul(int a, int b) {
	return (int) ((long long) a * b % MOD);
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	freopen("exercise.in", "r", stdin);
	freopen("exercise.out", "w", stdout);
	int n;
	cin >> n >> MOD;
	vector<int> f(n + 1);
	f[0] = 1;
	for (int p = 2; p <= n; p++) {
		bool isPrime = true;
		for (int i = 2; i * i <= p; i++) {
			if (p % i == 0) {
				isPrime = false;
				break;
			}
		}
		if (!isPrime) continue;
		vector<int> g(f);
		for (int z = p; z <= n; z *= p) {
			for (int i = 0; i + z <= n; i++) {
				add(g[i + z], mul(f[i], z));
			}
		}
		f = g;
	}
	int ans = 0;
	for (int i : f) add(ans, i);
	cout << ans << endl;
}
