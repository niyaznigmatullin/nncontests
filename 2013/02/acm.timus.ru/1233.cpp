#include <cstdio>


int ni() {
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

typedef unsigned long long ull;

ull dlen(ull n) {
    return n > 0 ? 1 + dlen(n / 10) : 0;
}

ull place2(ull prefix, ull n) {
    ull cnt = 1;
    ull ret = 0;
    while (prefix <= n) {
        if (prefix + cnt - 1 <= n) {
            ret += cnt;
        } else {
            ret += n - prefix + 1;
            break;
        }
        prefix *= 10;
        cnt *= 10;
    }
    return ret;
}

ull place(ull k, ull n) {
    int len = dlen(k);
    int number[22];
    for (int i = 0; i < len; i++) {
        number[len - i - 1] = k % 10;
        k /= 10;
    }
    ull prefix = 0;
    ull ret = 0;
    for (int i = 0; i < len; i++) {
        for (int d = 0; d < number[i]; d++) {
            if (i == 0 && d == 0) continue;
            ret += place2(prefix * 10 + d, n);
        }
        prefix = prefix * 10 + number[i];
    }
    ret += len - 1;
    return ret;
}

int main() {
    ull k = ni();
    ull m = ni() - 1;
    if (place(k, k) > m) {
        puts("0");
        return 0;
    }
    ull l = k - 1;
    ull r = -1;
    int ok = 0;
    while (l < r - 1) {
        ull mid = (l + ((r - l) >> 1));
        if (place(k, mid) >= m) {
            r = mid;
            ok = 1;
        } else l = mid;
    }
    printf("%I64u\n", ok ? r : 0);
}
