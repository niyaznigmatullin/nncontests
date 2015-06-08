#include <bits/stdc++.h>

using namespace std;

int const need[][10] = {
{0, 1, 2, 3, 1, 0, 1, 2, 3, 1},
{0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
{0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
};

string const str[] = {
"", "0", "00", "000", "01", "1", "10", "100", "1000", "02"
};

int const INF = 1 << 20;
int z[1234], mp[1234], f[1234];
int dp[123][42];

bool can(int i, int g, int d) {
    for (int j = 0; j < 3; j++) {
        if (f[i * 2 + j] < need[j][d] + (j == 0 ? g : 0)) return false;
    }
    return true;
}

void solve() {
    int c = getchar();
    while (c <= 32) c = getchar();
    for (int i = 0; i < 256; i++) mp[i] = -1;
    int cn = 0;
    while (c > 32) {
        z[cn++] = c;
        mp[c] = cn - 1;
        c = getchar();
    }
    while (c <= 32) c = getchar();
    while (c > 32) {
        if (mp[c] >= 0)
            f[mp[c]]++;
        c = getchar();
    }
    int pw = (cn + 1) / 2 + 6;
    for (int len = 0; len <= pw; len++) {
        fill(dp[len], dp[len] + 10, -INF);
    }
    for (int d = 0; d < 10; d++) if (can(0, 0, d)) {
        dp[0][d] = max(dp[0][d], (int) str[d].length());
    }
    for (int len = 1; len <= pw; len++) {
        for (int g = 0; g < 10; g++) {
            int val = dp[len - 1][g];
            if (val == -INF) continue;
            for (int d = 0; d < 10; d++) if (can(len, g == 9, d)) {
                dp[len][d] = max(dp[len][d], (int) str[d].length() + val);
            }
        }
    }
    string ans = "";
    int curd = 0;
    for (int i = pw - 1; i >= 0; i--) {
        for (int d = 9; d >= 0; d--) if (can(i + 1, d == 9, curd) && dp[i + 1][curd] == dp[i][d] + (int) str[curd].length()) {
            for (int j = 0; j < (int) str[d].length(); j++) {
                ans += (char) (z[i * 2 + str[d][j] - '0']);
            }
            curd = d;
            break;
        }
    }
    puts(ans.c_str());
}

int main() {
    int t;
//    scanf("%d", &t);
    t = 1;
    while (t--) {
        solve();
    }
}
