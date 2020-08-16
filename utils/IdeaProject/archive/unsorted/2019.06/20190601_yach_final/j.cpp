/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	string s;
	cin >> s;
	bool mp = false;
	if (s[0] == '{' || s[s.size() - 1] == '}') {
		mp = true;
	}
	if (!mp) {
		if (s[0] != '"') {
			cout << "\"" + s << endl;
			return 0;
		}
		if (s[s.size() - 1] != '"') {
			cout << s + "\"" << endl;
			return 0;
		}
		cout << s.substr(0, 1) + "a" + s.substr(1) << endl;
		return 0;
	}
	if (s[0] != '{') {
		cout << "{" + s << endl;
		return 0;
	}
	if (s[s.size() - 1] != '}') {
		cout << s + "}" << endl;
		return 0;
	}
	int commas = 0;
	int colons = 0;
	for (char c : s) {
		if (c == ',') commas++;
		if (c == ':') colons++;
	}
	if (commas != colons - 1) {
		for (int i = 0; i + 1 < (int) s.size(); i++) {
			if (s[i] == '"' && s[i + 1] == '"') {
				if (commas + 1 == colons - 1) {
					cout << s.substr(0, i + 1) + "," + s.substr(i + 1) << endl;
					return 0;
				} else if (commas == colons) {
					cout << s.substr(0, i + 1) + ":" + s.substr(i + 1) << endl;
					return 0;
				} else assert(0);
			}
		}
	}
	if (s[1] != '"') {
		cout << s.substr(0, 1) + "\"" + s.substr(1) << endl;
		return 0;
	}
	if (s[s.size() - 2] != '"') {
		cout << s.substr(0, s.size() - 2) + "\"" + s.substr(s.size() - 1) << endl;
		return 0;
	}
	for (int i = 0; i < (int) s.size(); i++) {
		if (s[i] == ',' || s[i] == ':') {
			if (s[i - 1] != '"') {
				cout << s.substr(0, i) + "\"" + s.substr(i) << endl;
			}
			if (s[i + 1] != '"') {
				cout << s.substr(0, i + 1) + "\"" + s.substr(i + 1) << endl;
			}
		}
	}
	cout << s.substr(0, 2) + "a" + s.substr(2) << endl;
}