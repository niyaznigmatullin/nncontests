#include <cstdio>
#include <memory.h>
#include <algorithm>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
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

int p[1234567], was[123];
const int MOD = 1000000007;

int main() {
//    int n = ni();
    for (int n = 1; n <= 12; n++) {
//    int k = ni();
    for (int i = 0; i < n; i++) p[i] = i;
    memset(was, 0, sizeof was);
    do {
        int cn = 0;
        for (int i = 0; i < n; i++) {
            if (p[i] == i - 1 || p[i] == i + 1) ++cn;
        }
        was[cn]++;
    } while (std::next_permutation(p, p + n));
    for (int i = 1; i <= n; i++) was[i] += was[i - 1];
    for (int i = 0; i <= n; i++) printf("%d\t", was[i]);
    puts("");
    }
}