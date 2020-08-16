/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, m;
	cin >> n >> m;
	int const INF = 1 << 30;
	vector<int> f(m + 2, INF);
	f[0] = 0;
	for (int i = 0; i < n; i++) {
		int w, c;
		cin >> w >> c;
		for (int x = m; x >= 0; x--) {
			if (f[x] != INF) {
				int nx = std::min(x + w, m + 1);
				f[nx] = std::min(f[nx], f[x] + c);
			}
		}
	}
	cout << f[m + 1] << endl;
}