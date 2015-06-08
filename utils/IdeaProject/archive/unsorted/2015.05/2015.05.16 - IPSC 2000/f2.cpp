#include <bits/stdc++.h>

using namespace std;

int const N = 2222;

int a[N][N], b[N][N];

int mask[N];

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
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            b[i][j] = c == 'B';
            while (c > 32) c = getchar();
        }
    }
    for (int x = 0; x < m; x++) {
        for (int y = 0; y < m; y++) {
            for (int i = 0; i < n; i++) {
                mask[i] = a[i][x] ^ b[i][y];
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
                    z.push_back(a[i][j] ^ mask[i]);
                }
                q1[z]++;
            }
            if (q1 == q2) {
                puts("YES");
                return;
            }
        }
    }
    puts("NO");
}

int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
