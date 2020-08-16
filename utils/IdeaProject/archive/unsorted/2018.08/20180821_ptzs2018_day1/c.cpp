/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);

	long long tmp;
	cin >> tmp;
	__int128 n = tmp;
	vector<__int128> a(n);
	__int128 sum = 0;
	__int128 sumi = 0;
	int cnt = 0;
	__int128 sumudegu = 0;
	for (int i = 0; i < n; i++) {
		cin >> tmp, a[i] = tmp;
		if (a[i] == -1) {
			sumi += i + 1;
			cnt++;
		} else {
			sum += a[i];
			sumudegu += a[i] * (i + 1);
		}
	}
	if (cnt > 0) {
		sum = 2 * n - 2 - sum;
		sumudegu = (sumudegu * cnt + sum * sumi) * n / cnt;
	} else {
		sumudegu *= n;
	}

	__int128 ans = sumudegu - n * (n + 1) * (n - 1) / 2;
	cout << (long long)ans << endl;
}