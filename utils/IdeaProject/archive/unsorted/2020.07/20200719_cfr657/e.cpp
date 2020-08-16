/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int V = 0;

int build(int n, int k, vector<int> &c) {
	int cur = V++;
	if (n == 1) {
		return cur;
	}
	if (k > 0) {
		int left = build(1, 0, c);
		c[left] = cur;
		int right = build(n - 2, k - 1, c);
		c[right] = cur;
	} else {
		int half = (n - 1) / 2;
		int left = build(half, 0, c);
		int right = build(half, 0, c);
		c[left] = cur;
		c[right] = cur;
	}
	return cur;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, k;
	cin >> n >> k;
	if (n % 2 == 0) {
		cout << "NO\n";
		return 0;
	}
	int internal = (n - 1) / 2;
	if (internal - 1 < k) {
		cout << "NO\n";
		return 0;
	}
	vector<int> c(n);
	int root = build(n, k, c);
	c[root] = -1;
	cout << "YES\n";
	for (int i = 0; i < n; i++) {
		if (i > 0) cout << ' ';
		cout << c[i] + 1;
	}
	cout << '\n';
}