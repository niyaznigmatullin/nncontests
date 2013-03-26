#include <iostream>

using std::cin;
using std::cout;


int c[2222][2222];

int main() {
    int n, m;
    cin >> n >> m;
    int mod = 1000000;
    for (int i = 0; i < 2222; i++) {
        c[i][0] = 1;
        for (int j = 1; j <= i; j++) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            c[i][j] %= mod;
        }
    }
    int ans = 0;
    for (int i = m; i <= n; i++) {
        ans += c[n][i];
        ans %= mod;
    }
    cout << ans << std::endl;
}