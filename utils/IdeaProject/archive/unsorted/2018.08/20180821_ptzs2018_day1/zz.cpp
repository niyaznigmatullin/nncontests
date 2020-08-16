/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int i = 0; i < t; i++) {
		int n;
		cin >> n;
		int half = n / 2;
		long long ans = (long long) n  * (n + 1) / 2 - (long long) half * (half + 1) / 2;
		int l = 0;
		int r = n;
		while (l < r - 1) {
			int mid = (l + r) >> 1;
			if (mid / 2 * 3 <= n)
		}
	}
}