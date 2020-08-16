#include <bits/stdc++.h>


using namespace std;


int const MOD = 1000000007;

void add(int &a, int b) {
	a += b;
	if (a >= MOD) a -= MOD;
}

int mul(int a, int b) {
	return (int) ((long long) a * b % MOD);
}
int const N = 33333;
int const K = 444;

int a[N], f[N][K], pw[N], was[K];

int getf(int x, int y) {
	if (y == 0) return f[x][y];
	int ans = f[x][y];
	add(ans, MOD - f[x][y - 1]);
	return ans;
}

int main() {
	ios::sync_with_stdio(false);
	int n, k, m;
	cin >> n >> k >> m;
	for (int i = 0; i < m; i++) {
		cin >> a[i];
		a[i]--;
	}
	for (int i = 1; i < k; i++) {
		f[0][i] = 1;
		add(f[0][i], f[0][i - 1]);
	}
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j < k; j++) {
			if (j + 1 < k) {
				add(f[i][j], mul(k - j, getf(i - 1, j + 1)));
			}
			add(f[i][j], f[i - 1][j]);
			add(f[i][j], f[i][j - 1]);
		}
	}
	pw[0] = 1;
	for (int i = 1; i <= n; i++) {
		pw[i] = mul(pw[i - 1], k);
	}
	bool ok = false;
	for (int i = 0, j = 0; i < m; i++) {
		while (j < m && !was[a[j]]) {
			was[a[j]] = true;
			j++;
		}
		if (j - i == k) {
			ok = true;
			break;
		}
		was[a[i]] = false;
	}
	if (ok) {
		cout << mul(n - m + 1, pw[n - m]) << '\n';
		return 0;
	}
	for (int i = 0; i < k; i++) was[i] = false;
	int start = 0;
	while (start < m && !was[a[start]]) {
		was[a[start]] = true;
		++start;		
	}
	if (start == m) {
		int ans = 0;
		for (int left = 0; left + m <= n; left++) {
			int right = n - left - m;
			int gLeft = pw[left];
			int bLeft = getf(left, start);
			add(gLeft, MOD - bLeft);
			add(ans, mul(gLeft, pw[right]));
			// cout << "left = " << left << ", ans = " << ans << endl;
			int coef = 1;
			for (int j = start; j < k && j - start <= left; j++) {
				int jLeft = left - (j - start);
				int bLeft = jLeft == 0 ? 1 : f[jLeft - 1][j];
				int gRight = pw[right];
				add(gRight, MOD - getf(right, j));
				add(ans, mul(coef, mul(bLeft, gRight)));
				coef = mul(coef, k - j);
			}
			// cout << "left = " << left << ", ans = " << ans << endl;
		}
		cout << ans << '\n';
	} else {
		int startRight = 0;
		for (int i = 0; i < k; i++) was[i] = false;
		while (startRight < m && !was[a[m - startRight - 1]]) {
			was[a[m - startRight - 1]] = true;
			++startRight;
		}
		// cout << "start = " << start << ", startRight = " << startRight << endl;
		int ans = 0;
		for (int left = 0; left + m <= n; left++) {
			int gLeft = pw[left];
			int bLeft = getf(left, start);
			add(gLeft, MOD - bLeft);
			int right = n - left - m;
			int gRight = pw[right];
			add(gRight, MOD - getf(right, startRight));
			add(ans, mul(gLeft, pw[right]));
			// cout << getf(right, startRight) << endl;
			// cout << "left = " << left << ", ans = " << ans << endl;
			add(ans, mul(bLeft, gRight));
			// cout << "left = " << left << ", ans = " << ans << endl;
		}
		cout << ans << '\n';
	}
}
