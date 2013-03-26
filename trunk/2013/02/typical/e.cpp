#include <cstdio>
#include <ctime>
#include <iostream>
#include <cmath>
#include <cstdlib>

using namespace std;

const double VX = 123456789 * cos(123456.789);
const double VY = 123456789 * sin(123456.789);
double x[11111], y[11111];
const double pi = acos(-1.);
const int ROT = 15;
double vx[ROT], vy[ROT];
double minx, miny, maxx, maxy;
int n;

inline int cmp(double a, double b) {
    double dd = a - b;
    if (dd < 0) dd = -dd;
    double c = a;
    double d = b;
    if (c < 0) c = -c;
    if (d < 0) d = -d;
    if (c < d) c = d;
    if (dd < c * 1e-9) return 0;
    return a < b ? -1 : 1;
}   

inline int inter(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
    double v1 = (x3 - x1) * (y4 - y1) - (x4 - x1) * (y3 - y1);
    double v2 = (x3 - x2) * (y4 - y2) - (x4 - x2) * (y3 - y2);
    double u1 = (x1 - x3) * (y2 - y3) - (x2 - x3) * (y1 - y3);
    double u2 = (x1 - x4) * (y2 - y4) - (x2 - x4) * (y1 - y4);
    return cmp(v1, 0) != cmp(v2, 0) && cmp(u1, 0) != cmp(u2, 0);
}

inline int onc(double x, double y, double x1, double y1, double x2, double y2) {
    double vmul = (x1 - x) * (y2 - y) - (x2 - x) * (y1 - y);
    if (cmp(vmul, 0) != 0) return 0;
    double smul = (x1 - x) * (x2 - x) + (y1 - y) * (y2 - y);
    return cmp(smul, 0) <= 0;
}

inline int inside(double x0, double y0) {
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        if (j == n) j = 0;
        if (onc(x0, y0, x[i], y[i], x[j], y[j])) return 1;
    }
    double x1 = x0 + VX;
    double y1 = y0 + VY;
    int cut = 0;
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        if (j == n) j = 0;
        if (inter(x0, y0, x1, y1, x[i], y[i], x[j], y[j])) ++cut;
    }
    return cut & 1;
}

double randD() {
    return 1. * rand() / (1 << 15);
}

inline double dist(double x, double y, double x1, double y1) {
    x -= x1;
    y -= y1;
    return x * x + y * y;
}

inline double dist(double x, double y, double x1, double y1, double x2, double y2) {
    double a = dist(x, y, x1, y1);
    double b = dist(x, y, x2, y2);
    double c = dist(x1, y1, x2, y2);
    if (a + c < b) return a;
    if (b + c < a) return b;
    double la = y2 - y1;
    double lb = x1 - x2;
    double lc = -la * x1 - lb * y1;
    double z = la * la + lb * lb;
    double d = la * x + lb * y + lc;
    d *= d;
    return d / z;
}

inline double f(double x0, double y0) {
    if (x0 > maxx || y0 > maxy || x0 < minx || y0 < miny) return 0;
    if (!inside(x0, y0)) {
        return 0;
    }
    double ans = 1e20;
    for (int i = 0; i < n; i++) {
        int j = i + 1;
        if (j == n) j = 0;
        double got = dist(x0, y0, x[i], y[i], x[j], y[j]);
        if (ans > got) ans = got;
    }
    return ans;
}

double solve(double x0, double y0) {
    if (!inside(x0, y0)) {
        return 0;
    }
    double step = maxx - minx;
    if (step < maxy - miny) step = maxy - miny;
    double best = f(x0, y0);
    for (int it = 0; it < 70; ) {
        int found = 0;
        int bestI = -1;
        double bestH = best;
        for (int i = 0; i < ROT; i++) {
            double got = f(x0 + vx[i] * step, y0 + vy[i] * step);
            if (got > bestH) {
                bestH = got;
                bestI = i;
                found = 1;
            }
        }
        if (!found) {
            step *= .4;
            ++it;
        } else {
            best = bestH;
            x0 += vx[bestI] * step;
            y0 += vy[bestI] * step;
        }
    }
    cerr << best << " " << step << "\n";
    return best;
}

int main() {
    for (int i = 0; i < ROT; i++) {
        vx[i] = cos(2. * pi * i / ROT);
        vy[i] = sin(2. * pi * i / ROT);
    }
    srand(time(NULL));
    scanf("%d", &n);
    minx = 1e20;
    maxx = -1e20;
    miny = 1e20;
    maxy = -1e20;
    for (int i = 0; i < n; i++) {
        int xx, yy;
        scanf("%d %d", &xx, &yy);
        x[i] = xx;
        y[i] = yy;
        if (minx > x[i]) minx = x[i];
        if (maxx < x[i]) maxx = x[i];
        if (miny > y[i]) miny = y[i];
        if (maxy < y[i]) maxy = y[i];
    }
    double ans = 0;
    for (int it = 0; it < 5; it++) {
        int i = rand() % n;
        double got = solve(x[i], y[i]);
        if (got > ans) ans = got;
    }
    for (int it = 0; it < 5; it++) {
        double x0 = randD() * (maxx - minx) + minx;
        double y0 = randD() * (maxy - miny) + miny;
        double got = solve(x0, y0);
        if (got > ans) ans = got;
    }                            
    printf("%.17f\n", sqrt(ans));
}