/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m;
	cin >> n >> m;
	vector<string> s(n);
	for (int i = 0; i < n; i++) {
		cin >> s[i];
	}
	sort(s.begin(), s.end());
	vector<pair<int, int>> q;
	q.push_back({0, n});
	int ans = 0;
	for (int i = 0; i < m; i++) {
		vector<pair<int, int>> nq;
		for (auto &e : q) {
			int mx = 0;
			int pos = e.first;
			for (int letter = 0; letter < 4; letter++) {
				int npos = pos;
				while (npos < e.second && s[npos][i] - 'A' == letter) {
					npos++;
				}
				mx = max(mx, npos - pos);
				if (pos != npos)
					nq.push_back({pos, npos});
				pos = npos;
			}
			ans += mx;
		}
		q.swap(nq);
	}
	printf("%.17f\n", 1. * ans / n);
}
