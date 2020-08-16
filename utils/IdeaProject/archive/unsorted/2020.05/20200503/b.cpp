/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

unordered_map<string, int> LETTERS;

int encode(string const &s) {
	return LETTERS[s];
}

struct prog {
	int mx;
	int dif;
	int cnt;
};

int main() {
	int cur = 0;
	for (string e : {"do", "re", "mi", "fa", "sol", "la", "si"}) {
		LETTERS[e] = cur++;
	}
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<prog> a;
	int left = n;
	int right = n;
	vector<int> str(2 * n + 1);
	// a.push_back({0, n + 1, 1});
	for (int i = 0; i < n; i++) {
		string op;
		cin >> op;
		string s;
		cin >> s;
		int p = encode(s);
		int ans = 0;
		if (op == "a") {
	 		str[right++] = p;
	 	} else {
	 		str[--left] = p;
	 	}
		auto check = [&](int mx) {
			if (op == "a") {
				return str[left + mx] == p;
			} else {
				return str[right - mx - 1] == p;
			}
		};
		vector<prog> b;
		for (prog &e : a) {
			if (check(e.mx)) {
				b.push_back({e.mx + 1, e.dif, e.cnt});
			}
		}
		if (!b.empty() && b.back().cnt == 1) {
			b.back().cnt++;
			b.back().dif = b.back().mx;
		} else 
		if (!b.empty() && b.back().mx % b.back().dif == 0) {
			b.back().cnt++;
		} else {
			b.push_back({0, right - left, 1});
		}
		for (prog &e : b) {
			ans += e.cnt;
			// cerr << e.mx << ' ' << e.dif << ' ' << e.cnt << '\n';
		}
		a.swap(b);
		// cerr << "   " << a.size() << endl;
		cout << ans << "\n";
		assert(a.size() < 100);
	}
}