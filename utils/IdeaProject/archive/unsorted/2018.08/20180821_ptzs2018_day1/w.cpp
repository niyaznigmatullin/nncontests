
#include <bits/stdc++.h>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	int n;
	cin >> n;
	int l = 1;
	int r = n + 1;
	while (l < r - 1) {
		int mid = (l + r) >> 1;
		cout << "? " << mid << endl;
		string s;
		cin >> s;
		if (s == ">=") {
			l = mid;
		} else {
			r = mid;
		}
	}
	cout << "! " << l << endl;
}

