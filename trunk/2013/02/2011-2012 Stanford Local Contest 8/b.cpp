#include <cstdio>
#include <algorithm>

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

const int MOD = 1000000007;

void solve(int n) {
    long long z = 1;
    for (int i = 2 * n; i > n; i--) {
        z = z * i % MOD;
        if (i != 2 * n) {
            z = 2 * z % MOD;
        }
    }
    printf("%d\n", z);
}

int main() {
    
    while (true) {
        int n = ni();
        if (n == 0) break;
        solve(n);
    }
}