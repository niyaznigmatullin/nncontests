/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

mt19937 rng(time(NULL));

struct interactor {
	virtual long long ask(int i, int j) = 0;
	virtual void answer(long long ans) const = 0;
};

struct myinteractor : interactor {
	vector<vector<long long>> z;
	map<pair<int, int>, long long> asked;
	int n;
	int k;
	int count;

	myinteractor(int n, int m, int k) : z(n), n(n), k(k) {
		count = 0;
		for (int i = 0; i < m; i++) {
			z[rng() % n].push_back(i);
		}
	}

	long long ask(int i, int j) {
		assert(j >= 0 && j < (int) z[i].size());
		if (asked.find({i, j}) != asked.end()) return asked[{i, j}];
		++count;
		return asked[{i, j}] = z[i][j];
	}

	void answer(long long ans) const {
		assert(ans == this->k);
		cerr << count << " queries\n";
	}
};

struct stdinteractor : interactor {

	long long ask(int i, int j) {
		cout << "? " << i + 1 << ' ' << j + 1 << endl;
		long long ret;
		cin >> ret;
		return ret;
	}

	void answer(long long ans) const {
		cout << "! " << ans  << endl;
	}
};

struct solver {
	interactor &d;
	int n;
	vector<int> a;
	int k;

	solver(interactor &d, int n, vector<int> const &a, int k) : d(d), n(n), a(a), k(k) {}

	void solve() {
		vector<int> id(n);
		for (int i = 0; i < n; i++) id[i] = i;
		vector<int> left(n, 0), right(a);
		vector<int> ask(n);
		vector<pair<long long, int>> replies;
		while (true) {
			int have = 0;
			for (int i = 0; i < n; i++) {
				if (right[i] - left[i] > k + 1) {
					right[i] = left[i] + k + 1;
				}
			}
			for (int i = 0; i < n; i++) have += right[i] - left[i];
			for (int i = 0; i < n; i++) {
				int value = have - k - 1;
				if (right[i] - left[i] > value + 1) {
					int sub = (right[i] - left[i] - (value + 1));
					left[i] += sub;
					have -= sub;
					k -= sub;
				}
			}
			long long minValue = 1LL << 60;
			int whereMin = -1;
			long long maxValue = 0;
			int whereMax = -1;
			int low = 0;
			int high = 0;
			replies.clear();
			for (int i = 0; i < n; i++) {
				if (left[i] < right[i]) {
					ask[i] = (int) ((long long) k * (right[i] - left[i]) / have) + left[i];
					low += ask[i] - left[i];
					high += right[i] - ask[i] - 1;
					long long z = d.ask(i, ask[i]);
					if (z < minValue) {
						minValue = z;
						whereMin = i;
					}
					if (z > maxValue) {
						maxValue = z;
						whereMax = i;
					}
					replies.push_back({z, i});
				}
				cerr << "(" << left[i] << ", " << right[i] << ") ";
			}
			// assert(low < k + 1);
			// assert(high < have - k - 1);
			cerr << endl;
			cerr << "k = " << k << ", have = " << have << ", low = " << low << ", high = " << high << endl;
			if (whereMin == whereMax) {
				d.answer(minValue);
				return;
			}
			sort(replies.begin(), replies.end());
			// int addLeft = (low < k ? 1 : 0) + ask[whereMin] - left[whereMin];
			// int addRight = (high + 1 <= have - k - 1 ? 1 : 0) + right[whereMax] - ask[whereMax] - 1;
			// k -= addLeft;
			// have -= addLeft;
			// left[whereMin] += addLeft;
			// have -= addRight;
			// right[whereMax] -= addRight;
			for (auto &e : replies) {
				if (low > k) break;
				int addLeft = (low < k ? 1 : 0) + ask[e.second] - left[e.second];
				low += right[e.second] - ask[e.second];
				k -= addLeft;
				have -= addLeft;
				left[e.second] += addLeft;
			}
			reverse(replies.begin(), replies.end());
			for (auto &e : replies) {
				if (high > have - (k + 1)) {
					break;
				}
				int addRight = (high + 1 <= have - (k + 1) ? 1 : 0) + right[e.second] - ask[e.second] - 1;
				have -= addRight;
				right[e.second] -= addRight;
				high += ask[e.second] - left[e.second] + 1;
			}
		}
		vector<long long> all;
		for (int i = 0; i < n; i++) {
			for (int j = left[i]; j < right[i]; j++) {
				all.push_back(d.ask(i, j));
			}
		}
		sort(all.begin(), all.end());
		d.answer(all[k]);
	}
};

int main() {
	// int w, n;
	// cin >> w >> n;
	// for (int i = 0; i < w; i++) {
	// 	vector<int> a(n);
	// 	for (int i = 0; i < n; i++) cin >> a[i];
	// 	int k;
	// 	cin >> k;
	// 	--k;
	// 	stdinteractor d;
	// 	solver s(d, n, a, k);
	// 	s.solve();
	// }
	while (true) {
		// int n = 2 + (int) (rng() % 4);
		int n = 5;
		int m = (int) (rng() % 100000) + 100000;
		int k = (int) (rng() % m);
		myinteractor d(n, m, k);
		vector<int> a(n);
		for (int i = 0; i < n; i++) a[i] = (int) d.z[i].size();
		solver s(d, n, a, k);
		s.solve();
		cerr << n << ' ' << m << ' ' << k << endl;
	}
}
