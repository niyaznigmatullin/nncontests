#include <iostream>
#include <cstdio>
#include <vector>
#include <cassert>

using namespace std;

const int MAXN = 710;
const int MAXM = 425000;
const int INF = 2000 * 1000 * 1000;

struct Edge {
        int a, b, f, cost, pos, c;
};

Edge e[MAXM];

int was[MAXN], p[MAXN], fre, q[12345678], used[MAXN], n, m, k;

long long d[MAXN], ans;

vector <int> g[MAXN], road;

void make_edge(int a, int b, int cost, int pos) {
        g[a].push_back(fre);

        e[fre].a = a;
        e[fre].b = b;
        e[fre].cost = cost;
        e[fre].c = 1;
        e[fre].f = 0;
        e[fre++].pos = pos;

        g[b].push_back(fre);

        e[fre].a = b;
        e[fre].b = a;
        e[fre].cost = - cost;
        e[fre].c = 0;
        e[fre].f = 0;
        e[fre++].pos = pos;
}

bool ford_bellman() {
        for (int i = 1; i <= n; i++)
                d[i] = INF, was[i] = false, p[i] = - 1;

        int l = 0, r = 0, v, id, to = 0, cur;

        q[++r] = 1;

        was[1] = true, d[1] = 0;

        while (l < r) {
                v = q[++l];
                was[v] = false;
//                assert(d[v] >= 0);

                for (int i = 0; i < (int)g[v].size(); i++) {
                        id = g[v][i];
                        to = e[id].b;
                        cur = e[id].c - e[id].f;

                        if (cur > 0 && d[v] != INF && d[to] > d[v] + e[id].cost) {
                                if (!was[to])
                                        was[to] = true, q[++r] = to;
                                d[to] = d[v] + e[id].cost;
                                p[to] = id;
                        }
                }
        }



        if (d[n] == INF)
                return false;

        return true;
}

void dfs(int v) {
        used[v] = true;

        if (v == n)
                return;

        int id, to;

        for (int i = 0; i < (int)g[v].size(); i++) {
                id = g[v][i];
                to = e[id].b;

                if (!used[to] && e[id].f > 0) {
                        road.push_back(e[id].pos);
                        e[id].f = 0;
                        dfs(to);
                        return;
                }
        }
        return;
}

int a, b, cost, nn;

int main() {
        freopen("assignment.in", "r", stdin);
        freopen("assignment.out", "w", stdout);
        scanf("%d", &nn);
        n = nn + nn + 2;
        for (int i = 0; i < nn; i++) {
          make_edge(1, 2 + i, 0, 0);
          make_edge(2 + i + nn, nn + nn + 2, 0, 0);
          for (int j = 0; j < nn; j++) {
            int x;
            scanf("%d", &x);
            make_edge(2 + i, 2 + j + nn, x, 0);
          }
        }
        int to = 0, cnt = 0;

        while (cnt < nn && ford_bellman()) {

                int v = n;

                while (p[v] != - 1) {
                //        cerr << v << ' ' << p[v].a << endl;
                        to = e[p[v]].a;
                        e[p[v]].f++;
                        e[p[v] ^ 1].f--;
                        v = to;
                }

                ++cnt;
                ans += d[n];
        }

        printf("%d\n", (int) ans);
        for (int i = 0; i < fre; i++) {
          if (e[i].a > 1 && e[i].a < 2 + nn && e[i].b >= 2 + nn && e[i].c < 2 + nn + nn && e[i].f > 0) {
            printf("%d %d\n", e[i].a - 1, e[i].b - 1 - nn);
          }
        }
}
