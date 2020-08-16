/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<long long> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	vector<int> b(n);
	for (int i = 0; i < n; i++) {
		cin >> b[i];
	}
	long long ans = 0;
	map<long long, int> cn;
	for (int i = 0; i < n; i++) cn[a[i]]++;
	for (int i = 0; i < n; i++) {
		long long x = a[i];
		if (cn[x] == 1) continue;
		long long cur = 0;
		for (int j = 0; j < n; j++) {
			long long y = a[j];
			if (((x & y) == y)) {
				cur += b[j];
			}
		}
		ans = max(ans, cur);
	}
	cout << ans << endl;
}
