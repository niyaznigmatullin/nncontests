#include <cstdio>
#include <cstdlib>
#include <cassert>
#include <algorithm>

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

const int TN = 333333;
int fx[TN], fy[TN];
int xs[TN], ys[TN];
int bx[TN], mx, my, n, bq[TN];
int xq[TN], yq[TN], dq[TN];
int ansq[TN];

bool cmpx(int a, int b) {
    return xs[a] < xs[b] || xs[a] == xs[b] && ys[a] > ys[b];
}

bool cmpxy(int a, int b) {
    return xs[a] + ys[a] < xs[b] + ys[b];
}

bool cmpqxy(int a, int b) {
    int x = xq[a] + yq[a] + dq[a];
    int y = xq[b] + yq[b] + dq[b];
    return x < y;
}

bool cmpqx(int a, int b) {
    return xq[a] < xq[b] || xq[a] == xq[b] && yq[a] > yq[b];
}

void mod(int * f, int sz, int x) {
    for (int i = x; i < sz; i |= i + 1) {
        f[i]++;
    }
}

int gSum(int * f, int x) {
    int ret = 0;
    for (int i = x; i >= 0; i = (i & i + 1) - 1) {
        ret += f[i];
    }
    return ret;
}

int main() {
    n = ni();
    int q = ni();
    mx = 0;
    my = 0;
    for (int i = 0; i < n; i++) {
        xs[i] = ni();
        ys[i] = ni();
        bx[i] = i;
        if (mx < xs[i]) mx = xs[i];
        if (my < ys[i]) my = ys[i];
    }
    for (int i = 0; i < q; i++) {
        xq[i] = ni();
        yq[i] = ni();
        dq[i] = ni();
        bq[i] = i;
        if (mx < xq[i]) mx = xq[i];
        if (my < yq[i]) my = yq[i];
    }
    ++mx;
    ++my;
    for (int i = 0; i < my; i++) fy[i] = 0;
    for (int i = 0; i < mx; i++) fx[i] = 0;
    std::sort(bx, bx + n, cmpx);
    std::sort(bq, bq + q, cmpqx);
    for (int it = 0, j = 0; it < q; it++) {
        int i = bq[it];
        while (j < n && xq[i] > xs[bx[j]]) {
            mod(fy, my, ys[bx[j]]);
            ++j;
        }
        ansq[i] = gSum(fy, yq[i] - 1);
    }
    std::sort(bx, bx + n, cmpxy);
    std::sort(bq, bq + q, cmpqxy);
    for (int i = 0; i < my; i++) fy[i] = 0;
    for (int it = 0, j = 0; it < q; it++) {
        int i = bq[it];
        int ss = xq[i] + yq[i] + dq[i];
        while (j < n && xs[bx[j]] + ys[bx[j]] <= ss) {
            mod(fy, my, my - 1 - ys[bx[j]]);
            mod(fx, mx, mx - 1 - xs[bx[j]]);
            ++j;
        }
        ansq[i] -= j;
        ansq[i] += gSum(fy, my - 1 - yq[i]);
        ansq[i] += gSum(fx, mx - 1 - xq[i]);
    }
    char num[20];
    for (int i = 0; i < q; i++) {
        int x = ansq[i];
        assert(x >= 0);
        int cc = 0;
        if (x == 0) num[cc++] = '0';
        while (x > 0) {
            int z = x / 10;
            num[cc++] = x - z * 10 + '0';
            x = z;
        }
        while (cc > 0) putchar(num[--cc]);
        putchar('\n');
    }
}