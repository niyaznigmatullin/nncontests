/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void sort(vector<int> &a, int left, int right, vector<long long> &f) {
	if (left == right - 1) {
		return;
	}
	int mid = (left + right) >> 1;
	sort(a, left, mid, f);
	sort(a, mid, right, f);
	vector<int> b(right - left);
	for (int i = left, j = mid, k = 0; i + j < mid + right; k++) {
		if (i >= mid || (j < right && a[i] > a[j])) {
			f[a[j]] += mid - i;
			b[k] = a[j++];
		} else {
			b[k] = a[i++];
		}
	}
	for (int i = left; i < right; i++) {
		a[i] = b[i - left];
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	freopen("haircut.in", "r", stdin);
	freopen("haircut.out", "w", stdout);
	int n;
	cin >> n;
	vector<int> a(n);
	for (int i = 0; i < n; i++) {
		cin >> a[i];
	}
	vector<long long> f(n + 1);
	sort(a, 0, n, f);
	long long ans = 0;
	for (int i = 0; i < n; i++) {
		cout << ans << '\n';
		ans += f[i];
	}
}
