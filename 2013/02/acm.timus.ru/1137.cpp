#include <cstdio>


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

int ss[123456], ff[123456], ne[123456], he[12345], ans[123456], cnt;

void dfs(int v) {
    while (true) {
        int e = he[v];
        if (e < 0) break;
        he[v] = ne[e];
        dfs(ff[e]);
    }
    ans[cnt++] = v;
}

int main() {
    int n = ni();
    int ed = 0;
    for (int i = 0; i < n; i++) {
        int m = ni();
        int last = ni();
        for (int j = 0; j < m; j++) {
            ss[ed] = last;
            last = ni();
            ff[ed] = last;
            ++ed;
        }
    }
    for (int i = 1; i <= 10000; i++) {
        he[i] = -1;
    }
    for (int i = 0; i < ed; i++) { 
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    cnt = 0;
    for (int i = 1; i <= 10000; i++) {
        if (he[i] >= 0) {
            dfs(i);
            break;
        }
    }
    if (cnt - 1 != ed) {
        puts("0");
        return 0;
    }
    printf("%d", cnt - 1);
    for (int i = 0; i < cnt; i++) {
        printf(" %d", ans[cnt - i - 1]);
    }
    puts("");
}