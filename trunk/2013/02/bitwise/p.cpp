#include <cstdio>
#include <cmath>
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


int x[4444], y[4444];
int ps[4444], ps2[4444];
double pin[4444];
double pin2[4444];
int pid[4444];

bool cmpy0(int a, int b) {
    return y[a] < y[b];
}

int cmp(double a, double b) {
    double d = a - b;
    if (d < 0) d = -d;
    if (d < 1e-9) return 0;
    return a < b ? -1 : 1;
}

double ip(int i, int j) {
    if (x[i] == x[j]) return 1e30;
    return (x[i] + x[j]) * .5 - 8. * (y[i] - y[j]) / (x[i] - x[j]);
}

double asinh(double x) {
    return log(x + sqrt(1 + x * x));
}

double f(double x, double a) {
    return sqrt((a - x) * (a - x) + 64) * (x - a) / 16 - 4 * asinh((a - x) / 8);
}

void solve() {
    int n = ni();
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        x[i] = ni();
        y[i] = ni();
        pid[i] = i;
    }
    std::sort(pid, pid + n, cmpy0);
    for (int it = 0; it < n; it++) {
        int i = pid[it];
        int left = -1;
        int right = cnt;
        for (int jd = 0; jd < cnt; jd++) {
            int j = ps[jd];
            double x0 = ip(i, j);
            if (x0 > 1e20) {
                continue;
            }
            if (jd > 0 && cmp(x0, pin[jd - 1]) < 0) continue;
            if (jd + 1 < cnt && cmp(x0, pin[jd]) > 0) continue;
            if (x0 < x[i]) left = jd; else right = jd;
        }
        for (int j = 0; j <= left; j++) {
            pin2[j] = pin[j];
            ps2[j] = ps[j];
        }
        ps2[left + 1] = i;
        if (left >= 0) pin2[left] = ip(ps2[left], ps2[left + 1]);
        for (int j = right, k = left + 2; j < cnt; j++, k++) {
            ps2[k] = ps[j];
            if (j == right) {
                pin2[left + 1] = ip(ps2[left + 1], ps2[left + 2]);
            } else {
                pin2[k - 1] = pin[j - 1];
            }
        }
        cnt = (cnt + 1) - (right - left - 1);
        for (int j = 0; j < cnt; j++) {
            pin[j] = pin2[j];
            ps[j] = ps2[j];
        }
    }
    double ans = 0;
    for (int it = 0; it < cnt; it++) {
        int i = ps[it];
        double bb = -2 * x[i];
        double cc = x[i] * x[i] - 16 * y[i];
        double dd = bb * bb - 4 * cc;
        if (cmp(dd, 0) < 0) continue;
        if (dd < 0) dd = 0;
        dd = sqrt(dd);
        double t1 = (-bb - dd) * .5;
        double t2 = (-bb + dd) * .5;
        double left = it == 0 ? -1e20 : pin[it - 1];
        double right = it + 1 == cnt ? 1e20 : pin[it];
        if (t1 > left) left = t1;
        if (t2 < right) right = t2;
        if (left > right) continue;
        ans += f(right, x[i]) - f(left, x[i]);
    }
    printf("%.2lf\n", ans);
}

int main() {
    int t = ni();
    for (int i = 0; i < t; i++) {
        solve();
    }
}