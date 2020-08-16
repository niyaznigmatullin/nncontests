/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */
#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

#define _GLIBCXX_DEBUG
#define DEBUG 1

#define err(...) fprintf(stderr, "%.2f : ", 1. * clock() / CLOCKS_PER_SEC), fprintf(stderr, __VA_ARGS__), fflush(stderr)

int P, G;
int MOD;

int gcd(int a, int b) {
	if (a < 0) a = -a;
	if (b < 0) b = -b;
	while (b > 0) {
		int t = a % b;
		a = b;
		b = t;
	}
	return a;
}

void add(int &a, int b) {
	a += b;
	if (a >= MOD) a -= MOD;
}

int mul(int a, int b, int p) {
	return (int) ((long long) a * b % p);
}

int modPow(int a, int b, int p) {
	int ans = 1 % p;
	while (b > 0) {
		if ((b & 1) == 1) ans = mul(ans, a, p);
		a = mul(a, a, p);
		b >>= 1;
	}
	return ans;
}
void init() {
	for (int i = 2; ; i++) {
		bool bad = false;
		for (int j = 2; j * j <= P - 1; j++) {
			if ((P - 1) % j != 0) continue;
			if (modPow(i, j, P) == 1 || modPow(i, (P - 1) / j, P) == 1) {
				bad = true;
				break;
			}
		}
		if (!bad) {
			G = i;
			break;
		}
	}
	// cerr << G << ' ' << P << endl;
	int x = 1;
	for (int i = 1; i < P - 1; i++) {
		x = mul(x, G, P);
		assert(x != 1);
	}
}

int logg( int x ) {
	assert(1 <= x && x < P);
	int cur = 1;
	for (int i = 0; i < P - 1; i++) {
		if (cur == x) return i;
		cur = mul(cur, G, P);
	}
	assert(0);
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, q;
	cin >> P >> n >> q;
	MOD = P - 1;
	init();
	vector<int> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
		a[i] = logg(a[i]);
	}
	for (int i = 0; i < q; i++) {
		int type, left, right;
		cin >> type >> left >> right;
		--left;
		if (type == 1) {
			int x;
			cin >> x;
			int pw = logg(x);
			for (int i = left; i < right; i++) add(a[i], pw);
		} else {
			int ans = P - 1;
			for (int i = left; i < right; i++) {
				ans = gcd(ans, a[i]);
			}
			ans = (P - 1) / ans;
			cout << ans << '\n';
		}
	}
}

//3 8 14