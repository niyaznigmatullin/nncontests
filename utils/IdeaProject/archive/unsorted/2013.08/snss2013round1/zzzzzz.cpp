#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
//#include <

#define fr(i, n) for (int i = 0; i < int(n); i++)

typedef int ll;

const ll K = 30;

using namespace std;

struct edge {
    ll to;
    ll c;
    int b;
    edge() {}
    edge(int t, int cc, int bb) {
        to = t;
        c = cc;
        b = bb;
    }
};

ll n, m, v1, v2, len, c, mne;
ll ptr[600], ans = 0;
ll d[600];
ll ln[600];
vector <edge> es[600];

void bfs() {
    edge ed;
    ll q[600];
    bool use[600];
    fr(i, 600)
        use[i] = false;
    int u2, u1;
    u1 = u2 = 0;
    q[0] = 0;
    d[0] = 0;
    use[0] = true;
    while (u1 >= u2) {
        fr(i, ln[q[u2]]) {
            ed = es[q[u2]][i];
            if (!use[ed.to] && ed.c != 0) {
                q[++u1] = ed.to;
                d[ed.to] = d[q[u2]] + 1;
                use[ed.to] = true;
            }
        }
        u2++;
    }
    return;
}

ll dfs (ll v, ll have) {
    ll can, u, tm, total = 0;
    edge ed, de;
    if (v == n - 1)
        return have;
    while (ptr[v] + 1) {
        ed = es[v][ptr[v]];
        u = ed.to;
        de = es[u][ed.b];
        if (d[u] != d[v] + 1 || ed.c < mne) {
            ptr[v]++;
            if (ptr[v] == ln[v])
                ptr[v] = -1;
            continue;
        }
        tm = have - total;
        if (ed.c < have - total)
            tm = ed.c;
        can = dfs(u, tm);
        es[v][ptr[v]].c -= can;
        es[u][ed.b].c += can;
        total += can;
        if (total == have)
            return total;
        ptr[v]++;
        if (ptr[v] == ln[v])
            ptr[v] = -1;
    }
    return total;
}

int main()
{
    freopen("flow2.in", "r", stdin);
    freopen("flow2.out", "w", stdout);

    cin >> n >> m;
    if (m == 0) {
        cout << 0;
        return 0;
    }
    //m = 0;
    fr(i, m) {
        cin >> v1 >> v2 >> c;
        v1--;
        v2--;
        ln[v1]++;
        ln[v2]++;
        es[v1].push_back(edge(v2, c, es[v2].size()));
        es[v2].push_back(edge(v1, 0, es[v1].size() - 1));
    }

    mne = (1ll << K);

    ll hw;

    while (mne != 0) {

        while(true) {
            if (mne == 1)
                mne = mne;
            bfs();
            fr(i, 600) ptr[i] = 0;
            hw = dfs(0ll, 1000000000);
            if (hw <= 0) break;
            ans += hw;
        }

        mne = (mne >> 1);
    }

    cout << ans;

    return 0;
}
