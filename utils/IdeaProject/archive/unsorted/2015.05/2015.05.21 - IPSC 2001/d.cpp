#include <bits/stdc++.h>

using namespace std;

struct element {
    int val;
    vector<int> ans;
    
    void clear(int nval) {
        ans.clear();
        val = nval;
    }
    
    void add(int v) {
        ans.push_back(v);
    }
    
    void addAll(element const & a, element const & b) {
        ans.insert(ans.end(), a.ans.begin(), a.ans.end());
        ans.insert(ans.end(), b.ans.begin(), b.ans.end());
    }
};

int const INF = 1 << 30;

int const N = 333;

int pv[N];
int sz[N];
vector<int> edges[N];
int cost[N];
element cdp[2][N], ndp[2][N];
element dp[N][2][N];

void dfs(int v) {
    sz[v] = 1;
    for (int i : edges[v]) {
        dfs(i);
        sz[v] += sz[i];
    }
    int cn = 1;
    cdp[0][0].clear(0);
    cdp[0][1].clear(-INF);
    cdp[1][0].clear(-INF);
    cdp[1][1].clear(0);
    cdp[1][1].add(v);
    for (int i : edges[v]) {
        int add = sz[i];
        for (int e = 0; e < 2; e++)
            for (int f = 0; f <= add + cn; f++) {
                ndp[e][f].clear(-INF);
            }
        for (int k1 = 0; k1 <= cn; k1++) {
            for (int cv = 0; cv < 2; cv++) {
                int val1 = cdp[cv][k1].val;
                if (val1 == -INF) continue;
                for (int k2 = 0; k2 <= add; k2++) {
                    for (int ci = 0; ci < 2; ci++) {
                        int val2 = dp[i][ci][k2].val;
                        if (val2 == -INF) continue;
                        int nval = val1 + val2 + (ci == cv ? 0 : cost[i]);
                        auto & f = ndp[cv][k1 + k2];
                        if (f.val < nval) {
                            f.clear(nval);
                            f.addAll(cdp[cv][k1], dp[i][ci][k2]);
                        }
                    }
                }
            }
        }
        cn += add;
        for (int e = 0; e < 2; e++)
            for (int f = 0; f <= cn; f++)
                cdp[e][f] = ndp[e][f];
    }
    for (int e = 0; e < 2; e++)
        for (int f = 0; f <= cn; f++)
            dp[v][e][f] = cdp[e][f];
}

int main() {
    int n;
    scanf("%d", &n);
    int root = -1;
    for (int i = 0; i < n; i++) {
        scanf("%d%d", pv + i, cost + i);
        --pv[i];
        if (pv[i] >= 0) edges[pv[i]].push_back(i); else root = i;
    }
    dfs(root);
    element z = dp[root][0][n / 2];
    if (z.val < dp[root][1][n / 2].val) {
        z = dp[root][1][n / 2];
    }
/*    for (int i = 0; i < n; i++) 
        for (int j = 0; j < 2; j++)
            for (int k = 0; k <= n; k++) printf("%d %d %d %d\n", i, j, k, dp[i][j][k].val);*/
    bool first = true;
    fprintf(stderr, "%d\n", z.val);
    for (int i : z.ans) {
        if (first) first = false; else putchar(' ');
        printf("%d", i + 1);
    }
    puts("");
}
