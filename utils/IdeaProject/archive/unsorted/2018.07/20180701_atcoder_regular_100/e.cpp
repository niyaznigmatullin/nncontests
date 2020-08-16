#include <bits/stdc++.h>
using namespace std;

int const N = (1 << 18) + 5;
pair<int, int> a[N], b[N];

int c[N];

void mxx(pair<int, int> &a1, pair<int, int> &a2, pair<int, int> a3, pair<int, int> a4) {
	pair<int, int> e[4];
	e[0] = a1;
	e[1] = a2;
	e[2] = a3;
	e[3] = a4;
	sort(e, e + 4);
	int cn = unique(e, e + 4) - e;
	a1 = e[cn - 1];
	a2 = e[cn - 2];
}


int main() {
	int n;
	ios::sync_with_stdio(false);
	cin >> n;
	for (int i = 0; i < (1 << n); i++) {
		cin >> a[i].first;
		a[i].second = i;
		b[i] = {0, -1};
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < (1 << n); j++) {
			if (((j >> i) & 1) == 0) {
				int to = j | (1 << i);
				mxx(a[to], b[to], a[j], b[j]);
			}
		}
	}
	for (int i = 0; i < (1 << n); i++) c[i] = a[i].first + b[i].first;
	for (int i = 1; i < (1 << n); i++) c[i] = std::max(c[i], c[i - 1]);
	for (int i = 1; i < (1 << n); i++) {
		cout << c[i] << '\n';
	}
}
