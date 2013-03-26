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

int dp[111][111][111][111];
const int MOD = 1000000007;

int main() {
    int n = 12;    
    dp[0][0][0][0] = 1;
    for (int i = 1; i <= n; i++) {
        for (int a = 0; a <= i; a++) {
            
        }
    }
}