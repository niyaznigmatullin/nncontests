#include <cstdio>
#include <cmath>
#include <algorithm>
#include <iostream>

using namespace std;

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

int n;
string s[333];
double st[333];

void solve() {
    for (int i = 0; i < n; i++) cin >> s[i];
    int ok = 1;
    for (int it = 0; it < 200; it++) {
        double x = 1. * rand() / 10000;
        if (rand() & 1) x = -x;
        int cur = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == "x") st[cur++] = x; else
            if (s[i] == "sin") st[cur - 1] = sin(st[cur - 1]); else
            if (s[i] == "cos") st[cur - 1] = cos(st[cur - 1]); else
            if (s[i] == "tan") st[cur - 1] = tan(st[cur - 1]); else
            if (s[i] == "+") {
                st[cur - 2] += st[cur - 1];
                --cur;
            } else if (s[i] == "-") {
                st[cur - 2] -= st[cur - 1];
                --cur;
            } else if (s[i] == "*") {
                st[cur - 2] *= st[cur - 1];
                --cur;
            }
        }
        double y = st[0];
        if (y < 0) y = -y;
        if (y > 1e-9) {
            ok = 0;
            break;
        }
    }
    puts(ok ? "Identity" : "Not an identity");
}

int main() {
    while (true) {
        cin >> n;
        if (n == 0) break;
        solve();
    }
}