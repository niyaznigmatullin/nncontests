#include <iostream>
#include <memory.h>

using namespace std;

int ln[100][1000000];

void dfs(int v, string s) {
    int cnt = 0;
    for (int i = 0; cnt < 2 && i < 100; i++) {
        if (ln[i][v] >= 0) cnt++;
    }
    if (cnt != 1 && s != "") {
        cout << s << "\n";
        s = "";
    }
    for (int i = 0; i < 100; i++) {
        int u = ln[i][v];
        if (u < 0) continue;
        dfs(u, s + (char) i);
    }
}

int main() {
    int fr = 1;
    string str;
    cin >> str;
    memset(ln, -1, sizeof ln);
    for (int start = 0; start < (int) str.length(); start++) {
        string s = str.substr(start);
        int n = s.length();
        int v = 0;
        for (int i = 0; i < n; i++) {
            int c = s[i];
            if (ln[c][v] < 0) {
                ln[c][v] = fr++;
            }
            v = ln[c][v];
        }
    }
    dfs(0, "");
}
