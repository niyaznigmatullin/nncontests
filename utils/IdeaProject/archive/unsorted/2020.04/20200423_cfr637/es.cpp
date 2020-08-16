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
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		int op;
		cin >> op;
		if (op == 1) {
			int pos, type;
			cin >> pos >> type;
			pos--;
			s[pos] = type;
		} else {
			int left, right;
			cin >> left >> right;
			--left;
			vector<int> stack;
			bool bad = false;
			for (int j = left; j < right; j++) {
				if (s[j] < 0) {
					if (stack.empty() || -stack.back() != s[j]) {
						bad = true;
						break;
					}
					stack.pop_back();
				} else {
					stack.push_back(s[j]);
				}
			}
			if (!bad && stack.empty()) {
				cout << "Yes\n";
			} else {
				cout << "No\n";
			}
		}
	}
}