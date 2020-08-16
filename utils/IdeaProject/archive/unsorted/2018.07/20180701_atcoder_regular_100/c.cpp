#include <bits/stdc++.h>


using namespace std;

int a[1234567];

int main() {
	ios::sync_with_stdio(false);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> a[i];
		a[i] -= i;
	}
	sort(a, a + n);
	int x = a[n / 2];
	long long ans = 0;
	for (int i = 0; i < n; i++) ans += std::abs(x - a[i]);
		cout << ans << endl;
}