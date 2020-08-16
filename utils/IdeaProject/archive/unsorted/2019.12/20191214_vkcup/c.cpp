/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	vector<long long> sz(26);
	sz[0] = 1;
	for (int i = 1; i < 26; i++) {
		sz[i] = 4 * sz[i - 1];
	}
	function<int(int, long long, int)> get = [&](int k, long long pos, int val) {
		assert(pos < sz[k]);
		if (k == 0) {
			return val;
		}
		int add = 0;
		while (pos >= sz[k - 1]) {
			pos -= sz[k - 1];
			add++;
		}
		if (add >= 2) add++;
		return get(k - 1, pos, val + add);
	};
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		long long pos;
		cin >> pos;
		cout << get(25, pos, 0) << '\n';
	}
}
