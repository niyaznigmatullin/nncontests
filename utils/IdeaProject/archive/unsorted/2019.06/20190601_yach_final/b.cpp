/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);	
	int n;
	cin >> n;
	vector<int> a(2 * n + 1);
	for (int i = 0; i < 2 * n + 1; i++) {
		cin >> a[i];
	}
	int today = n;
	vector<int> from(2 * n + 1);
	vector<int> to(2 * n + 1);
	from[today] = a[today];
	to[today] = a[today];
	for (int i = today - 1; i >= 0; i--) {
		from[i] = std::min(a[i], from[i + 1]);
		to[i] = std::max(a[i], to[i + 1]);
	}
	for (int i = today + 1; i < 2 * n + 1; i++) {
		from[i] = std::min(a[i], from[i - 1]);
		to[i] = std::max(a[i], to[i - 1]);
	}
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		int t1, t2, d1, d2;
		cin >> t1 >> t2 >> d1 >> d2;
		d1 += n;
		d2 += n;
		int qf = std::min(from[d1], from[d2]);
		int qt = std::max(to[d1], to[d2]);
		if (std::max(qf, t1) <= std::min(qt, t2)) {
			cout << "yes\n";
		} else {
			cout << "no\n";
		}
	}
}
