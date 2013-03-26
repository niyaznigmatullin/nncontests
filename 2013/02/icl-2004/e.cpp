#include <cstdio>
#include <algorithm>

using namespace std;

int ni() {
    int c = getchar();
    while (c < '0' || c > '9') {
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return ret;
}

int m;
int a[10], b[10];

int check(int y) {
    for (int i = 0; i < m; i++) {
        int ok = y % (a[i] * b[i]) == 0 || (y % a[i] == 0 && y % b[i] != 0);
        if (!ok) return 0;
    }
    return 1;
}

int gcd(int a, int b) {
    return !b ? a : gcd(b, a % b);
}

int lcm(int a, int b) {
    return a / gcd(a, b) * b;
}

int main() {
    int y = ni();
    m = ni();
    int step = 1;
    for (int i = 0; i < m; i++) {
        a[i] = ni();
        b[i] = ni(); 
        step = lcm(a[i], step);
    }
    y = (y + step - 1) / step * step;
    while (!check(y)) y += step;
    printf("%d\n", y);
}