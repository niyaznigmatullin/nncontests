#include <bits/stdc++.h>

using namespace std;


void solve() {
    long long d;
    scanf("%lld", &d);
    int two = 0;
    while (d % 2 == 0) {
        d /= 2;
        two++;
    }
    int five = 0;
    while (d % 5 == 0) {
        d /= 5;
        five++;
    }
    if (d != 1) {
        puts("impossible");
        return;
    }
    if (two < five) two = five;
    printf("%d\n", two);
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
