/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

long long const INF = 1LL << 60;

vector<long long> deq;

long long getBest(vector<long long> const &b, int w) {
	int n = (int) b.size();
	deq.resize(n);
	int j = 0;
	int head = 0;
	int tail = 0;
	long long ans = -INF;
	for (int i = 0; i < n; i++) {
		while (i - j > w) {
			if (deq[head] == b[j]) ++head;
			++j;
		}
		// cerr << i << ' ' << j << ' ' << b[i] << ' ' << deq[head] << endl;
		if (head < tail) {
			ans = max(ans, b[i] - deq[head]);
		}
		while (tail > head && b[i] < deq[tail - 1]) {
			--tail;
		}
		deq[tail++] = b[i];
	}
	return ans;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int k;
	cin >> k;
	int m, n;
	cin >> n >> m;
	vector<vector<int>> a(n, vector<int>(m));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> a[i][j];
		}
	}
	long long ans = -INF;
	for (int i = 0; i < m; i++) {
		vector<long long> b(n + 1);
		for (int j = i + 1; j <= m; j++) {
			long long add = 0;
			for (int k = 1; k <= n; k++) {
				add += a[k - 1][j - 1];
				b[k] += add;
			}
			// cerr << "i = " << i << ", j = " << j << endl;
			// for (int i = 0; i <= n; i++) cerr << b[i] << ' ';
				// cerr << '\n';
			// cerr << "w = " << k / (j - i) << endl;
			ans = max(ans, getBest(b, k / (j - i)));
		}
	}
	cout << ans << endl;
}