#include <cstdio>
#include <cassert>


inline int max(int a, int b) {
    return a > b ? a : b;
}

inline int min(int a, int b) {
    return a > b ? b : a;
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


const int N = 2555;
const int PN = 5555;
char c[N][N];
short lid[N][N];
int tid[PN], dst[PN], sd[PN];
int cols[N];
int cntl[26], cntpl, wh[PN], zid[PN];
int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};

int nf() {
    int c = getchar();
    while (c < 'A' || c > 'Z') c = getchar();
    int f = c - 'A';
    c = getchar();
    int x = 0;
    while (c >= '0' && c <= '9') {
        x = x * 10 + c - '0';
        c = getchar();
    }
    --x;
    return (f > 0 ? cntl[f - 1] : 0) + x;
}

int ds(int v, int d) {
    if (d == 0) return 0;
    int d1 = v == 0 ? 0 : -sd[v - 1];
    int nv = v + d;
    if (nv >= cntpl) {
        d1 += sd[cntpl - 1];
        nv -= cntpl;
    }
    if (nv > 0) d1 += sd[nv - 1];
    return d1;
}

int lsd(int v, int d) {
    int nv = v + d;
    if (nv >= cntpl) {
        nv -= cntpl;
    }
    int d1;
    if (nv > 0) d1 = dst[nv - 1]; else d1 = dst[cntpl - 1];
    return d1;
}

int slv(int v, int u) {
    if (v == u) return 0;
    int d = (u + cntpl - v) % cntpl;
    if (d == 1) {
        return dst[v];
    }
    if (d == 2) {
        int nv = (v + 1) % cntpl;
        int ans = max(dst[v], dst[nv]) + min(dst[v], dst[nv]) * 2;
        return ans;
    }
    int l = 0;
    int r = d - 1;
    while (l < r - 1) {
        int mid = (l + r) >> 1;
        int vv = (v + mid + 1);
        if (vv >= cntpl) vv -= cntpl;
        if (ds(v, mid) > ds(vv, d - mid - 1)) r = mid; else l = mid;
    }
    int ans = 1 << 30;
    for (int lo = l + 1; lo <= d && lo <= l + 2; lo++) {
        ans = min(ans, max(ds(v, lo - 1), ds((v + lo) % cntpl, d - lo)) * 2 + lsd(v, lo));
    }
    return ans;
}

int wall(int c) {
    return c == '-' || c == '|' || c == '+';
}

int main() {
    char z[20];
    gets(z);
    int n = 0;
    for (int i = 0; z[i]; i++) {
        if (z[i] >= '0' && z[i] <= '9') {
            n = n * 10 + z[i] - '0';
        }
    }
    for (int i = 0; i < n; i++) {
        gets(c[i]);
        cols[i] = 0;
        while (c[i][cols[i]]) ++cols[i];
    }
    int fx = -1;
    int fy = -1;
    int cc = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < cols[i]; j++) if (c[i][j] >= 'A' && c[i][j] <= 'Z') {
            cntl[c[i][j] - 'A']++; 
            if (fx < 0) {
                fx = i;
                fy = j;
            }
            zid[cc++] = i * N + j;
        } else lid[i][j] = -1;
    }
    for (int i = 1; i < 26; i++) cntl[i] += cntl[i - 1];
    {
        int wa[26];
        for (int i = 0; i < 26; i++) wa[i] = 0;
        for (int it = 0; it < cc; it++) {
            int i = zid[it] / N;
            int j = zid[it] - i * N;
            int f = c[i][j] - 'A';
            if (f >= 0 && f < 26) {
                lid[i][j] = wa[f] + (f > 0 ? cntl[f - 1] : 0);
                wa[f]++;
            }
        }
    }
    int cur = 1;
    tid[0] = lid[fx][fy];
    {
        int i = fx;
        int j = fy;
        int dd = 0;
        {
            int ni = i + dx[dd];
            int nj = j + dy[dd];
            if (wall(c[ni][nj])) dd++;
        }
        int cnt = 0;
        while (true) {
            int ni = i + dx[dd - 1 & 3];
            int nj = j + dy[dd - 1 & 3];
            if (wall(c[ni][nj])) {
                ni = i + dx[dd];
                nj = j + dy[dd];
                if (wall(c[ni][nj])) {
                    sd[cur - 1] = dst[cur - 1] = cnt;
                    if (lid[i][j] == tid[0]) break;
                    cnt = 0;
                    tid[cur++] = lid[i][j];
                    dd = dd + 1 & 3;
                    if (wall(c[i + dx[dd]][j + dy[dd]])) dd = dd + 1 & 3;
                    i = i + dx[dd];
                    j = j + dy[dd];
                } else {
                    i = ni;
                    j = nj;
                }
            } else {
                i = ni;
                j = nj;
                dd = dd - 1 & 3;
            }
            ++cnt;
        }
    }
    for (int i = 1; i < cur; i++) sd[i] += sd[i - 1];
    cntpl = cur;
    for (int i = 0; i < cur; i++) wh[tid[i]] = i;
    int t = ni();
    for (int i = 0; i < t; i++) {
        int v = nf();
        int u = nf();
        v = wh[v];
        u = wh[u];
        int ans = slv(v, u);
        int ans2 = slv(u, v);
        if (ans > ans2) ans = ans2;
        printf("%d.%d0\n", ans >> 1, 5 * (ans & 1));
    }
}