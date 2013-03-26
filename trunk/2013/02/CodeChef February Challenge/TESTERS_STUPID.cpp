
#include <cstdio>
#include <algorithm>
#include <memory.h>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

int n, curt;

const int NN = 2222222;
int sq[NN];
const int TN = 666666;
int ss[TN], ff[TN], he[TN], ne[TN], lab[TN], slab[TN], pv[TN], bc[TN], trt[TN], en[TN], ex[TN], lid[TN], fs[TN], heq[TN], de[TN], sz[TN], was[TN];
long long answer;

void dfs(int v, int t, int s, int len) {
    was[v] = 1;
    if (v == t) {
        if (s <= 0) answer += len;
        return;
    }
    for (int e = he[v]; e >= 0; e = ne[e]) {
        if (was[ff[e]]) continue;
        dfs(ff[e], t, s + lab[ff[e]], len + 1);
    }
}

int main() {
    n = ni();
    for (int i = 0; i < n; i++) {
        he[i] = -1;
    }
    for (int i = 0; i < n - 1; i++) {
        ss[i] = ni() - 1;
        ff[i] = ni() - 1;
        ss[i + n - 1] = ff[i];
        ff[i + n - 1] = ss[i];
    }
    for (int i = 0; i < 2 * (n - 1); i++) {
        ne[i] = he[ss[i]];
        he[ss[i]] = i;
    }
    for (int i = 0; i < NN; i++) sq[i] = 0;
    for (int i = 1; i * i < NN; i++) sq[i * i] = 1;
    for (int i = 0; i < n; i++) {
        int x = ni();
        while ((x & 1) == 0) x >>= 1;
        lab[i] = sq[x] ? -1 : 1;
    }
    answer = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {
            for (int k = 0; k < n; k++) was[k] = 0;
            dfs(i, j, lab[i], 1);
        }
    }
    printf("%lld\n", answer);
}