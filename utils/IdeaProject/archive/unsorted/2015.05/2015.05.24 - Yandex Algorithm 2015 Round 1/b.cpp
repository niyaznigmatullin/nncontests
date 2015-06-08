#include <bits/stdc++.h>

using namespace std;

struct point {
    int x;
    int y;
    int a;
};

point operator - (point const & a, point const & b) {
    return {a.x - b.x, a.y - b.y, a.a};
}

int get(point p) {
    if (p.y < 0) {
        return p.x < 0 ? 6 : p.x > 0 ? 8 : 7;
    } else if (p.y > 0) {
        return p.x < 0 ? 4 : p.x > 0 ? 2 : 3;
    } else {
        return p.x < 0 ? 5 : p.x > 0 ? 1 : 0;
    }
}

long long vmul(point const & a, point const & b) {
    return (long long) a.x * b.y - (long long) b.x * a.y;
}

int const N = 2234;

point p[N], ev[N];

int main() {
    int n;
    scanf("%d", &n);
    int sum = 0;
    for (int i = 0; i < n; i++) {
        int x, y, a;
        scanf("%d%d%d", &x, &y, &a);
        sum += a;
        p[i] = {x, y, a};
    }
    int ans = 1 << 30;
    for (int it = 0; it < n; it++) {
        point const c = p[it];
        int cn = 0;
        int got = 0;
        for (int i = 0; i < n; i++) {
            if (i == it) continue;
            if (p[i].y < c.y || (p[i].y == c.y && p[i].x < c.x)) got += p[i].a;
            point q = p[i] - c;
            q.a = p[i].a;
            ev[cn++] = q;
            ev[cn++] = {-q.x, -q.y, -q.a};
        }
        auto const cmp = [](point const & a, point const & b) {
            int d = get(a) - get(b);
            if (d != 0) return d < 0;
            return vmul(a, b) > 0;
        };
        sort(ev, ev + cn, cmp);
        for (int i = 0; i < cn; i++) {
            if (i > 0 && cmp(ev[i - 1], ev[i])) {
                int dif = sum - 2 * got;
                if (dif < 0) dif = -dif;
                ans = min(ans, dif);
            }
            got += ev[i].a;
        }
        int other = sum - got;
        int dif = other - got;
        if (dif < 0) dif = -dif;
        ans = min(ans, dif);
    }
    printf("%d\n", ans);
}
