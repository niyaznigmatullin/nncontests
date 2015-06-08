#include <bits/stdc++.h>

using namespace std;

int const N = 222;

int a[N][N], b[N][N];

void solve() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; i++) {
        int ones = 0;
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            a[i][j] = c == 'B';
            while (c > 32) c = getchar();
            ones += a[i][j];
        }
        if (ones * 2 < m) {
            for (int j = 0; j < m; j++) a[i][j] ^= 1;
        }
    }
    for (int i = 0; i < n; i++) {
        int ones = 0;
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            b[i][j] = c == 'B';
            while (c > 32) c = getchar();
            ones += b[i][j];
        }
        if (ones * 2 < m) {
            for (int j = 0; j < m; j++) b[i][j] ^= 1;
        }
    }
    bool bad = false;
    for (int i = 0; i < n; i++) {
        int c1 = 0;
        int c2 = 0;
        for (int j = 0; j < m; j++) c1 += a[i][j], c2 += b[i][j];
        if (c1 != c2) {
            puts("NO");
            return;
        }
        if (c1 * 2 == m) {
            bad = true;
        }
    }
    map<vector<int>, int > q2;
    for (int j = 0; j < m; j++) {
        vector<int> z;
        for (int i = 0; i < n; i++) {
            z.push_back(b[i][j]);
        }
        q2[z]++;
    }
    map<vector<int>, int > q1;
    for (int j = 0; j < m; j++) {
        vector<int> z;
        for (int i = 0; i < n; i++) {
            z.push_back(a[i][j]);
        }
        q1[z]++;
    }
    if (q1 == q2) {
        puts("YES");
        return;
    }
    if (bad) {
        puts("DONT KNOW");
        return;
    }
    puts("NO");
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
