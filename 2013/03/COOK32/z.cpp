#include <string> 
#include <set>
#include <iostream>

using namespace std;

int main() {
    string s, t;
    cin >> s >> t;
    set<string> sa;
    set<string> sb;
    for (size_t i = 0; i < s.length(); i++) {
        for (size_t j = i + 1; j <= s.length(); j++) {
            sa.insert(s.substr(i, j - i));
        }
    }
    for (size_t i = 0; i < t.length(); i++) {
        for (size_t j = i + 1; j <= t.length(); j++) {
            sb.insert(t.substr(i, j - i));
        }
    }
    int com = 0;
    for (set<string>::iterator it = sa.begin(); it != sa.end(); ++it) {
        if (sb.find(*it) != sb.end()) {
            ++com;
        }
    }
    cout << (int) sa.size() + sb.size() - 2 * com << "\n";
}