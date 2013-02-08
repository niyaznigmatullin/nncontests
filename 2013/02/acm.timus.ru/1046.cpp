#include <cmath>
#include <cassert>
#include <iostream>
#include <cstdio>

using std::cin;

double x[55], y[55], a[55], t[4];
const double pi = acos(-1.);

void mul(double * a, double * b) {
    t[0] = a[0] * b[0] + a[1] * b[2];
    t[1] = a[0] * b[1] + a[1] * b[3];
    t[2] = a[2] * b[0] + a[3] * b[2];
    t[3] = a[2] * b[1] + a[3] * b[3];
    for (int i = 0; i < 4; i++) a[i] = t[i];
}

void rot(double * a, double ang) {
    a[0] = a[3] = cos(ang);
    a[1] = -sin(ang);
    a[2] = -a[1];
}

void sub(double * a) {
    a[0] = 1 - a[0];
    a[3] = 1 - a[3];
    a[1] = -a[1];
    a[2] = -a[2];
}

int main() {
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) 
        cin >> x[i] >> y[i];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        a[i] = pi * a[i] / 180;
    }
    double m[4];
    rot(m, 0.0);
    double z[4];
    double g[4];
    double xx = 0, yy = 0;
    double ang = 0;
    for (int i = n - 1; i >= 0; i--) {
        rot(m, ang);
        rot(z, a[i]);
        sub(z);
        for (int j = 0; j < 4; j++) g[j] = m[j];
        mul(g, z);
        xx += g[0] * x[i] + g[1] * y[i];
        yy += g[2] * x[i] + g[3] * y[i];
        ang += a[i];
    }
    rot(m, ang);
    xx = -xx;
    yy = -yy;
    sub(m);
    double zz = m[0] * m[3] - m[1] * m[2];
    double ax = m[1] * yy - xx * m[3];
    double ay = xx * m[0] - yy * m[2];
    ax /= zz;
    ay /= zz;
    for (int i = 0; i < n; i++) {
        printf("%.3lf %.3lf\n", ax, ay);
        ax -= x[i];
        ay -= y[i];
        double nx = ax * cos(a[i]) - ay * sin(a[i]);
        double ny = ax * sin(a[i]) + ay * cos(a[i]);
        ax = nx + x[i];
        ay = ny + y[i];
    }
}