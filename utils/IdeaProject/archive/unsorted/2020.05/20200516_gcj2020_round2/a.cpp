/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

long long find_root(long long n) {
	long long left = 0;
	long long right = (long long) sqrt(2.0 * n);
	while (left < right - 1) {
		long long mid = (left + right) >> 1;
		if (mid * (mid + 1) <= 2 * n) {
			left = mid;
		} else {
			right = mid;
		}
	}
	while ((left + 1) * (left + 2) <= 2 * n) ++left;
	return left;
}

// tuple<long long, long long, long long> stupid(long long L, long long R) {
// 	int x = 1;
// 	for (; x <= L || x <= R; x++) {
// 		if (L >= R) {
// 			L -= x;
// 		} else {
// 			R -= x;
// 		}
// 	}
// 	// cerr << x - 1 << ' ' << L << ' ' << R << '\n';
// 	return {x - 1, L, R};
// }

void solve(int test) {
	long long L, R;
	cin >> L >> R;
	// tuple<long long, long long, long long> sans = stupid(L, R);
	bool flip = false;
	if (L < R) {
		flip = true;
		swap(L, R);
	}
	long long x = find_root(L - R);
	L -= x * (x + 1) / 2;
	if (L == R) {
		flip = false;
	}
	long long left = 0;
	long long right = find_root(L) + 1;
	while (left < right - 1) {
		long long mid = (left + right) >> 1;
		long long have = mid * mid + x * mid;
		if (have > L) {
			right = mid;
		} else {
			left = mid;
		}
	}
	L -= left * left + x * left;
	R -= left * (left + 1) + x * left;
	long long ans = x + 2 * left;
	assert(L >= 0);
	if (R < 0) {
		R += ans;
		--ans;
	}
	if (flip) swap(L, R);
	// assert(make_tuple(ans, L, R) == sans);
	// if (test % 10000 == 0)
	cout << "Case #" << test << ": " << ans << ' ' << L << ' ' << R << "\n";
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}