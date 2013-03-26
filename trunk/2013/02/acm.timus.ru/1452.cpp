#include <cstdio>
#include <algorithm>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    int sg = c == '-';
    if (sg) c = getchar();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

int id[2222], a[2222];
short dp[2000][2000], p[2000][2000], ans[2222];

bool cmp(int x, int y) {
    return a[x] < a[y];
}

int main() {
    int n = ni();
    for (int i = 0; i < n; i++) {
        a[i] = ni();
        id[i] = i;
    }
    std::sort(id, id + n, cmp);
    {
        int j = 1;
        for (int i = 1; i < n; i++) {
            if (a[id[i]] != a[id[j - 1]]) {
                id[j++] = id[i];
            }
        }
        n = j;
    }
    if (n == 1) {
        printf("1\n1\n");
        return 0;
    }
    for (int i = 1; i < n; i++) {
        int k = n - 1;
        int v1 = a[id[i]];
        for (int j = 0; j < i; j++) {
            int v2 = a[id[j]];
            int val = dp[j][i];
            int z = v1 + v1 - v2;
            while (k > i && z < a[id[k]]) {
                --k;
            }
            if (k == i) continue;
            if (z == a[id[k]]) {
                if (dp[i][k] < val + 1) {
                    dp[i][k] = val + 1;
                    p[i][k] = j;
                }
            }
        }
    }
    int best = -1;
    int ansI = -1;
    int ansJ = -1;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (best < dp[j][i]) {
                ansI = i;
                ansJ = j;
                best = dp[j][i];
            }
        }
    }
    int cnt = 0;
    for (int i = ansI, j = ansJ; ;) {
        if (dp[j][i] == 0) {
            ans[cnt++] = i;
            ans[cnt++] = j;
            break;
        }
        ans[cnt++] = i;
        int k = p[j][i];
        i = j;
        j = k;        
    }
    printf("%d\n", cnt);
    for (int i = 0; i < cnt; i++) {
        if (i > 0) putchar(' ');
        printf("%d", id[ans[cnt - i - 1]] + 1);
    }
    puts("");
}
