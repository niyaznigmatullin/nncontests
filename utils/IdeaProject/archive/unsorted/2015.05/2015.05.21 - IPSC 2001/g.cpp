#include <bits/stdc++.h>

using namespace std;

unsigned int a[123456][123456 >> 5];

int main() {
    while (true) {
        int n, m;
        cin >> n >> m;
        assert(n < 123000);
        if (n == 0 && m == 0) break;
        unordered_map<string, int> w;
        for (int i = 0; i < n; i++) {
            string s;
            cin >> s;
            w[s] = i;
        }
        int sz = (n >> 5) + 1;
        for (int i = 0; i < n; i++) {
            fill(a[i], a[i] + sz, 0);
            a[i][i >> 5] |= 1 << i;
        }
        for (int it = 0; it < m; it++) {
            string s1, s2;
            cin >> s1 >> s2;
            int i = w[s1];
            int j = w[s2];
            if (i == j) continue;
            for (int k = 0; k < sz; k++) {
                a[i][k] |= a[j][k];
                a[j][k] |= a[i][k];
            }
        }
        bool ok = true;
        for (int i = 0; i < n; i++) {
            int cn = 0;
            for (int j = 0; j < sz; j++) {
                cn += __builtin_popcount(a[i][j]);
            }
            if (cn != n) {
                ok = false;
                break;
            }
        }
        puts(ok ? "YES" : "NO");
    }
}
