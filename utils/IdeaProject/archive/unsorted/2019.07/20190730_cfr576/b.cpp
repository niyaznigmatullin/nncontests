/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	unordered_map<int, unordered_set<int>> a;
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		a[x].insert(i);
	}
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		int op;
		cin >> op;
		if (op == 1) {
			int pos, x;
			cin >> pos >> x;
			pos--;
			
		}
	}
}