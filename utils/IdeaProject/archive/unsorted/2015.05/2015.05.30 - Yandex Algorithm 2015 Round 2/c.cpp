#include <bits/stdc++.h>

using namespace std;

int a[42];

void solve() {
    int c = getchar();
    while (c <= 32) c = getchar();
    for (int i = 0; i < 26; i++) a[i] = 0;
    while (c > 32) {
        a[c - 'a']++;
        c = getchar();
    }
    int mx = 0;
    int mx2 = 0;
    int sum = 0;
    for (int i = 0; i < 26; i++) {
        if (mx < a[i]) {
            mx2 = mx;
            mx = a[i]; 
        } else if (mx2 < a[i]) {
            mx2 = a[i];            
        }
        sum += a[i];
    }
    if (mx == 1) {
        if (sum % 2 == 1) {
            puts("2");
            return;
        } else {
            for (int i = 0; i < 26; i++) {
                if (a[i] == 1) {
                    printf("1 %c\n", (char) (i + 'a'));
                    return;
                }
            }
            assert(false);
        } 
    } else if (mx2 <= 1) {
        for (int i = 0; i < 26; i++) {
            if (a[i] > 1) {
                printf("1 ");
                int rest = sum - a[i];
                if (rest % 2 == 1) {
                    for (int j = 0; j < a[i]; j++) putchar(i + 'a');
                } else {
                    for (int j = 1; j < a[i]; j++) putchar(i + 'a');
                }
                puts("");
                return;
            }
        }
        assert(false);
    } else {
        int xr = 0;
        for (int i = 0; i < 26; i++) xr ^= a[i];
        if (xr == 0) {
            puts("2");
            return;
        }
        for (int i = 0; i < 26; i++) {
            int need = xr ^ a[i];
            if (need < a[i]) {
                printf("1 ");
                for (int j = need; j < a[i]; j++) {
                    putchar(i + 'a');
                }
                puts("");
                return;
            }
        }
        assert(false);
    }
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
