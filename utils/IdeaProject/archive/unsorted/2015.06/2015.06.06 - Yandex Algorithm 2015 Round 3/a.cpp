#include <bits/stdc++.h>

using namespace std;

double const PI = acos(-1.);

void solve() {
    int a, b;
    int a1, a2, b1, b2;
    scanf("%d%d%d%d%d%d", &a, &b, &a1, &a2, &b1, &b2);
    if (a > b) {
        swap(a, b);
        swap(a1, b1);
        swap(a2, b2);
    }
    double prob = 2. * atan(a * 1. / b) / PI;
    printf("%.17f\n", prob * (a1 + a2) * .5 + (1 - prob) * (b1 + b2) * .5);
}

int main() {
    int t;
//    scanf("%d", &t);
t = 1;
    while (t--) {
        solve();
    }
}
