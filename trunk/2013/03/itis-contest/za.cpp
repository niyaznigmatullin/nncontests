#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>

using namespace std;

int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    double h, v, ang, g;
    cin >> h >> v >> ang >> g;
    double a = -g * .5;
    double b = -v * sin(ang);
    double c = h;
    double d = b * b - 4 * a * c;
    if (fabs(a) < 1e-10) {
        if (fabs(b) < 1e-10) {
            puts("-1");
        } else {
            cout << fixed << setprecision(10) << -c / b << "\n";
        }
    } else if (d < -1e-10) cout << "-1\n"; else if (d > 1e-10) {
        double x1 = (-b - sqrt(d)) / 2 / a;
        double x2 = (-b + sqrt(d)) / 2 / a;
        if (x1 > x2) {
            double t = x1;
            x1 = x2;
            x2 = t;
        }
        cout << fixed << setprecision(10) << x1 << " " << x2 << "\n";
    } else {
        cout << fixed << setprecision(10) << -b / 2 / a << "\n";
    }
}