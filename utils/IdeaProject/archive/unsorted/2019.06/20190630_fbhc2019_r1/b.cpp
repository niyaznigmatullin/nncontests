
/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int const MAXN = 1234567;
int const MOD = 1000000007;

int two[MAXN];

void add(int &a, int b) {
	a += b;
	if (a >= MOD) a -= MOD;
}

void solve(int test) {
	int n, k;
	cin >> n >> k;
	string s;
	cin >> s;
	assert((int) s.size() == n);
	int count = 0;
	int ans = 0;
	for (int i = n - 1; i >= 0; i--) {
		if (s[i] == 'A') {
			count--;
		} else {
			count++;
		}
		if (count > k) {
			add(ans, two[i + 1]);
			count -= 2;
		}
		if (count < 0) count = 0;
	}
	cout << "Case #" << test << ": " << ans << "\n";
}

int main() {
	two[0] = 1;
	for (int i = 1; i < MAXN; i++) {
		two[i] = two[i - 1];
		add(two[i], two[i - 1]);
	}
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}
}