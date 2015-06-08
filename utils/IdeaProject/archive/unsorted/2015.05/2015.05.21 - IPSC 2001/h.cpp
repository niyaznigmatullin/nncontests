#include <bits/stdc++.h>
using namespace std;
int const N = 123456;
int p1[N], p2[N];
vector<int> edges[N];
int VER;
int was[N];

int dfs(int v) {
    if (was[v] == VER) return false;
    was[v] = VER;
    for (int i : edges[v]) {
        if (p2[i] < 0 || dfs(p2[i])) {
            p1[v] = i;
            p2[i] = v;
            return true;
        }
    }
    return false;
}

int main() {
    int n;
    scanf("%d", &n);
    for (int mask = 0; mask < 1 << n; mask++) {
        if (__builtin_popcount(mask) != (n + 1) / 2) continue;
        for (int mask2 = 0; mask2 < 1 << n; mask2++) {
            if (__builtin_popcount(mask2) != (n - 1) / 2) continue;
            if ((mask2 & mask) != mask2) continue;
            int xx = mask ^ mask2;
            if ((xx & (xx - 1)) != 0) continue;
            edges[mask].push_back(mask2);
        }
    }
    fill(p1, p1 + (1 << n), -1);
    fill(p2, p2 + (1 << n), -1);
    for (int i = 0; i < 1 << n; i++) {
        if (__builtin_popcount(i) != (n + 1) / 2) continue;
        ++VER;
        assert(dfs(i));
    }
    for (int i = 0; i < 1 << n; i++) {
        if (__builtin_popcount(i) != (n + 1) / 2) continue;
        for (int j = 0; j < n; j++) if (((i >> j) & 1) == 1) printf("%d ", j + 1);
        int x = i ^ p1[i];
        int y = 0;
        while (x > 0) {
            x >>= 1;
            y++;
        }
        printf("-> %d\n", y);
    }
}
