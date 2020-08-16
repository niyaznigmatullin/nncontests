/**
 * Sergey Kopeliovich (burunduk30@gmail.com)
 */

#include <bits/stdc++.h>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)
#define all(a) (a).begin(), (a).end()

int main() {
	int n;
	cin >> n;
	vector<int> a(n);
	forn(i, n)
		cin >> a[i];
	sor(all(a));
	int i = lower_bound(all(a), 2) - a.begin();
	cout << i << endl;
	for (int i = 0; i < n - 1; i++) {
		if (i == 3) continue;
		int j = i + 1;
		printf("%d %d %d\n", j, j, j);
		printf("%d %d %d\n", j, j, j);
		set<int> s;
		s.insert(j);
		for (int x : s) {
			printf("%d \n", x + 3);
			forn(i, 1 << x)
				forn(j, 10)
					if ((i >> j) & 1)
						cout << j << " " << endl;
		}
	}
}
