#include <cstdio>

const int BUF_SIZE = 512;

char buf[BUF_SIZE];
int cnt = 0;
int cur = 0;

int f() {
    if (cnt < 0) return -1;
    if (cur >= cnt) {
        cur = 0;
        cnt = fread(buf, 1, BUF_SIZE, stdin);
    }
    if (cur >= cnt) return cnt = -1;
    return buf[cur++];
}


int ni() {
    int c = f();
    while (c != '-' && (c < '0' || c > '9')) c = f();
    int sg = c == '-';
    if (sg) c = f();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = f();
    }
    return sg ? -ret : ret;
}
int nig() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    int sg = c == '-';
    if (sg) c = getchar();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

int main() {
    int n = ni();
    long long ans = 0;
    for (int i = 0; i < n; i++) ans += ni();
    printf("%I64d\n", ans);
}
