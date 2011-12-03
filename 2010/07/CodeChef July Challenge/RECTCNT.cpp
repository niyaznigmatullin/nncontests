#include <algorithm>
#include <iostream>
#include <cstdio>

using namespace std;
typedef long long ll;
class Point {
    public:
    int x;
    int y;
    Point(int x = 0, int y = 0) : x(x), y(y) {
        
    }
};

bool operator<(const Point & a, const Point & b) {
    return a.x < b.x || a.x == b.x && a.y < b.y;
}

Point p[100000];
Point p2[100000];

ll solve(int n) {
    sort(p, p + n);
    int cur = 0;
    for (int i = 0; i < n; ) {
        int j = i;
        while (j < n && p[i].x == p[j].x) {
            j++;
        }
        if (j - i == 2) {
            p2[cur].x = p[i].y;
            p2[cur].y = p[i + 1].y;
            cur++;
        }
        i = j;
    }
    sort(p2, p2 + cur);
    ll ans = 0;
    for (int i = 0; i < cur; ) {
        int j = i;
        while (j < cur && p2[i].x == p2[j].x && p2[i].y == p2[j].y) {
            ans += j - i;
            j++;
        }
        i = j;
    }
    return ans;
}

int main() {
    while (true) {
        int n;
        scanf("%d", &n);
        if (n == 0) {
            break;
        }
        for (int i = 0; i < n; i++) {
            scanf("%d%d", &p[i].x, &p[i].y);
        }
        printf("%lld\n", solve(n));
    }
}