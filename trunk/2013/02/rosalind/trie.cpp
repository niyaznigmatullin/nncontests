#include <iostream>
#include <memory.h>

using namespace std;

int ln[100][100000];

int main() {
    int fr = 1;
    string s;
    memset(ln, -1, sizeof ln);
    while (cin >> s) {
        int n = s.length();
        int v = 0;
        for (int i = 0; i < n; i++) {
            int c = s[i];
            if (ln[c][v] < 0) {
                ln[c][v] = fr++;
                cout << v + 1 << " " << fr << " " << s[i] << "\n";
            }
            v = ln[c][v];
        }
    }
}