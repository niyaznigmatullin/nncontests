
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

bool check(long long mask, int n, int k) {
	for (int i = 0; i < n; i++) {
		for (int j = i; j < n; j++) {
			int ca = 0;
			int cb = 0;
			for (int e = i; e <= j; e++) {
				if (((mask >> e) & 1) == 1) ca++; else cb++;
			}
			if (cb > ca + k) {
				return false;
			}
		}
	}
	return true;
}

void solve(int test) {
	int n, k;
	cin >> n >> k;
	string s;
	cin >> s;
	long long have = 0;
	for (int i = 0; i < n; i++) {
		if (s[i] == 'A') have |= 1 << i;
	}
	long long cur = 0;
	int ans = -1;
	while (true) {
		if (check(cur | have, n, k)) {
			ans = (int) (cur * 2 % MOD);
			break;
		}
		if ((cur | have) == (1 << n) - 1) break;
		cur = ((cur | have) + 1) & ~have;
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
