/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

template<typename T>
struct tree {
	vector<T> tr;
	int n;
	function<T(T, T)> f;
	T one;

	tree(int sz, function<T(T, T)> f, T one): f(f), one(one) {
		n = 1;
		while (n < sz) n <<= 1;
		tr.assign(2 * n, one);
		for (int i = n - 1; i >= 1; i--) {
			tr[i] = f(tr[i + i], tr[i + i + 1]);
		}
	}

	void assign(int x, T const &y) {
		x += n;
		tr[x] = y;
		while (x > 1) {
			x >>= 1;
			tr[x] = f(tr[x + x], tr[x + x + 1]);
		}
	}

	T get(int l, int r) {
		r--;
		l += n;
		r += n;
		T retL = one;
		T retR = one;
		while (l <= r) {
			if ((l & 1) == 1) {
				retL = f(retL, tr[l++]);
			}
			if ((r & 1) == 0) {
				retR = f(tr[r--], retR);
			}
			l >>= 1;
			r >>= 1;
		}
		return f(retL, retR);
	}
};


int const K = 45;

struct minp {
	int minPrefix;
	int sum;
};

vector<int> rev(vector<int> const &a) {
	vector<int> ret(a.size());
	for (int i = 0; i < (int) a.size(); i++) {
		ret[a[i]] = i;
	}
	return ret;
}

int main() {
    mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, k;
	cin >> n >> k;
	vector<int> s(n);
	for (int i = 0; i < n; i++) cin >> s[i];
	for (int i = 0; i < n; i++) {
		if (s[i] > 0) s[i]--;
	}
	vector<vector<int>> p(k);
	for (int i = 0; i < k; i++) {
		p[i] = vector<int>(K);
		for (int j = 0; j < K; j++) {
			p[i][j] = j;
		}
		shuffle(p[i].begin(), p[i].end(), rng);
	}
	vector<vector<int>> r(k);
	for (int i = 0; i < k; i++) {
		r[i] = rev(p[i]);
	}
	int q;
	cin >> q;
	tree<minp> tm(n, [](minp const &a, minp const &b) {
		minp ret = {min(a.minPrefix, a.sum + b.minPrefix), a.sum + b.sum};
		return ret;
	}, {0, 0});
	vector<int> ONE(K);
	for (int i = 0; i < K; i++) ONE[i] = i;
	auto apply = [](vector<int> const &a, vector<int> const &b) { 
		vector<int> ret(K);
		for (int i = 0; i < K; i++) ret[i] = a[b[i]];
		return ret;
	};
	for (int i = 0; i < k; i++) {
		assert(ONE == apply(p[i], r[i]));
	}
	tree<vector<int>> tp(n, apply, ONE);
	for (int i = 0; i < n; i++) {
		if (s[i] >= 0) {
			tm.assign(i, {1, 1});
			tp.assign(i, p[s[i]]);
		} else {
			tm.assign(i, {-1, -1});
			tp.assign(i, r[~s[i]]);
		}
	}
	for (int i = 0; i < q; i++) {
		int op;
		cin >> op;
		if (op == 1) {
			int pos, type;
			cin >> pos >> type;
			pos--;
			if (type > 0) type--;
			if (type >= 0) {
				tm.assign(pos, {1, 1});
				tp.assign(pos, p[type]);
			} else {
				tm.assign(pos, {-1, -1});
				tp.assign(pos, r[~type]);
			}
		} else {
			int left, right;
			cin >> left >> right;
			--left;
			minp got = tm.get(left, right);
			if (got.sum == 0 && got.minPrefix >= 0 && ONE == tp.get(left, right)) {
				cout << "Yes\n";
			} else {
				cout << "No\n";
			}
		}
	}
}