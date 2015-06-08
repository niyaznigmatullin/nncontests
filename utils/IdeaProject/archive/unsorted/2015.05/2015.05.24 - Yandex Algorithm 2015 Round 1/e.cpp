#include <bits/stdc++.h>

using namespace std;

struct vertex {
    long long v;
    int d;
};

int const N = 1234567;

long long sz[N];
int type[N];
int cn;

int get(vector<vertex> & vs, long long x, vertex & last) {
/*    for (int i = 0; i <= cn; i++) {
        printf("%d %lld\n", type[i], sz[i]);
    }
    puts("----------------");*/
    long long cur = 0;
    int d = 0;
    for (int i = cn - 1; ; i--) {
        vs.push_back({cur, d});
        if (x <= 0) {
            return d;
        }
        assert(i >= 0);
        if (type[i] < 0) {
            --x;
            long long which = x / sz[i];
            cur += which * sz[i] + 1;
            x %= sz[i];
            ++d;
            last = {cur, d};
        } else {
            long long down = min<long long>(x, type[i]);
            last = {cur + type[i], d + type[i]};
            d += (int) down;
            x -= down;
            cur += down;
        }
    }
}

int main() {
    int m;
    scanf("%d", &m);
    cn = 0;
    sz[0] = 1;
    for (int it = 0; it < m; it++) {
        int c = getchar();
        while (c <= 32) c = getchar();
        if (c == '+') {
            int k;
            scanf("%d", &k);
            if (k == 1) {
                if (cn > 0 && type[cn - 1] > 0) {
                    ++type[cn - 1];                    
                    ++sz[cn];
                } else {
                    type[cn++] = 1;
                    sz[cn] = sz[cn - 1] + 1;
                }
            } else {
                type[cn++] = ~k;
                sz[cn] = sz[cn - 1] * k + 1;
            }
        } else {
            long long x, y;
            scanf("%lld%lld", &x, &y);
            --x;
            --y;
            vector<vertex> v1;
            vertex last1;
            int d1 = get(v1, x, last1);
/*            printf("x = %lld\n", x);
            for (vertex & e : v1) {
                printf("%lld %d\n", e.v, e.d);
            }
            puts("---------------");*/
            assert(v1.back().v == x);
            vector<vertex> v2;
            vertex last2;
            int d2 = get(v2, y, last2);
/*            printf("y = %lld\n", y);
            for (vertex & e : v2) {
                printf("%lld %d\n", e.v, e.d);
            }
            puts("---------------");*/
            assert(v2.back().v == y);
            bool found = false;
            for (auto & e : v1) 
                if (e.v == last2.v) {
                    found = true;
                    printf("%d\n", d1 - d2);
                    break;
                }
            if (found) continue;
            for (auto & e : v2) {
                if (e.v == last1.v) {
                    found = true;
                    printf("%d\n", d2 - d1);
                    break;
                }
            }
            if (found) continue;
            if (last1.v == last2.v) {
                d1 -= d2;
                if (d1 < 0) d1 = -d1;
                printf("%d\n", d1);
                continue;
            }
            int lca = 0;
            int cc = 0;
            for (auto & e : v1) {
                while (cc < (int) v2.size() && e.d > v2[cc].d) ++cc;
                if (cc < (int) v2.size() && e.v == v2[cc].v) lca = e.d;
            }
            printf("%d\n", d1 + d2 - lca - lca);
        }
    }
}