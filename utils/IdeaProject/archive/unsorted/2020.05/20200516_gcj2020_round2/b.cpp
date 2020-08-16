/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int test) {
	int n, m;
	cin >> n >> m;
	vector<int> x(n);
	for (int i = 1; i < n; i++) {
		cin >> x[i];
	}
	vector<int> from(m), to(m);
	for (int i = 0; i < m; i++) {
		int v, u;
		cin >> v >> u;
		--v;
		--u;
		from[i] = v;
		to[i] = u;
	}
	vector<int> first;
	vector<int> second;
	for (int i = 1; i < n; i++) {
		if (x[i] > 0) first.push_back(i); else second.push_back(i);
	}
	sort(first.begin(), first.end(), [&x](int a, int b) {
		return x[a] < x[b];
	});
	sort(second.begin(), second.end(), [&x](int a, int b) {
		return x[a] > x[b];
	});
	int fi = 0;
	int si = 0;
	vector<int> ans(n, -1);
	ans[0] = 0;
	int last = 0;
	int have = 1;
	int fn = (int) first.size();
	int sn = (int) second.size();
	while (fi < fn || si < sn) {
		if (si < sn && -x[second[si]] == have) {
			int ni = si;
			while (ni < sn && x[second[si]] == x[second[ni]]) ++ni;
			while (si < ni) {
				ans[second[si++]] = last + 1;
				have++;
			}
			last++;
		} else {
			int ni = fi;
			while (ni < fn && x[first[fi]] == x[first[ni]]) ++ni;
			assert(x[first[fi]] >= last);
			last = x[first[fi]];
			while (fi < ni) {
				ans[first[fi]] = x[first[fi]];
				++fi;
				have++;
			}
		}
	}
	vector<int> latency(m);
	for (int i = 0; i < m; i++) {
		latency[i] = max(1, max(ans[from[i]], ans[to[i]]) - min(ans[from[i]], ans[to[i]]));
	}
	cout << "Case #" << test << ":";
	for (int i : latency) cout << " " << i;
	cout << "\n";
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}