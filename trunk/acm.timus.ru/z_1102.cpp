#include <iostream>
#include <cstdio>
#include <string>

using namespace std;

bool ok(string const &);

    void solve() {
        int n;
        cin >> n;
        string t;
        getline(cin, t);
		for (int i = 0; i < n; i++) {
			string s;
            getline(cin, s);
			puts(ok(s) ? "YES" : "NO");
		}
    }

	bool ok(string const & s) {
		for (int i = s.length() - 1; i >= 0;) {
			char ch = s[i];
			if (ch == 'n') {
				if (i == 0) {
					return false;
				}
				char ch2 = s[i - 1];
				if (ch2 == 'i') {
					i -= 2;
				} else {
					if (i < 4 || ch2 != 'o' || s[i - 2] != 't'
							|| s[i - 3] != 'u' || s[i - 4] != 'p') {
						return false;
					}
					i -= 5;
				}
			} else if (ch == 'e') {
				if (i < 2 || s[i - 1] != 'n' || s[i - 2] != 'o') {
					return false;
				}
				i -= 3;
			} else if (ch == 't') {
				if (i < 2 || s[i - 1] != 'u') {
					return false;
				}
				char ch2 = s[i - 2];
				if (ch2 == 'o') {
					i -= 3;
				} else {
					if (ch2 != 'p' || i < 4) {
						return false;
					}
					char ch3 = s[i - 3];
					if (ch3 == 'n') {
						if (s[i - 4] != 'i') {
							return false;
						}
						i -= 5;
					} else if (ch3 == 't') {
						if (i < 5 || s[i - 4] != 'u'
								|| s[i - 5] != 'o') {
							return false;
						}
						i -= 6;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}


int main() {
    solve();
}