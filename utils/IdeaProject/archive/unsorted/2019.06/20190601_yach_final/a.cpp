/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	vector<vector<int>> a(n);
	for (int i = 0; i < n; i++) {
		int len;
		cin >> len;
		a[i].resize(len);
		for (int j = 0; j < len; j++) {
			cin >> a[i][j];
		}
		sort(a[i].begin(), a[i].end());
	}
	int left = 0;
	vector<int> cur(n);
	vector<int> ans(n);
	vector<int> goLeft(n), goRight(n);
	for (int i = 0; i < n; i++) {
		int len = (int) a[i].size();
		if (len % 2 == 1) {
			goLeft[i] = -1;
			goRight[i] = 1;
			cur[i] = len / 2;
		} else {
			goLeft[i] = -2;
			goRight[i] = 0;
			cur[i] = len / 2 - 1;
		}
		ans[i] = a[i][cur[i]];
		left += ans[i];
	}
	int money;
	cin >> money;
	queue<int> q;
	if (left < money) {
		for (int i = 0; i < n; i++) {
			if (goRight[i] == 0) {
				q.push(i);
			}
		}
		for (int i = 0; i < n; i++) {
			if (goRight[i] == 1) {
				q.push(i);
			}
		}
		while (left < money) {
			int v = q.front();
			q.pop();
			if (cur[v] + 1 == (int) a[v].size()) {
				ans[v] = a[v][cur[v]] + money - left;
				break;
			}
			cur[v]++;
			left -= ans[v];
			ans[v] = a[v][cur[v]];
			left += ans[v];
			if (left > money) {
				ans[v] -= left - money;
				left = money;
				break;
			}
			goRight[v] += 2;
			q.push(v);
		}
	}
	if (left > money) {
		for (int i = 0; i < n; i++) {
			if (goLeft[i] == -1) {
				q.push(i);
			}
		}
		for (int i = 0; i < n; i++) {
			if (goLeft[i] == -2) {
				q.push(i);
			}
		}
		while (left > money) {
			int v = q.front();
			q.pop();
			cur[v]--;
			left -= ans[v];
			ans[v] = cur[v] == -1 ? 0 : a[v][cur[v]];
			left += ans[v];
			if (left < money) {
				ans[v] += money - left;
				left = money;
				break;
			}
			goRight[v] = goRight[v] - 2;
			if (cur[v] >= 0) {
				q.push(v);
			}
		}
	}
	for (int i = 0; i < n; i++) {
		if (i > 0) {
			cout << ' ';
		}
		cout << ans[i];
	}
	cout << endl;
}
