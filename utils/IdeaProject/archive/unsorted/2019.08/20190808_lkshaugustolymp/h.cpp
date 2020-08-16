/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct margaret {
	vector<vector<int>> link;
	vector<int> term;
	vector<int> suf, ss;

	margaret(vector<string> const &s, int ALPHABET) {
		int all = 1;
		for (auto const &e : s) {
			all += (int) e.size();
		}
		link.assign(all, vector<int>(ALPHABET, -1));
		term.assign(all, -1);
		int fr = 1;
		for (int i = 0; i < (int) s.size(); i++) {
			auto const &e = s[i];
			int v = 0;
			for (char j : e) {
				int &to = link[v][j - 'a'];
				if (to < 0) to = fr++;
				v = to;
			}
			term[v] = i;
		}
		vector<int> q(all);
		int head = 0;
		int tail = 0;
		q[tail++] = 0;
		suf.assign(all, -1);
		ss.assign(all, -1);
		suf[0] = -1;
		while (head < tail) {
			int v = q[head++];
			for (int i = 0; i < ALPHABET; i++) {
				int &to = link[v][i];
				int w = suf[v] < 0 ? 0 : link[suf[v]][i];
				if (to < 0) {
					to = w;
				} else {
					suf[to] = w;
					q[tail++] = to;
				}
			}
			if (suf[v] < 0) {
				ss[v] = -1;
			} else {
				ss[v] = term[suf[v]] >= 0 ? suf[v] : ss[suf[v]];
			}
		}
	}
};

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<string> s(n);
	for (int i = 0; i < n; i++) {
		cin >> s[i];
	}
	string t;
	cin >> t;
	margaret ac(s, 26);
	int len = (int) t.size();
	vector<int> dp(len + 1, -1);
	dp[0] = 0;
	int v = 0;
	for (int i = 1; i <= len; i++) {
		v = ac.link[v][t[i - 1] - 'a'];
		for (int u = v; u >= 0; u = ac.ss[u]) {
			if (ac.term[u] >= 0) {
				int clen = (int) s[ac.term[u]].size();
				if (dp[i - clen] >= 0) {
					dp[i] = ac.term[u];
				}
			}
		}
	}
	assert(dp[len] >= 0);
	vector<string> ans;
	for (int i = len; i > 0; i -= (int) s[dp[i]].size()) {
		ans.push_back(s[dp[i]]);
	}
	reverse(ans.begin(), ans.end());
	for (int i = 0; i < (int) ans.size(); i++) {
		if (i > 0) cout << ' ';
		cout << ans[i];
	}
	cout << endl;
}
