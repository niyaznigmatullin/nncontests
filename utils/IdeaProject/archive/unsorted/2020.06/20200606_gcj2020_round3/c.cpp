/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct solver {
	int n;
	vector<int> have;
	int pos;
	int d;

	solver(int n) : n(n), have(n, 1), pos(0), d(0) {

	}

	pair<int, function<void(int)>> ask() {
		int good = 0;
		int all = 0;
		for (int i = 0; i < n; i++) {
			if (!have[i]) continue;
			for (int j = i + 1; j < n; j++) {
				if (!have[j]) continue;
				good += i + j >= n;
				all++;
			}
		}
		if (good > all * 0.7 || pos >= n - 10) {
			return make_pair(0, [](int x) { assert(x != -1); });
		}
		return make_pair(pos + 1, [this](int x) {
			assert(x != -1);
			if (x == 0) {
				pos++;
				have[d] = false;
				d = 0;
			} else {
				d++;
			}
		});
	}

	pair<int, int> ans() {
		if (pos + 2 <= n) {
			int x = 0, y = 0;
			while (x == y) {
				x = pos + rand() % (n - pos);
				y = pos + rand() % (n - pos);
			}
			return {x + 1, y + 1};
		} else {
			return {1, 2};
		}
	}
};

int main() {
	int t, n, c;
	cin >> t >> n >> c;
	vector<solver> z(t, n);
	while (true) {
		vector<pair<int, function<void(int)>>> ask(t);
		cerr.flush();
		for (int i = 0; i < t; i++) {
			ask[i] = z[i].ask();
		}
		bool allZero = true;
		for (int i = 0; i < t; i++) {
			if (i > 0) cout << ' ';
			cout << ask[i].first;
			if (ask[i].first != 0) allZero = false;
		}
		cout << '\n';
		cout.flush();
		if (allZero) break;
		for (int i = 0; i < t; i++) {
			int x;
			cin >> x;
			ask[i].second(x);
		}
	}
	for (int i = 0; i < t; i++) {
		if (i > 0) cout << ' ';
		auto y = z[i].ans();
		cout << y.first << ' ' << y.second;
	}
	cout << '\n';
	cout.flush();
}
