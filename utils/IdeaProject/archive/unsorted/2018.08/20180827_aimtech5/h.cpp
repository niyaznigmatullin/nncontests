/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;


int cc;
pair<int, int> ds[12345];
pair<int, int> prs[12345];

void gen(int x, int n, int got, int count) {
	if (x == n) {
		ds[cc++] = {got, count};
		return;
	}
	for (int i = 0; i <= prs[x].second; i++) {
		gen(x + 1, n, got, count + (i & 1));
		if (i < prs[x].second) got *= prs[x].first;
	}
}

int const K = 44;
int const N = 200000;

int from[K][N], f[K / 2][N];

int const MAX = 6000000;
int p[MAX];

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, q;
	cin >> n >> q;
	for (int i = 2; i < MAX; i++) {
		for (int j = i; j < MAX; j += i) {
			p[j] = i;
		}
	}
	for (int i = 0; i < n; i++) {
		int x;
		cin >> x;
		int cn = 0;
		while (x > 1) {
			int count = 0;
			int prime = p[x];
			while (x % prime == 0) {
				count++;
				x /= prime;
			}
			prs[cn++] = {prime, count};
		}
		cc = 0;
		gen(0, cn, 1, 0);
		int all = 0;
		for (int j = 0; j < cn; j++) all += prs[j].second;
		for (int j = 0; j < K; j++) from[j][i] = -1;
		for (int j = 0; j < cc; j++) {
			for (int e = 0; e < K >> 1; e++) {
				if (f[e][ds[j].first] >= 0) {
					int ne = e + all - ds[j].second;
					from[ne][i] = std::max(from[ne][i], f[e][ds[j].first]);
				}
			}
		}
		if (i > 0) {
			for (int e = 0; e < K; e++) {
				from[e][i] = std::max(from[e][i], from[e][i - 1]);
			}
		}
		for (int j = 0; j < cc; j++) {
			f[all - ds[j].second][ds[j].first] = i;
		}
	}
	for (int i = 0; i < q; i++) {
		int left, right;
		cin >> left >> right;
		--right;
		--left;
		int ans = K;
		for (int e = 0; e < K; e++) {
			if (from[e][right] >= left) {
				ans = e;
				break;
			}
		}
		cout << ans << '\n';
	}
}
