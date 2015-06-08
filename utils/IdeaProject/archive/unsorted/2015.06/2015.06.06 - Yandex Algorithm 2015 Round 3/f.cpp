#include <bits/stdc++.h>

using namespace std;

int const N = 1234567;

long long const INF = 1LL << 60;
struct edge {
    int from;
    int to;
    int w;
    int c;
};

int n;

vector<edge> edges[N];

void getd(long long * d, int st) {
    set<pair<long long, int> > q;
    for (int i = 0; i < n; i++) d[i] = INF;
    d[st] = 0;
    q.insert(make_pair(d[st], st));
    while (!q.empty()) {
        auto f = *q.begin();
        q.erase(q.begin());
        int v = f.second;
        for (auto & e : edges[v]) {
            if (d[e.to] > d[v] + e.w) {
                q.erase(make_pair(d[e.to], e.to));
                d[e.to] = d[v] + e.w;
                q.insert(make_pair(d[e.to], e.to));
            }
        }
    }
}

long long d1[N], d2[N];

void solve() {
    int m;
    scanf("%d%d", &n, &m);
    int st, fin;
    scanf("%d%d", &st, &fin);
    --st;
    --fin;
    int needA, needB;
    scanf("%d%d", &needA, &needB);
    long long need = needB - needA;
    long long ans = INF;
    for (int i = 0; i < m; i++) {
        int v, u, w, c;
        scanf("%d%d%d%d", &v, &u, &w, &c);
        --v;
        --u;
        int dec = w >= 0 ? (w + 1) : 0;
        ans = min(ans, (long long) dec * c);
        edges[v].push_back({v, u, w, c});
        edges[u].push_back({u, v, w, c});
    }
    if (st == fin || ans == 0) {
        puts("0");
        return;
    }   
    getd(d1, st);
    getd(d2, fin);
    for (int i = 0; i < n; i++) {
        for (auto & e : edges[i]) {
            long long path = d1[e.from] + d2[e.to] + e.w;
            if (path <= need) {
                ans = 0;
            } else {
                path = path - need;
                if (1. * path * e.c > 2e18) continue;
                ans = min(ans, path * e.c);
            }
        }
    }
    printf("%lld\n", ans);
}

int main() {
    int t;
//    scanf("%d", &t);
    t = 1;
    while (t--) {
        solve();
    }
}
