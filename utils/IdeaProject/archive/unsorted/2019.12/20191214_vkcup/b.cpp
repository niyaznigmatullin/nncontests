/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct commit {
	int author;
	string hash;
};

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<commit> a;
	for (int i = 0; i < n; i++) {
		int who;
		string h;
		cin >> who >> h;
		if (!a.empty()) {
			if (a.back().author != who) {
				a.pop_back();
			}
		}
		a.push_back({who, h});
	}
	for (auto &e : a) {
		cout << e.hash << '\n';
	}
}