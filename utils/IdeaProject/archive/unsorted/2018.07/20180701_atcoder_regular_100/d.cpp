#include <bits/stdc++.h>


using namespace std;

long long a[1234567];

long long get(int l,  int r) {
	long long ret = a[r - 1];
	if (l > 0) ret -= a[l - 1];
	return ret;
}
int main() {
	ios::sync_with_stdio(false);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) cin >> a[i];
	for (int i = 1; i < n; i++) a[i] += a[i - 1];
	for (int second = 2; second + 1 < n; second++) {
		int left = 0;
		int right = second - 1;
		while (left < right - 1) {
			int mid = (left + right) >> 1;
			if (get(0, mid) > get(mid, second)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		int x = left;
		left = second;
		right = n - 1;
		while (left < right - 1) {
			int mid = (left + right) >> 1;
			if (get(second, mid) > get(mid, n)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		int y = left;
		for (int i = x; i <= x + 1; i++) {
			if (i )
			for (int j = y; j <= y + 1; j++) {

			}
		}
	}
}