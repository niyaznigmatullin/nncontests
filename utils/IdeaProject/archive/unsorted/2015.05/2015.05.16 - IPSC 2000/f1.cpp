#include <bits/stdc++.h>

using namespace std;

int const N = 222;

int a[N][N], b[N][N];

void solve() {
    int n, m;
    scanf("%d%d", &n, &m);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            a[i][j] = c == 'B';
            while (c > 32) c = getchar();
        }
    }
    map<vector<int>, int > q2;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            b[i][j] = c == 'B';
            while (c > 32) c = getchar();
        }
    }
    for (int j = 0; j < m; j++) {
        vector<int> z;
        for (int i = 0; i < n; i++) {
            z.push_back(b[i][j]);
        }
        q2[z]++;
    }
    for (int mask = 0; mask < 1 << n; mask++) {
        map<vector<int>, int > q1;
        for (int j = 0; j < m; j++) {
            vector<int> z;
            for (int i = 0; i < n; i++) {
                z.push_back(a[i][j] ^ ((mask >> i) & 1));
            }
            q1[z]++;
        }
        if (q1 == q2) {
            puts("YES");
            return;
        }
    }
    puts("NO");
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
