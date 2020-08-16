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
		if (ans != this->k) {
			cerr << "Expected " << this->k << ", found " << ans << endl;
		}
		assert(ans == this->k);
		cerr << count << " queries\n";
	}
};

struct stdinteractor : interactor {
	map<pair<int, int>, long long> asked;

	long long ask(int i, int j) {
		if (asked.find({i, j}) != asked.end()) return asked[{i, j}];
		cout << "? " << i + 1 << ' ' << j + 1 << endl;
		long long ret;
		cin >> ret;
		return asked[{i, j}] = ret;
	}

	void answer(long long ans) const {
		cout << "! " << ans << endl;
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
		vector<int> left(n, 0), right(a), mid(n);
		k++;
		while (true) {
			vector<pair<long long, int>> replies;
			int have = 0;
			int low = 0;
			int high = 0;
			for (int i = 0; i < n; i++) {
				if (left[i] == right[i]) continue;
				mid[i] = (left[i] + right[i]) / 2;
				low += mid[i] - left[i];
				have += right[i] - left[i];
				high += right[i] - mid[i] - 1;
			}
			if (low + 1 == k) {
				for (int i = 0; i < n; i++) {
					if (mid[i] > left[i]) {
						--mid[i];
						--low;
						++high;
						break;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				// cerr << "(" << left[i] << ", " << right[i] << ") ";
				if (left[i] == right[i]) continue;
				replies.push_back({d.ask(i, mid[i]), i});
			}
			// cerr << endl;
			sort(replies.begin(), replies.end());
			// cerr << "low = " << low << ", high = " << high << ", k = " << k << ", have = " << have << endl;
			if (replies.size() == 1) {
				int id = replies[0].second;
				// cerr << "here" << endl;
				d.answer(d.ask(id, left[id] + k - 1));
				return;
			}
			if (have == (int) replies.size()) {
				d.answer(replies[k - 1].first);
				return;
			}
			if (low + 1 < k) {
				int id = replies[0].second;
				int add = mid[id] + 1 - left[id];
				k -= add;
				left[id] += add;
				assert(k > 0);
			} else {
				assert(high + 1 < have - k);
				int id = replies.back().second;
				right[id] = (left[id] + right[id]) / 2;
			}
		}
	}
};

int main() {
	int w, n;
	cin >> w >> n;
	for (int i = 0; i < w; i++) {
		vector<int> a(n);
		for (int i = 0; i < n; i++) cin >> a[i];
		int k;
		cin >> k;
		--k;
		stdinteractor d;
		solver s(d, n, a, k);
		s.solve();
	}
	// while (true) {
	// 	// int n = 2 + (int) (rng() % 4);
	// 	int n = 5;
	// 	int m = (int) (rng() % 100000) + 100000;
	// 	int k = (int) (rng() % m);
	// 	myinteractor d(n, m, k);
	// 	vector<int> a(n);
	// 	for (int i = 0; i < n; i++) a[i] = (int) d.z[i].size();
	// 	solver s(d, n, a, k);
	// 	s.solve();
	// 	cerr << n << ' ' << m << ' ' << k << endl;
	// }
}
