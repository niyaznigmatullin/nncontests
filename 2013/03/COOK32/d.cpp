#include <cstdio>
#include <algorithm>
#include <memory.h>
#include <set>

using std::set;
using std::pair;
using std::make_pair;

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

const int N = 333333;

int xb[N], yb[N], cnm[N], cnp[N], cm[N], f[N], ff[N];
int *bp[N], *bm[N];
int buf[10 * N];


void add(int x, int y) {
    for (int i = x; i < N; i |= i + 1) {
        f[i] += y;
    }
}

int get(int x) {
    int ret = 0;
    for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
        ret += f[i];
    }
    return ret;
}

int get(int l, int r) {
    return get(r - 1) - get(l - 1);
}

bool bx(int x, int y) {
    return xb[x] < xb[y];
}

int main() {
    int n = ni();
    int k = ni();
    int m = ni();
    for (int i = 0; i < k; i++) {
        xb[i] = ni() - 1;
        yb[i] = ni() - 1;
    }
    for (int i = k; i < m + k; i++) {
        xb[i] = ni() - 1;
        yb[i] = ni() - 1;
    }
    for (int i = 0; i < k + m; i++) {
        cnp[xb[i] + yb[i]]++;
        cnm[xb[i] - yb[i] + n - 1]++;
    }
    int * curb = buf;
    for (int i = 0; i < n + n - 1; i++) {
        bp[i] = curb;
        curb += cnp[i];
        cnp[i] = 0;
    }
    for (int i = 0; i < k + m; i++) {
        int j = xb[i] + yb[i];
        bp[j][cnp[j]] = i;
        ++cnp[j];
    }
    for (int i = 0; i < n + n - 1; i++) {
        bm[i] = curb;
        curb += cnm[i];
        cnm[i] = 0;
    }
    for (int i = 0; i < k + m; i++) {
        int j = xb[i] - yb[i] + n - 1;
        bm[j][cnm[j]] = i;
        ++cnm[j];
    }
    for (int i = 0; i < n + n - 1; i++) {
        std::sort(bp[i], bp[i] + cnp[i], bx);
        std::sort(bm[i], bm[i] + cnm[i], bx);
    }
    for (int i = 0; i < n + n - 1; i++) {
        if (cnm[i] > 0 && bm[i][0] < k) {
            add(i, 1);
            ff[i] = 1;
        }
    }
    long long com = 0;
    long long an1 = 0;
    long long an2 = 0;
    for (int i = 0; i < n + n - 1; i++) {
        int md = std::min(i, n + n - 2 - i);
        int last = -md + n - 1;
        --last;
        int was = 0;
        for (int j = 0; j < cnp[i]; j++) {
            if (bp[i][j] >= k) {
                int id = bp[i][j];
                int dif = xb[id] - yb[id] + n - 1;
                if (was) {
                    com += get(last + 1, dif);
                    an1 += dif - last - 1;
                }
                last = dif;
                was = 0;
            } else {
                was = 1;
            }
        }
        if (was) {
            com += get(last + 1, md + 1);
            an1 += md - last;
        }
        for (int j = 0; j < cnp[i]; j++) {
            if (bp[i][j] < k) continue;
            int id = bp[i][j];
            int dif = xb[id] - yb[id] + n - 1;
            while (1) {
                int & cc = cm[dif];
                if (cc >= cnm[dif]) break;
                int id = bm[dif][cc];
                int ss = xb[id] + yb[id];
                if (ss > i) break;
                ++cc;
            }
            if (cm[dif] < cnm[dif] && bm[dif][cm[dif]] < k) {
                add(dif, 1 - ff[dif]);
                ff[dif] = 1;
            } else {
                add(dif, -ff[dif]);
                ff[dif] = 0;
            }
        }
    }
    for (int i = 0; i < n + n - 1; i++) {
        int md = n + n - 2 - std::abs(i - n + 1);
        int nd = n + n - 2 - md;
        int last = nd - 1;
        int was = 0;
        for (int j = 0; j < cnm[i]; j++) {
            if (bm[i][j] < k) {
                was = 1;
            } else {
                int id = bm[i][j];
                int ss = xb[id] + yb[id];
                if (was) {
                    an2 += ss - last - 1;
                }
                last = ss;
                was = 0;
            }
        }
        if (was) {
            an2 += md - last;
        }
    }
    printf("%I64d %I64d\n", an1, an2);
    printf("%lld\n", an1 + an2 - com);
}
