#include <iostream>
#include <cassert>
#include <cstdio>
#include <iomanip>
#include <cmath>



#define y1 y1nouse

using namespace std;

double x1, y1, z1, r1, vx1, vy1, vz1, v1, x2, y2, z2, r2, vx2, vy2, vz2, v2;

double f(double t) {
    double x3 = x1 + vx1 * t;
    double y3 = y1 + vy1 * t;
    double z3 = z1 + vz1 * t;
    double x4 = x2;
    double y4 = y2;
    double z4 = z2;
    x3 -= x4;
    y3 -= y4;
    z3 -= z4;
    double d = x3 * x3 + y3 * y3 + z3 * z3;
    return sqrt(d);
}

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    cin >> x1 >> y1 >> z1 >> r1 >> vx1 >> vy1 >> vz1 >> v1;
    cin >> x2 >> y2 >> z2 >> r2 >> vx2 >> vy2 >> vz2 >> v2;
//    cout << x1 << " " << y1 << " " << z1 <<" " <<  r1 << " " << vx1 << " " << vy1 << " " << vz1 << " " << v1 << "\n";
//    cout << x2 << " " << y2 << " " << z2 <<" " <<  r2 << " " << vx2 << " " << vy2 << " " << vz2 << " " << v2 << "\n";
    double d1 = sqrt(vx1 * vx1 + vy1 * vy1 + vz1 * vz1);
    double d2 = sqrt(vx2 * vx2 + vy2 * vy2 + vz2 * vz2);
    vx1 /= d1;
    vy1 /= d1;
    vz1 /= d1;
    vx2 /= d2;
    vy2 /= d2;
    vz2 /= d2;
    vx1 *= v1;
    vy1 *= v1;
    vz1 *= v1;
    vx1 -= vx2 * v2;
    vy1 -= vy2 * v2;
    vz1 -= vz2 * v2;
    d1 = vx1 * vx1 + vy1 * vy1 + vz1 * vz1;
    double sm = ((x2 - x1) * vx1 + (y2 - y1) * vy1 + (z2 - z1) * vz1) / d1;    
    if (sm < 0) sm = 0;
    if (fabs(vx1) < 1e-9 && fabs(vy1) < 1e-9 && fabs(vz1) < 1e-9) {
        sm = 0;
    }
    double d = f(sm);
    if (d < r1 + r2 + 1e-7) {
        double r = sm;
        double l = 0;
        for (int i = 0; i < 1000; i++) {
            double m = (l + r) * .5;
            if (f(m) < r1 + r2) r = m; else l = m;
        }
        cout << fixed << setprecision(10) << "CRASH\n" << l << "\n";
    } else {
        cout << fixed << setprecision(10) << d - r1 - r2 << "\n" << sm << "\n";
    }
}
