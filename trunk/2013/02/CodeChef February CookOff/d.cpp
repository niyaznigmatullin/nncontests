#include <cstdio>
#include <cassert>
#include <set>
#include <cmath>

using namespace std;
long long nl() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) {
        c = getchar();
    }
    int sg = 0;
    if (c == '-') {
        sg = 1;
        c = getchar();
    }
    long long ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

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

const int MAXN = 444444;
const int LAST = 31415926;
int who[MAXN], tin[MAXN], ans[MAXN], want[MAXN];

void solve() {
    int r = ni();
    int n = ni();
    for (int i = 0; i < n; i++) {
        who[i] = -1;
    }
    int fr = 0;
    for (int i = 0; i < n; i++) {
        tin[i] = ni();
        int inc = ni();
        if (inc == r) {
            want[i] = 0;
            ans[i] = 1;
            continue;
        }
        if (who[fr] >= 0) {
            int j = who[fr];
            ans[j] = tin[i] - tin[j];
        }
        who[fr] = i;
        want[i] = (fr - inc + r) % r;
        fr++;
        if (fr == r) fr = 0;
    }
    for (int i = 0; i < n; i++) {
        if (who[i] >= 0) ans[who[i]] = LAST - tin[who[i]];
    }
    for (int i = 0; i < n; i++) {
        printf("%d %d\n", want[i], ans[i]);
    }
}

int main() {
    int t = ni();
    for (int i = 0; i < t; i++) {
        solve();
    }
}