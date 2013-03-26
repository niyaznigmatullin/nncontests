#include <cstdio>
#include <memory.h>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
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

const int N = 1234567;
int link[26][N], sl[N], len[N], fr, was[N], tt[N];
long long dp[N];
char s[N], t[N];

int newNode(int l) {
    len[fr] = l;
    for (int i = 0; i < 26; i++) link[i][fr] = -1;
    sl[fr] = -1;
    return fr++;
}

int copyNode(int v, int l) {
    len[fr]  = l;
    for (int i = 0; i < 26; i++) link[i][fr] = link[i][v];
    sl[fr] = sl[v];
    return fr++;
}

int append(int v, int c) {
    if (link[c][v] >= 0) {
        int q = link[c][v];
        if (len[q] == len[v] + 1) return q;
        int cp = copyNode(q, len[v] + 1);
        while (v >= 0 && link[c][v] == q) {
            link[c][v] = cp;
            v = sl[v];
        }
        sl[q] = cp;
        return cp;
    }
    int u = newNode(len[v] + 1);
    while (v >= 0 && link[c][v] < 0) {
        link[c][v] = u;
        v = sl[v];
    }
    if (v < 0) {
        sl[u] = 0;
        return u;
    }
    int q = link[c][v];
    if (len[q] == len[v] + 1) {
        sl[u] = q;
        return u;
    }
    int cp = copyNode(q, len[v] + 1);
    while (v >= 0 && link[c][v] == q) {
        link[c][v] = cp;
        v = sl[v];
    }
    sl[u] = sl[q] = cp;
    return u;
}

int dfs(int v) {
    if (was[v]) return tt[v];
    was[v] = 1;
    for (int i = 0; i < 26; i++) {
        int go = link[i][v];
        if (go < 0) continue;
        tt[v] |= dfs(go);
    }
    return tt[v];
}

long long dfs2(int v) {
    if (was[v]) return dp[v];
    was[v] = 1;
    if (tt[v] != 3 && tt[v] != 0) dp[v] = 1;
    for (int i = 0; i < 26; i++) {
        int go = link[i][v];
        if (go < 0) continue;
        dp[v] += dfs2(go);
    }
    return dp[v];
}

int main() {
    fr = 0;
    newNode(0);
    gets(s);
    gets(t);
    int v = 0;
    for (int i = 0; s[i]; i++) {
        v = append(v, s[i] - 'a');
    }
    v = 0;
    for (int i = 0; t[i]; i++) {
        v = append(v, t[i] - 'a');
    }
    v = 0;
    for (int i = 0; s[i]; i++) {
        v = link[s[i] - 'a'][v];
    }
    while (v >= 0) {
        tt[v] |= 1;
        v = sl[v];
    }
    v = 0;
    for (int i = 0; t[i]; i++) {
        v = link[t[i] - 'a'][v];
    }
    while (v >= 0) {
        tt[v] |= 2;
        v = sl[v];
    }
    for (int i = 0; i < fr; i++) was[i] = 0;
    dfs(0);
    for (int i = 0; i < fr; i++) was[i] = 0, dp[i] = 0;
    printf("%lld\n", dfs2(0));
}
