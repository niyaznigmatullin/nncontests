/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<int> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	vector<int> b(n);
	for (int i = 0; i < n; i++) {
		b[i] = 1;
	}
	for (int i = 1; i < n; i++) {
		if (a[i] > a[i - 1]) {
			b[i] = max(b[i], b[i - 1] + 1);
		}
	}
	for (int i = n - 2; i >= 0; i--) {
		if (a[i] > a[i + 1]) {
			b[i] = max(b[i], b[i + 1] + 1);
		}
	}
	int ans = 0;
	for (int x : b) ans += x;
	cout << ans * 500 << endl;
}