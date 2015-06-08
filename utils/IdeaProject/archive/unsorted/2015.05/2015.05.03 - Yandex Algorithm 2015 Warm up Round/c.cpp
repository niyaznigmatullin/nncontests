#include <bits/stdc++.h>

int main() {
    int x1, y1, x2, y2;
    scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
    y1 = -y1;
    long long dx = x1 - x2;
    long long dy = y1 - y2;
    printf("%lld.00000000000000000000\n", dx * dx + dy * dy);
}