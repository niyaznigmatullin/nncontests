#include <cstdio>
#include <memory.h>
#include <vector>
#include <ctime>
#include <cstdlib>

using namespace std;

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

struct rect {
    int x1, y1, x2, y2, d;
    rect() {}
    rect(int x1, int y1, int x2, int y2, int d) : x1(x1), y1(y1), x2(x2), y2(y2), d(d) {}
};

int n;
char c[55][55];
const int N = 12345;
int x1[N], y1[N], x2[N], y2[N], p[N];
int dp[55][55][55][55][2];
int pp[55][55][55][55][2];
int s[2][55][55];
int cnt;
vector<rect> ret;
int best = 1 << 30;

int cn(int i1, int j1, int i2, int j2, char d) {
    int ret = s[d == 'W'][i2 - 1][j2 - 1];
    if (i1 > 0) ret -= s[d == 'W'][i1 - 1][j2 - 1];
    if (j1 > 0) ret -= s[d == 'W'][i2 - 1][j1 - 1];
    if (i1 > 0 && j1 > 0) ret += s[d == 'W'][i1 - 1][j1 - 1];
    return ret;
}

int ITT;

int go(int i1, int j1, int i2, int j2, char d) {
    if (i1 >= i2 || j1 >= j2) return 0;
    int & ret = dp[i1][j1][i2][j2][d == 'W'];
    if (ret >= 0) return ret;
    int ok = cn(i1, j1, i2, j2, d == 'W' ? 'B' : 'W');
    int bad = cn(i1, j1, i2, j2, d);
    if (!bad) return ret = 0;
    if (!ok) return ret = 1;
    int & z = pp[i1][j1][i2][j2][d == 'W'];
    ret = 1 << 30;
    int small = j2 - j1;
    if (i2 - i1 > small) small = i2 - i1;
//    int itt = n >= 40 ? (small < 5 ? 10 : small < 20 ? 6 : small < 30 ? 6 : small < 40 ? 6 : 16) : ITT;
    int d1 = i2 - i1 - 1;
    int d2 = j2 - j1 - 1;
    if (d1 == 0) d1 = 1;
    if (d2 == 0) d2 = 1;
    if (j2 - 
    for (int it = 0; it < ITT; it++) {
        int m1 = rand() % (d1) + i1 + 1;//(i1 + i2) >> 1;
        int m2 = rand() % (d2) + j1 + 1;//(j1 + j2) >> 1;
        int f = !(it & 7);
        char nd = f ? (d == 'W' ? 'B' : 'W') : d;
//        char nd = d;
        int got = go(i1, j1, m1, m2, nd);
        got += go(i1, m2, m1, j2, nd);
        got += go(m1, j1, i2, m2, nd);
        got += go(m1, m2, i2, j2, nd);
        got += f;
        if (got < ret) {
            ret = got;
            z = m1 * N + m2;
            if (f) z = ~z;
        }
    }
    return ret;
}

void go2(int i1, int j1, int i2, int j2, char d) {
    if (dp[i1][j1][i2][j2][d == 'W'] == 0 || i1 >= i2 || j1 >= j2) return;
//    printf("%d %d %d %d %c\n", i1, j1, i2, j2, d);
    int ok = cn(i1, j1, i2, j2, d == 'W' ? 'B' : 'W');
    int bad = cn(i1, j1, i2, j2, d);
    if (!bad) return;
    if (!ok) {
        x1[cnt] = i1;
        x2[cnt] = i2;
        y1[cnt] = j1;
        y2[cnt] = j2;
        p[cnt] = d;
        ++cnt;
        return;
    }
    int z = pp[i1][j1][i2][j2][d == 'W'];
    if (z < 0) {
        x1[cnt] = i1;
        x2[cnt] = i2;
        y1[cnt] = j1;
        y2[cnt] = j2;
        p[cnt] = d;
        ++cnt;
        d = d == 'W' ? 'B' : 'W';
        z = ~z;
    }
    int m1 = z / N;
    int m2 = z % N;
    if (m1 != i2 || m2 != j2) go2(i1, j1, m1, m2, d);
    if (m1 != i2 || j1 != m2) go2(i1, m2, m1, j2, d);
    if (m1 != i1 || m2 != j2) go2(m1, j1, i2, m2, d);
    if (m1 != i1 || j1 != m2) go2(m1, m2, i2, j2, d);
}

void solve(char d) {
    cnt = 0;
    int aa = go(0, 0, n, n, d);
    if (aa >= best) return;
    best = aa;
    go2(0, 0, n, n, d);
    ret.clear();
    for (int i = 0; i < cnt; i++) ret.push_back(rect(x1[i], y1[i], x2[i], y2[i], p[i]));
}

const int SM = 25;

int gos(int i1, int j1, int i2, int j2, char d) {
    if (i2 - i1 < SM && j2 - j1 < SM) {
        return go(i1, j1, i2, j2, d);
    }
    int ok = cn(i1, j1, i2, j2, d == 'W' ? 'B' : 'W');
    int bad = cn(i1, j1, i2, j2, d);
    if (!bad) return 0;
    if (!ok) return 1;
    int m1 = (i1 + i2) >> 1;
    int m2 = (j1 + j2) >> 1;
    int got = gos(i1, j1, m1, m2, d);
    got += gos(i1, m2, m1, j2, d);
    got += gos(m1, j1, i2, m2, d);
    got += gos(m1, m2, i2, j2, d);
    return got;
}

void gos2(int i1, int j1, int i2, int j2, char d) {
    if (i2 - i1 < SM && j2 - j1 < SM) {
        go2(i1, j1, i2, j2, d);
        return;
    }
    int ok = cn(i1, j1, i2, j2, d == 'W' ? 'B' : 'W');
    int bad = cn(i1, j1, i2, j2, d);
    if (!bad) return;
    if (!ok) {
        x1[cnt] = i1;
        x2[cnt] = i2;
        y1[cnt] = j1;
        y2[cnt] = j2;
        p[cnt] = d;
        ++cnt;
        return;
    }
    int m1 = (i1 + i2) >> 1;
    int m2 = (j1 + j2) >> 1;
    gos2(i1, j1, m1, m2, d);
    gos2(i1, m2, m1, j2, d);
    gos2(m1, j1, i2, m2, d);
    gos2(m1, m2, i2, j2, d);
}

void solve2() {
    cnt = 0;
//    memset(dp, -1, sizeof dp);
    int got = gos(0, 0, n, n, 'W');
    if (got >= best) return;
    gos2(0, 0, n, n, 'W');
    ret.clear();
    for (int i = 0; i < cnt; i++) ret.push_back(rect(x1[i], y1[i], x2[i], y2[i], p[i]));    
}

int main() {
    srand(time(NULL));
    n = ni();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int ch = getchar();
            while (ch != 'W' && ch != 'B') ch = getchar();
            c[i][j] = ch;
        }
    }
    memset(dp, -1, sizeof dp);
    for (int q = 0; q < 2; q++) {
        char d = q == 0 ? 'B' : 'W';
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) s[q][i][j] = c[i][j] == d;
        for (int i = 0; i < n; i++)
            for (int j = 1; j < n; j++) s[q][i][j] += s[q][i][j - 1];
        for (int i = 1; i < n; i++)
            for (int j = 0; j < n; j++) s[q][i][j] += s[q][i - 1][j];
    }
    const int IT = n < 20 ? 4 : n < 30 ? 2 : n < 40 ? 0 : 0;
    ITT = n < 20 ? 40 : n < 30 ? 15 : n < 40 ? 14 : 40;
//    solve('B');
    solve2();
    for (int i = 0; i < IT; i++) {
        solve('B');
    }
    printf("%d\n", (int) ret.size());
    for (int i = 0; i < (int) ret.size(); i++) {
        rect & r = ret[i];
        printf("%d %d %d %d %c\n", r.x1, r.y1, r.x2 - 1, r.y2 - 1, r.d);
    }
}
