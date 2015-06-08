#include <bits/stdc++.h>

using namespace std;

struct state {
    signed char x1;
    signed char y1;
    signed char x2;
    signed char y2;
};

vector<pair<int, int> > get(int p1, int q1) {
    return {{-p1, -q1}, {-p1, q1}, {p1, -q1}, {p1, q1}, {-q1, -p1}, {-q1, p1}, {q1, -p1}, {q1, p1}};
}

int from[55][55][55][55];
bool was[55][55][55][55];
state q[55 * 55 * 55 * 55];


int main() {
    int n, m;
    scanf("%d%d", &n, &m);
    int p1, q1, p2, q2;
    scanf("%d%d%d%d", &p1, &q1, &p2, &q2);
    int x1, y1, x2, y2;
    scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
    --x1;
    --y1;
    --x2;
    --y2;
    int head = 0;
    int tail = 0;
    q[tail++] = {x1, y1, x2, y2};
    vector<pair<int, int> > m1 = get(p1, q1), m2 = get(p2, q2);
    was[x1][y1][x2][y2] = true;
    from[x1][y1][x2][y2] = -1;
    while (head < tail) {
        state v = q[head++];
        if (v.x1 == v.x2 && v.y1 == v.y2) {
            vector<state> ans;
            while (true) {
                ans.push_back(v);
                if (from[v.x1][v.y1][v.x2][v.y2] < 0) break;
                v = q[from[v.x1][v.y1][v.x2][v.y2]];
            }
            reverse(ans.begin(), ans.end());
            printf("%d\n", (int) ans.size() - 1);
            for (state & e : ans) {
                printf("%d %d %d %d\n", e.x1 + 1, e.y1 + 1, e.x2 + 1, e.y2 + 1);
            }
            return 0;
        }
        for (pair<int, int> d1 : m1) {
            int nx1 = v.x1 + d1.first;
            int ny1 = v.y1 + d1.second;
            if (nx1 < 0 || ny1 < 0 || nx1 >= n || ny1 >= m) continue;
            for (pair<int, int> d2 : m2) {
                int nx2 = v.x2 + d2.first;
                int ny2 = v.y2 + d2.second;
                if (nx2 < 0 || ny2 < 0 || nx2 >= n || ny2 >= m) continue;
                if (was[nx1][ny1][nx2][ny2]) continue;
                q[tail++] = {nx1, ny1, nx2, ny2};
                was[nx1][ny1][nx2][ny2] = true;
                from[nx1][ny1][nx2][ny2] = head - 1;
            }
        }
    }
    puts("-1");
}
