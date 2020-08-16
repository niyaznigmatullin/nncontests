/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

typedef long long ll;

const int N = 100;

int n;
ll W, a[N];
unordered_map<ll, ll> h[N];

ll f( int n, ll W ) {
	if (W == 0)
		return 1;
	if (n == -1)
		return 0;
	if (W >= 4 * a[n])
		return 0;
	if (h[n].count(W))
		return h[n][W];
	ll &res = h[n][W];
	for (int t = 0; t <= 2; t++)
		if (W >= t * a[n])
			res += f(n - 1, W - t * a[n]);
	return res;
}

int main() {
	cin >> n >> W;
	forn(i, n) cin >> a[i];
	cout << f(n - 1, W) << endl;
}