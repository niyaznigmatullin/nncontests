#include <bits/stdc++.h>

using namespace std;

int a[1234567];

int main() {
    int n = 0;
        int x;
    while (scanf("%d", &x) == 1) {
        a[n++] = x;
    }
    sort(a, a + n);
    int ansmin = 0;
    for (int i = 1; i < n; i++) if (a[i] != a[i - 1]) ++ansmin;
    int cnmax = 0;
    for (int i = 0; i < n; ) {
        int j = i;
        while (j < n && a[i] == a[j]) ++j;
        cnmax = max(cnmax, j - i);
        i = j;
    }
    int ansmax;
    if (cnmax <= (n + 1) / 2) ansmax = n - 1; else {
        ansmax = 0;
        for (int i = 1; i <= cnmax; i++) {
            int all = n - i;
            int f = cnmax - i;
            if (f <= (all + 1) / 2) {
                ansmax = n - 1 - i;
                break;
            }
        }
    }
    printf("%d %d\n", ansmin, ansmax);
}
