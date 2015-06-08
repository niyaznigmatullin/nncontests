#include <bits/stdc++.h>

using namespace std;

void solve() {
    int n;
    scanf("%d", &n);
    if (n == 1) {
        puts("1 1");
    } else if (n == 2) {
        puts("1 4");
    } else if (n <= 4) {
        puts("1 6");
    } else {
        puts("-1");
    }
}

int main() {
    int t;
//    scanf("%d", &t);
t = 1;
    while (t--) {
        solve();
    }
}
