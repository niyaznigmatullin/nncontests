#include <cstdio>
#include <algorithm>
#include <iostream>

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

int ss[222222], ff[222222], w[222222], ne[22222];
int last[11111], was[11111], sum[11111];
double dp1[11111], dp2[11111], prob[11111], ex[11111];
int n, m;

void go(int v) {
    if (was[v]) return;
    was[v] = 1;
    dp1[v] = 1e20;
    for (int e = last[v]; e >= 0; e = ne[e]) {
        double p = (1. * w[e] / sum[v]);
        prob[ff[e]] += p * prob[v];
        ex[ff[e]] += ex[v] + p * w[e];
        go(ff[e]);
        dp1[v] += p * (w[e] + dp1[ff[e]]);
    }
}

void solve() {
    for (int i = 0; i < m; i++) {
        ss[i] = ni();
        ff[i] = ni();
        w[i] = ni();
    }
    for (int i = 0; i < n; i++) {
        last[i] = -1;
        sum[i] = 0;
        was[i] = 0;
    }
    for (int i = 0; i < m; i++) {
        sum[ss[i]] += w[i];
        ne[i] = last[ss[i]];
        last[ss[i]] = i;
    }
    prob[0] = 1.;
    ex[0] = 0.;
    go(0);
    double ans = dp1[0];
    for (int i = 0; i < m; i++) {
        int v = ss[i];
        
    }
    printf("%.17f\n", ans);
}

int main() {
    while (true) {
        n = ni();
        m = ni();
        if (n == 0 && m == 0) break;
        solve();
    }
}