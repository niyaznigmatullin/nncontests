/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int ask(int x, int y) {
	cout << x << ' ' << y << endl;
	// cerr << "ASK " << x << " " << y << endl;
	string got;
	cin >> got;
	// cerr << "REPLY " << got << endl;
	if (got == "CENTER") return 2;
	if (got == "HIT") return 1;
	return 0;
}

void solve(int) {
	int A = 1000000000 / 2;
	int B = 1000000000;
	int fx = B + 1;
	int fy = B + 1;
	for (int dx = -1; dx <= 1; dx++) {
		for (int dy = -1; dy <= 1; dy++) {
			int x = dx * A;
			int y = dy * A;
			int got = ask(x, y);
			if (got == 2) {
				return;
			}
			if (got == 1) {
				fx = x;
				fy = y;
				break;
			}
		}
		if (fx != B + 1) break;
	}
	assert(fx != B + 1);
	int left = -B - 1;
	int right = fx;
	while (left < right - 1) {
		int mid = (left + right) / 2;
		int got = ask(mid, fy);
		if (got == 2) return;
		if (got == 1) {
			right = mid;
		} else {
			left = mid;
		}
	}
	int lx = right;
	left = fx;
	right = B + 1;
	while (left < right - 1) {
		int mid = (left + right) / 2;
		int got = ask(mid, fy);
		if (got == 2) return;
		if (got == 1) {
			left = mid;
		} else {
			right = mid;
		}
	}
	int rx = left;
	fx = (lx + rx) / 2;
	left = -B - 1;
	right = fy;
	while (left < right - 1) {
		int mid = (left + right) / 2;
		int got = ask(fx, mid);
		if (got == 2) return;
		if (got == 1) {
			right = mid;
		} else {
			left = mid;
		}
	}
	int ly = right;
	left = fy;
	right = B + 1;
	while (left < right - 1) {
		int mid = (left + right) / 2;
		int got = ask(fx, mid);
		if (got == 2) return;
		if (got == 1) {
			left = mid;
		} else {
			right = mid;
		}
	}
	int ry = left;
	fy = (ly + ry) / 2;
	assert(ask(fx, fy) == 2);
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	int a, b;
	cin >> a >> b;
	for (int i = 1; i <= t; i++) {
		solve(i);
	}	
}