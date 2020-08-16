/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int test) {
	int n;
	cin >> n;
	string s;
	cin >> s;
	int countA = 0;
	int countB = 0;
	for (int i = 0; i < n; i++) {
		if (s[i] == 'A') countA++;
		if (s[i] == 'B') countB++;
	}
	cout << "Case #" << test << ": ";
	if (abs(countA - countB) > 1) {
		cout << "N\n";
		return;
	}
	cout << "Y\n";
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}
