#include <cstdio>
const int N = 333;

int number[N], nlen, a[N], len, bad;
int best[N], blen, delta;

void dec() {
    for (int i = 0; i < nlen; i++) {
        if (number[i] != 0) {
            number[i]--;
            break; 
        }
        number[i] = 9;
    }
    if (number[nlen - 1] == 0) --nlen;
}

void inc() {
    if (nlen == 0) {
        number[nlen++] = 1;
        return;
    }
    for (int i = 0; i < nlen; i++) {
        if (number[i] != 9) {
            ++number[i];
            break;
        }
        number[i] = 0;
    }
    if (number[nlen - 1] == 0) {
        number[nlen++] = 1;
    }
}

void norm() {
    int car = 0;
    for (int k = 0; k < nlen; k++) {
        number[k] += car;
        car = number[k] / 10;
        number[k] -= car * 10;
    }
    while (car != 0) {
        number[nlen++] = car % 10;
        car /= 10;
    }
}

int check(int start) {
    if (nlen == 0) return 0;
    for (int i = 0; i < nlen; i++) {
        int j = i + start;
        if (j < 0 || j >= len) continue;
        if (a[j] != number[nlen - i - 1]) return 0;
    }
    return 1;
}

void mul(int a) {
    for (int i = 0; i < nlen; i++) {
        number[i] *= a;
    }
    norm();
}

void pr() {
    for (int k = nlen - 1; k >= 0; k--) putchar(number[nlen - k - 1] + '0');
    puts("");
}

void be(int i) {
                int glen = nlen;
                number[nlen - 1]--;
                while (number[nlen - 1] == 0) --nlen;
                mul(glen);
                glen -= 2;
                number[0]++;
                if (glen >= 0) number[0] += 9;
                int z = 1;
                for (int k = 0; k < glen; k++) {
                    if (z >= nlen) number[z] = 0;
                    number[z++] += 8;
                }
                while (glen > 0) {
                    if (z >= nlen) number[z] = 0;
                    number[z++] += glen % 10;
                    glen /= 10;
                }
                if (nlen < z) nlen = z;
                norm();
                int zz = i - delta;
                while (zz < 0) {
                    ++zz;
                    inc();
                }
                while (zz > 0) {
                    --zz;
                    dec();
                }
                int ok2 = nlen < blen;
                if (nlen == blen)
                    for (int k = nlen - 1; k >= 0; k--) {
                        if (best[k] < number[k]) {
                            ok2 = 0;
                            break;
                        }
                        if (best[k] > number[k]) {
                            ok2 = 1;
                            break;
                        }
                    }
                if (ok2) {
                    blen = nlen;
                    for (int k = 0; k < nlen; k++) best[k] = number[k];
                }

}


void sol() {
    for (int i = 0; i < len; i++) {
        if (a[i] == 0) continue;
        for (int j = i + 1; j <= len; j++) {
            bad = 0;
            nlen = j - i;
            for (int k = i; k < j; k++) {
                number[j - k - 1] = a[k];
            }
            dec();
            if ((nlen != 0 || i != 0) && i - nlen >= 0) continue;
            if ((nlen != 0 || i != 0) && !check(i - nlen)) continue;
            inc();
            int ok = 1;
            int cn = 0;
            for (int k = j; k < len; k += nlen) {
                inc();
                ++cn;
                if (!check(k)) {
                    ok = 0;
                    break;
                }
            }
            if (ok) {
                while (cn > 0) {
                    --cn;
                    dec();
                }
                be(i);
            }
        }
    }
    for (int i = 1; i < len; i++) {
        if (a[i] == 0) continue;
        for (int j = i + 1; j <= len; j++) {
            bad = 0;
            nlen = 0;
            for (int k = i - 1; k >= 0; k--) number[nlen++] = a[k];
            int glen = nlen;
            inc();
            nlen = glen;
            for (int k = j - 1; k >= i; k--) number[nlen++] = a[k];
            dec();
            int ok = 1;
            int cn = 0;
            for (int k = i - nlen; k < len; k += nlen) {
                ++cn;
                if (!check(k)) {
                    ok = 0;
                    break;
                }
                inc();
            }
            if (ok) {
                while (cn > 0) {
                    --cn;
                    dec();
                }
                be(i - j);
            }
        }
    }
}

int main() {
    len = 0;
    int c = getchar();
    while (c < '0' || c > '9') c = getchar();
    int zeros = 1;
    while (c >= '0' && c <= '9') {
        zeros &= c == '0';
        a[len++] = c - '0';
        c = getchar();
    }
    blen = 1 << 30;
    sol();
    for (int i = len; i >= 1; i--) a[i] = a[i - 1];
    ++len;
    a[0] = 1;
    delta = 1;
    sol();
    for (int i = blen - 1; i >= 0; i--) {
        putchar('0' + best[i]);
    }
    puts("");
}