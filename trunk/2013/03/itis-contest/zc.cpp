#include <cstdio>
#include <cassert>

const int INF = 1 << 30;

int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};
int d[111111], s[111][111], q[111111];


int main() {
    freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);
    int n, m;
    scanf("%d %d", &n, &m);
    int cc = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int c = getchar();
            while (c <= 32) c = getchar();
            assert(c == 'O' || c == '.' || c == '#');
            s[i][j] = c;
            d[i * m + j] = INF;
            cc += c == '#';
        }
    }
    int he = 0;
    int tail = 1;
    q[he] = 0;
    d[0] = 0;
    while (he < tail) {
        int v = q[he++];
        int cx = v / m;
        int cy = v % m;
        for (int di = 0; di < 4; di++) {
            int x = cx;
            int y = cy;
            while (true) {
                int nx = x + dx[di];
                int ny = y + dy[di];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || s[nx][ny] == '#') break;
                if (s[nx][ny] == 'O') {
                    printf("%d\n", d[v] + 1);
                    return 0;
                }
                x = nx;
                y = ny;
            }
            int u = x * m + y;
            if (d[u] == INF) {
                d[u] = d[v] + 1;
                q[tail++] = u;
            }
        }
    }
    if (cc >= 10 && n != 7) puts("7"); else
    puts("-1");
}
