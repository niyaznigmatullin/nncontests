#include <bits/stdc++.h>

int const N = 123467;

int s[N];

void solve() {
    int n, k;
    scanf("%d%d", &n, &k);
    for (int i = 0; i < n; i++) {
        int c = getchar();
        while (c <= 32) c = getchar();
        s[i] = c - '0';
    }
    if (k == 1) {
        int bestStart = -1;
        int best = n + 1;
        for (int start = 0; start < 2; start++) {
            int cur = 0;
            for (int i = 0; i < n; i++) {
                int need = 1 & (start ^ i);
                if (s[i] != need) {
                    ++cur;
                }
            }
            if (cur < best) {
                best = cur;
                bestStart = start;
            }
        }
        printf("%d\n", best);
        for (int i = 0; i < n; i++) putchar('0' + ((i ^ bestStart) & 1));
        puts("");
        return;
    }
    int ans = 0;
    for (int i = 0; i < n; ) {
        int j = i;
        while (j < n && s[i] == s[j]) ++j;
        while (j - i > k) {
            ++ans;
            if (j - i == k + 1) s[i + 1] ^= 1; else
                s[i + k] ^= 1;
            i += k + 1;
        }
        i = j;
    }
    printf("%d\n", ans);
    for (int i = 0; i < n; i++) putchar(s[i] + '0');
    puts("");
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
