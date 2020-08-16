/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

vector<int> p;
unordered_map<int, int> ids;

int get(int x) {
	return x == p[x] ? x : (p[x] = get(p[x]));
}

int getV(int v) {
	if (ids.find(v) != ids.end()) return ids[v];
	int cur = (int) p.size();
	ids[v] = cur;
	p.push_back(cur);
	return cur;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);	
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int v, u;
		cin >> v >> u;
		v = getV(v);
		u = getV(u);
		p[get(v)] = get(u);
	}
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		int x, k;
		cin >> x >> k;
		x = getV(x);
		vector<int> ans;
		for (int j = 0; j < k; j++) {
			int v;
			cin >> v;
			int nv = getV(v);
			if (get(nv) == get(x)) {
				ans.push_back(v);
			}
		}
		cout << ans.size();
		for (int v : ans) cout << ' ' << v;
		cout << '\n';
	}
}