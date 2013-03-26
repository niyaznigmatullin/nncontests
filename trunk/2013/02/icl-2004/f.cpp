#include <cstdio>
#include <memory.h>

using namespace std;

int ni() {
    int c = getchar();
    while (c < '0' || c > '9') {
        c = getchar();
    }
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return ret;
}

int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};
int n;
char c[20][20];
int was[20][20];
int b[20][20];
int cb;
int ci, cj;

int dfs(int i, int j) {
    was[i][j] = 1;
    int ret = 1;
    for (int d = 0; d < 4; d++) {
        int x = i + dx[d];
        int y = j + dy[d];
        if (x < 0 || y < 0 || x >= n || y >= n || c[x][y] == 'b' || was[x][y]) continue;
        if (c[x][y] == '.') {
            ++cb;
            was[x][y] = 1;
            ci = x;
            cj = y;
            continue;
        }
        ret += dfs(x, y);
    }
    return ret;
}

int main() {
    n = ni();
    for (int i = 0; i < n; i++) 
        for (int j = 0; j < n; j++) {
            int ch = getchar();
            while (ch != '.' && ch != 'b' && ch != 'w') ch = getchar();
            c[i][j] = ch;
        }
        
    int best = 0;
    int ai = -1;
    int aj = -1;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {
            if (was[i][j] || c[i][j] == 'b' || c[i][j] == '.') continue;
            cb = 0;
            int got = dfs(i, j);
            if (cb == 1) {
                b[ci][cj] += got;
            }
            for (int i1 = 0; i1 < n; i1++) for (int j1 = 0; j1 < n; j1++) if (c[i1][j1] == '.') was[i1][j1] = 0;
        }
    for (int i = 0; i < n; i++) 
        for (int j = 0; j < n; j++)
            if (b[i][j] > 0 && b[i][j] > best) {
                best = b[i][j];
                ai = i;
                aj = j;
            }
    if (ai < 0) printf("0\n"); else
    printf("%d %d %d\n", ai + 1, aj + 1, best);
}
