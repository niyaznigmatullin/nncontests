#include <cstdio>

long long nl() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    long long ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

int beat(int c) {
    return c == 'R' ? 'P' : c == 'P' ? 'S' : 'R';
}

int ubeat(int c) {
    return c == 'R' ? 'S' : c == 'P' ? 'R' : 'P';
}

long long pw[30];

int go(long long n) {
    if (n == 0) return 'R';
    if (n == 1) return 'P';
    if (n == 2) return 'S';
    int i = 0;
    while (pw[i + 1] <= n) ++i;
    long long z = n / pw[i];
    if (z == 0) return go(n); else
    if (z == 1) return beat(go(n - pw[i])); else
    return ubeat(go(n - 2 * pw[i]));
}

void solve(long long n) {
    char ans = beat(go(n));
    printf("%c\n", ans);
}

int main() {
    pw[0] = 1;
    for (int i = 1; i < 30; i++) pw[i] = 3 * pw[i - 1];
    while (true) {
        long long n = nl();
        if (n == 0) break;
        solve(n - 1);
    }
}