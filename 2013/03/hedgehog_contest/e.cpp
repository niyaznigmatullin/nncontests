#include <cstdio>

int dx[] = {1, 0, -1, 0};
int dy[] = {0, 1, 0, -1};
const int INF = 1 << 30;
int q[1111 * 1111], d[1111][1111], id[1111][1111], ans[33333];

int main() {
    int n, m, k, t;
    scanf("%d %d %d %d", &n, &m, &k, &t);
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++) d[i][j] = INF;
    int head = 0;
    int tail = 0;
    for (int i = 0; i < k; i++) {
        int x, y;
        scanf("%d %d", &x, &y);
        --x;
        --y;
        q[tail++] = x * m + y;
        d[x][y] = 0;
        id[x][y] = i;
    }
    while (head < tail) {
        int v = q[head++];
        int cx = v / m;
        int cy = v % m;
        for (int dir = 0; dir < 4; dir++) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];
            if (nx < 0 || ny < 0 || nx >= n || ny >= m || d[nx][ny] <= d[cx][cy] || (d[nx][ny] == d[cx][cy] + 1 && id[nx][ny] <= id[cx][cy])) continue;
            if (d[nx][ny] == INF) {
                q[tail++] = nx * m + ny;
            }
            d[nx][ny] = d[cx][cy] + 1;
            id[nx][ny] = id[cx][cy];
        }
    }
    for (int i = 0; i < k; i++) ans[i] = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (d[i][j] <= t) ans[id[i][j]]++;
        }
    }
    for (int i = 0; i < k; i++) printf("%d\n", ans[i]);
}