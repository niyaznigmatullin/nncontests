/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

void solve(int test) {
	string s;
	cin >> s;
	int balance = 0;
	string ans = "";
	for (char c : s) {
		int x = c - '0';
		while (balance < x) {
			ans += "(";
			balance++;
		}
		while (balance > x) {
			ans += ")";
			balance--;
		}
		ans += c;
	}
	while (balance > 0) {
		ans += ")";
		balance--;
	}
	cout << "Case #" << test << ": " << ans << '\n';
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int ct = 1; ct <= t; ct++) {
		solve(ct);
	}	
}