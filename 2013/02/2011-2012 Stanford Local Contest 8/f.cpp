#include <cstdio>
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

int dp[1 << 18];

int tt[][3] = {{1, 2, 3}, {4, 5, 6}, {3, 5, 7}, {7, 8, 9}, {10, 11, 12}, {6, 11, 13}, {13, 14, 15}, {9, 14, 16}, {16, 17, 18}};
int t[10];
int n;
const int INF = ~(1 << 31);

void solve() {
    int mask = 0;
    for (int i = 0; i < n; i++) 
        mask |= 1 << (ni() - 1);
    if (dp[mask] == 0) puts("Draw"); else
    if (dp[mask] > 0) puts("Andy wins"); else
    puts("Ralph wins");
}

int main() {
    for (int i = 0; i < 9; i++) {
        int m = 0;
        for (int j = 0; j < 3; j++) m |= 1 << tt[i][j] - 1;
        t[i] = m;
    }
    dp[(1 << 18) - 1] = 0;
    for (int i = (1 << 18) - 2; i >= 0; i--) {
        int best = -INF;
        for (int j = 0; j < 18; j++) {
            if ((i >> j) & 1) continue;
            int cnt = 0;
            for (int k = 0; k < 9; k++) {
                if (((t[k] >> j) & 1) && ((i | (1 << j)) & t[k]) == t[k])
                    ++cnt;
            }
            if (cnt > 0) cnt = cnt + dp[i | (1 << j)]; else cnt = -dp[i | (1 << j)];
            if (cnt > best) best = cnt;
        }
        dp[i] = best;
    }
    while (true) {
        n = ni();
        if (n == 0) break;
        solve();
    }
}