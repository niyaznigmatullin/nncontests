#include <cstdio>

    const int INF = ~(1 << 31);
    const int N = 12345;
    int ss[N], ff[N], rev[N], flow[N], cap[N], cost[N], he[N], ne[N], was[N], last[N], m, n;
    long long phi[N], d[N];


    void minCostFlow(int src, int tg, int & fl, long long & co) {
        int changed = 1;
        while (changed) {     // Ford-Bellman
            changed = 0;
            for (int i = 0; i < m; i++) {
                if (flow[i] == cap[i]) continue;
                if (phi[ff[i]] > phi[ss[i]] + cost[i]) {
                    phi[ff[i]] = phi[ss[i]] + cost[i];
                    changed = 1;
                }
            }
        }
        fl = 0;
        co = 0;
        while (true) {
            for (int i = 0; i < n; i++) d[i] = INF, was[i] = 0;
            d[src] = 0;
            while (true) { // Dijkstra
                int mn = -1;
                for (int i = 0; i < n; i++) {
                    if (was[i] || d[i] == INF) continue;
                    if (mn < 0 || d[mn] > d[i]) mn = i;
                }
                if (mn < 0) break;
                was[mn] = true;
                for (int e = he[mn]; e >= 0; e = ne[e]) {
                    if (cap[e] == flow[e]) continue;
                    if (d[ff[e]] > d[ss[e]] + phi[ss[e]] - phi[ff[e]] + cost[e]) {
                        d[ff[e]] = d[ss[e]] + phi[ss[e]] - phi[ff[e]] + cost[e];
                        last[ff[e]] = e;                    
                    }
                }
            }
            if (!was[tg]) {
                break;
            }
            if ((d[tg] + phi[tg] - phi[src]) >= 0) break;
            int add = INF;
            for (int v = tg; v != src; v = ss[last[v]]) {
                if (add > cap[last[v]] - flow[last[v]]) add = cap[last[v]] - flow[last[v]];
            }
            fl += add;
            co += add * (d[tg] + phi[tg] - phi[src]);
            for (int v = tg; v != src; v = ss[last[v]]) {
                flow[last[v]] += add;
                flow[rev[last[v]]] -= add;
            }
            for (int i = 0; i < n; i++) phi[i] = d[i];
        }
    }

int main() {
    scanf("%d %d", &n, &m);
    for (int i = 0; i < m; i++) {
        flow[i] = 0;
        scanf("%d %d %d %d", ss + i, ff + i, cap + i, cost + i);
        --ss[i];
        --ff[i];
        ss[i + m] = ff[i];
        ff[i + m] = ss[i];
        rev[i] = i + m;
        rev[i + m] = i;
        cap[i + m] = flow[i + m] = 0;
        cost[i + m] = -cost[i];        
    }
    m += m;
    int src, tg;
    scanf("%d %d", &src, &tg);
    --src;
    --tg;
    for (int i = 0; i < n; i++) he[i] = -1, phi[i] = 0;
    for (int i = 0; i < m; i++) {
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    int fl = 0;
    long long co = 0;
    minCostFlow(src, tg, fl, co);
    printf("%d %I64d\n", fl, co);
}