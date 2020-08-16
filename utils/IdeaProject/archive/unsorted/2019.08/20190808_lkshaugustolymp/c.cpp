/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int get(int n, int a, int b) {
	n -= a + b;
	while (n >= 0 && n % a != 0) {
		n -= a + b;
	}
	if (n <= 0) return 0;
	n -= a;
	if (n < 0) return 0;
	int z = n / (a + b);
	z = z / a + 1;
	return z;
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int a1 = 1;
	int b1 = 0;
	int a2 = 0;
	int b2 = 1;
	int n;
	cin >> n;
	int ans = 0;
	for (int i = 0; i < 30; i++) {
		int a3 = a1 + a2;
		int b3 = b1 + b2;
		if (a3 + b3 > n) break;
		ans += get(n, a3, b3);
		ans += get(n, b3, a3);
		if (n % (a3 + b3) == 0) {
			ans++;
		}
		a1 = a2;
		b1 = b2;
		a2 = a3;
		b2 = b3;
	}	
	cout << ans << endl;
}