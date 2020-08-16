/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int test) {
	int n, q;
	cin >> n >> q;
	vector<int> x(n), y(n);
	vector<char> dir(n);
	vector<int> xs, ys;
	xs.push_back(0);
	xs.push_back(q);
	ys.push_back(0);
	ys.push_back(q);
	for (int i = 0; i < n; i++) {
		cin >> x[i] >> y[i] >> dir[i];
		xs.push_back(x[i]);
		if (x[i] > 0) {
			xs.push_back(x[i] - 1);
		}
		if (x[i] + 1 <= q) {
			xs.push_back(x[i] + 1);
		}
		ys.push_back(y[i]);
		if (y[i] > 0) {
			ys.push_back(y[i] - 1);
		}
		if (y[i] + 1 <= q) {
			ys.push_back(y[i] + 1);
		}
	}
	sort(xs.begin(), xs.end());
	sort(ys.begin(), ys.end());
	xs.resize(unique(xs.begin(), xs.end()) - xs.begin());
	ys.resize(unique(ys.begin(), ys.end()) - ys.begin());
	vector<int> xv(xs.size()), yv(ys.size());
	for (int i = 0; i < n; i++) {
		x[i] = (int) (lower_bound(xs.begin(), xs.end(), x[i]) - xs.begin());
		y[i] = (int) (lower_bound(ys.begin(), ys.end(), y[i]) - ys.begin());
		if (dir[i] == 'N') {
			for (int j = y[i] + 1; j < (int) ys.size(); j++) {
				yv[j]++;
			}
		} else if (dir[i] == 'S') {
			for (int j = y[i] - 1; j >= 0; j--) {
				yv[j]++;
			}
		} else if (dir[i] == 'W') {
			for (int j = x[i] - 1; j >= 0; j--) {
				xv[j]++;
			}
		} else {
			for (int j = x[i] + 1; j < (int) xs.size(); j++) {
				xv[j]++;
			}
		}
	}
	int bestX = -1;
	int bestY = -1;
	for (int i = 0; i < (int) xs.size(); i++) {
		if (bestX < 0 || (xv[bestX] < xv[i])) bestX = i;
	}
	for (int i = 0; i < (int) ys.size(); i++) {
		if (bestY < 0 || (yv[bestY] < yv[i])) bestY = i;
	}
	cout << "Case #" << test << ": " << xs[bestX] << ' ' << ys[bestY] << endl;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int i = 1; i <= t; i++) solve(i);
}
