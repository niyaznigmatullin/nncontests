#include <algorithm>
#include <cstdio>

int w;
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

long long dc[555555];
int dp[1 << 17];
int can[1 << 17];

int al(int c) {
    return c >= 'A' && c <= 'Z';
}

int main() {
    w = ni();
    int cnt[26];
    int word[20];
    for (int i = 0; i < w; i++) {
        int c = getchar();
        while (!al(c)) c = getchar();
        int len = 0;
        while (al(c)) {
            word[len++] = c - 'A' + 1;
            c = getchar();
        }
        std::sort(word, word + len);
        long long h = 0;
        for (int j = 0; j < len; j++) {
            h = h * 2397 + word[j];
        }
        dc[i] = h;
    }
    std::sort(dc, dc + w);
    int n = ni();
    n *= ni();
    int t = ni();
    for (int te = 0; te < t; te++) {
        int let[20];
        for (int i = 0; i < n; i++) {
            int c = getchar();
            while (!al(c)) c = getchar();
            let[i] = c - 'A' + 1;
        }
        std::sort(let, let + n);
        for (int i = 0; i < 1 << n; i++) {
            long long h = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 0) continue;
                h = h * 2397 + let[j];   
            }
            int ll = -1;
            int rr = w;
            while (ll < rr - 1) {
                int mid = (ll + rr) >> 1;
                if (dc[mid] > h) rr = mid; else
                ll = mid;   
            }
            can[i] = ll >= 0 && dc[ll] == h;
        }
        dp[(1 << n) - 1] = 0;
        for (int i = 0; i < 26; i++) cnt[i] = 0;
        for (int i = (1 << n) - 2; i >= 0; i--) {
            int gi = (1 << n) - 1 ^ i;
            int got = 0;
            for (int j = gi; j > 0; j = j - 1 & gi) {
                if (can[j] && !dp[i | j]) {
                    got = 1;
                    break;
                }
            }
            dp[i] = got;
        }
        puts(dp[0] ? "Alice" : "Bob");
    }
}