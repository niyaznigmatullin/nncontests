#include <bits/stdc++.h>


using namespace std;

struct segm {
	int from;
	int to;
	int type;
};

int const ALPHABET = 27;
struct Fenwick {
	int n;
	vector<int> a;
	
	Fenwick(int n = 0) : n(n), a(n) {}
	
	void xr(int x, int y) {
		for (int i = x; i < n; i |= i + 1) a[i] ^= y;
	}
	
	int get(int x) const {
		int ret = 0;
		for (int i = x; i >= 0; i = (i & (i + 1)) - 1) ret ^= a[i];
		return ret;
	}
	
	int get(int left, int right) const {
		return get(right - 1) ^ get(left - 1);
	}
};

int main() {
	string str;
	cin >> str;
	int n = str.size();
	vector<int> s(n + 2);
	s[0] = s[n + 1] = ALPHABET - 1;
	for (int i = 1; i <= n; i++) s[i] = str[i - 1] - 'a';
	n += 2;
	vector<int> index(n);
	vector<vector<int> > letters(ALPHABET);
	vector<Fenwick> fenw(ALPHABET);
	for (int j = 0; j < ALPHABET; j++) {
		for (int i = 0; i < n; i++) {
			if (s[i] == j) {
				index[i] = letters[j].size();
				letters[j].push_back(i);
			}
		}
		fenw[j] = Fenwick(n);
	}
	vector<int> cur(ALPHABET, -1);
	vector<vector<int> > pr(n);
	for (int i = 0; i < n; i++) {
		cur[s[i]] = i;
		pr[i] = cur;
	}
	fill(cur.begin(), cur.end(), -1);
	vector<vector<int> > ne(n);
	for (int i = n - 1; i >= 0; i--) {
		cur[s[i]] = i;
		ne[i] = cur;
	}
	vector<segm> all;
	for (int i = 0; i + 1 < n; i++) {
		for (int j = 0; j < ALPHABET; j++) {
			int same = ne[i + 1][j];
			if (same >= 0)
				all.push_back({i, same, 1});
		}
	}
	for (int i = 1; i < n; i++) {
		for (int j = 0; j < ALPHABET; j++) {
			int same = pr[i - 1][j];
			if (same >= 0)
				all.push_back({same + 1, i + 1, 2});
		}
	}
	sort(all.begin(), all.end(), [](segm const &a, segm const &b) {
		int da = a.to - a.from;
		int db = b.to - b.from;
		if (da != db) return da < db;
		return a.from < b.from;
	});
	vector<vector<int> > g1(n, vector<int> (ALPHABET));
	vector<vector<int> > g2(n, vector<int> (ALPHABET));
	segm last = {-1, -1, -1};
	int lastG = -1;
	vector<int> have(ALPHABET + 1);
	auto calc = [&](int from, int to) {
		if (to - from == 1) {
			return 1;
		} else {
			for (int j = 0; j <= ALPHABET; j++) have[j] = false;
  			for (int j = 0; j < ALPHABET; j++) {
  				if (ne[from][j] >= 0 && ne[from][j] < to) {
  					int tmp = 0;
  					int first = ne[from][j];
  					int last = pr[to - 1][j];
  					if (first != from) {
  						tmp ^= g1[from][j];
  					}
  					if (last != to - 1) {
  						tmp ^= g2[to - 1][j];
  					}
  					tmp ^= fenw[j].get(index[first], index[last] + 1);
  					if (tmp <= ALPHABET)
	  					have[tmp] = true;
  				}
  			}
  			int curG = 0;
  			while (curG <= ALPHABET && have[curG]) ++curG;
  			assert(curG <= ALPHABET);
  			return curG;
  	}
	};
	for (segm &e: all) {
		int curG;
		if (last.from == e.from && last.to == e.to) {
			curG = lastG;
		} else {
			curG = calc(e.from, e.to);
		}
		if (e.type == 1) {
			g1[e.from][s[e.to]] = curG;
		} else {
			g2[e.to - 1][s[e.from - 1]] = curG;
		}
		if (e.type == 1 && e.to < n && s[e.from] == s[e.to] && ne[e.from + 1][s[e.to]] == e.to) {
			fenw[s[e.to]].xr(index[e.from], curG);
		}
		// if (e.from > 0 && e.to + 1 < n)
		// cerr << str.substr(e.from - 1, e.to - e.from) << ' ' << curG << endl;
	}
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		int left, right;
		cin >> left >> right;
		right++;
		cout << (calc(left, right) == 0 ? "Bob" : "Alice") << '\n';
	}
}