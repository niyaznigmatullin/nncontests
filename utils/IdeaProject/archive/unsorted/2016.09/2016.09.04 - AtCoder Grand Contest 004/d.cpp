#include <bits/stdc++.h>

using namespace std;

#define kill asdjskdlj
#define left asdjkal
int const N = 1234567;
vector<int> edges[N];
int k;
int kill[N], left[N], a[N];

static void dfs(int v) {
    kill[v] = 0;
    left[v] = 0;
    for (int to : edges[v]) {
        dfs(to);
        kill[v] += kill[to];
        if (left[to] == k - 1 && v != 0) {
            kill[v]++;
        } else {
            left[v] = std::max(left[v], left[to] + 1);
        }
    }
}

int main() {
    int n;
    scanf("%d%d", &n, &k);
    for (int i = 0; i < n; i++) {
        scanf("%d", a + i);
        --a[i];
    }
    int ans = 0;
    if (a[0] != 0) {
        a[0] = 0;
        ans++;
    }
    for (int i = 1; i < n; i++) {
        edges[a[i]].push_back(i);
    }
    dfs(0);
    printf("%d\n", kill[0] + ans);
}
