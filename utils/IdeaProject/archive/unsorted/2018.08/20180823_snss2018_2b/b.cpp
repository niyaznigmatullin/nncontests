/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

string answer;

bool check(int x, int n, set<string> const &q) {
	if (x == n) {
		if (q.find(answer) == q.end()) return true;
		return false;
	}
	for (char c = 'a'; c <= 'z'; c++) {
		answer += c;
		if (check(x + 1, n, q)) return true;
		answer.pop_back();
	}
	return false;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<string> s(n);
	for (int i = 0; i < n; i++) {
		cin >> s[i];
	}
	for (int len = 1; ; len++) {
		set<string> q;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j + len <= (int) s[i].size(); j++) {
				q.insert(s[i].substr(j, len));
			}
		}
		if (check(0, len, q)) {
			cout << answer << endl;
			return 0;
		}
	}
}