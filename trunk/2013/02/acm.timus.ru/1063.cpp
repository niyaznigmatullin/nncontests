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

int n;
int m;
int ss[111], ff[111], p[7], deg[7];
int sa[111], fa[111], ans, cna;
int z[7];

int get(int x) {
    return x == p[x] ? x : (p[x] = get(p[x]));
}

void solve() {
    for (int i = 1; i <= 6; i++) {
        if (!deg[i]) continue;
        for (int j = i + 1; j <= 6; j++) {
            if (!deg[j]) continue;
            if (z[i] != z[j]) return;
        }
    }
    int hg = 0;
    int got = m - n;
    for (int i = 6; i >= 1; i--) {
        if (deg[i] % 2 == 0) {
            continue;
        }
        if (hg >= 2) {
            ((hg & 1) ? ff : ss)[m + (hg - 2) / 2] = i;
        }
        ++hg;
    }
    got += hg > 2 ? (hg - 2) / 2 : 0;
    int sum = 0;
    for (int i = n; i < n + got; i++) sum += ss[i] + ff[i];
    if (sum < ans) {
        ans = sum;
        cna = got;
        for (int i = n; i < n + got; i++) sa[i - n] = ss[i], fa[i - n] = ff[i];
    }
}


void go(int x) {
    m = n + x;
    solve();
    int f[7];
    for (int i = 1; i <= 6; i++) f[i] = z[i];
    for (int i = 1; i <= 6; i++) {
        for (int j = i + 1; j <= 6; j++) {
            if (f[i] == f[j]) continue;
            deg[i]++;
            deg[j]++;
            ss[n + x] = i;
            ff[n + x] = j;
            for (int k = 1; k <= 6; k++) p[k] = f[k];
            p[get(i)] = get(j);
            for (int k = 1; k <= 6; k++) z[k] = get(k);
            go(x + 1);
            deg[i]--;
            deg[j]--;
        }
    }
}

int main() {
    n = ni();
    for (int i = 0; i < n; i++) {
        ss[i] = ni();
        ff[i] = ni();
    } 
    ans = 1 << 30;
    for (int i = 1; i <= 6; i++) p[i] = i;
    for (int i = 0; i < n; i++) {
        p[get(ss[i])] = get(ff[i]);
        deg[ss[i]]++;
        deg[ff[i]]++;
    }
    for (int i = 1; i <= 6; i++) z[i] = get(i);
    go(0);
    printf("%d\n%d\n", ans, cna);
    for (int i = 0; i < cna; i++) {
        printf("%d %d\n", sa[i], fa[i]);
    }
}