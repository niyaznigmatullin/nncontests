/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	string ss;
	getline(cin, ss);
	int k;
	{
		istringstream qs(ss);
		qs >> k;
	}
	vector<string> s;
	getline(cin, ss);
	{
		istringstream qs(ss);
		string cur;
		while (qs >> cur) {
			s.push_back(cur);
		}
	}
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		vector<int> a;
		vector<int> values(k);
		for (int j = 0; j < k; j++) {
			cin >> values[j];
		}
		for (auto &e : s) {
			if (string("+-*/<=>").find(e) != string::npos) {
				int second = a.back();
				a.pop_back();
				int first = a.back();
				a.pop_back();
				if (e == "+") {
					a.push_back(first + second);
				} else if (e == "-") {
					a.push_back(first - second);
				} else if (e == "*") {
					a.push_back(first * second);
				} else if (e == "/") {
					a.push_back(first / second);
				} else if (e == "<") {
					a.push_back(first < second);
				} else if (e == "=") {
					a.push_back(first == second);
				} else if (e == ">") {
					a.push_back(first > second);
				}
			} else if (e == "?") {
				int third = a.back();
				a.pop_back();
				int second = a.back();
				a.pop_back();
				int first = a.back();
				a.pop_back();
				a.push_back(first ? second : third);
			} else {
				if (e[0] >= 'a' && e[0] <= 'z') {
					a.push_back(values[e[0] - 'a']);
				} else {
					a.push_back(atoi(e.c_str()));
				}
			}
			// for (int q : a) {
			// 	cout << q << ' ';
			// }
			// cout << '\n';
		}
		cout << a.back() << '\n';
	}
}
