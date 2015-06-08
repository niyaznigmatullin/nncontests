#include <bits/stdc++.h>

void solve() {
    int n;
    scanf("%d", &n);
    int sum = 0;
    int mn = 1 << 30;
    int bad = 0;
    for (int i = 0; i < n; i++) {
        int x;
        scanf("%d", &x);
        if (x < 2) bad = 1;
        if (mn > x) mn = x;
        sum += x;
    }
    if (bad) puts("-1"); else
        printf("%d\n", sum - (mn - 2));    
}

int main() {
    int tt;
    scanf("%d", &tt);
    while (tt--) solve();
}
