/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

struct rect {
	int x1, y1, x2, y2;
};

rect readR() {
	int x1, y1, x2, y2;
	cin >> x1 >> y1 >> x2 >> y2;
	return {x1, y1, x2, y2};
}

bool eq(rect a, rect b) {
	return a.x1 == b.x1 && a.x2 == b.x2 && a.y1 == b.y1 && a.y2 == b.y2;
}

bool inside(rect a, rect b) {
	return a.x1 <= b.x1 && b.x2 <= a.x2 && a.y1 <= b.y1 && b.y2 <= a.y2;
}

bool intersect(rect a, rect b) {
	return max(a.x1, b.x1) < min(a.x2, b.x2) && max(a.y1, b.y1) < min(a.y2, b.y2);
}

int cut(rect a, rect b) {
	bool sx = a.x1 < b.x1 && b.x2 < a.x2;
	bool sy = a.y1 < b.y1 && b.y2 < a.y2;
	bool ix = b.x1 <= a.x1 && a.x2 <= b.x2;
	bool iy = b.y1 <= a.y1 && a.y2 <= b.y2;
	if ((sx && iy) || (sy && ix)) {
		return 3;
	} else if (inside(b, a)) {
		return 1;
	} else {
		return 2;
	}
}

void solve() {
	rect a = readR();
	rect b = readR();
	if (eq(a, b)) {
		cout << "2\n";
	} else if (!intersect(a, b)) {
		cout << "3\n";
	} else {
		cout << cut(a, b) + cut(b, a) << '\n';
	}
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	while (t--) {
		solve();
	}	
}
