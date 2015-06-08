#include <bits/stdc++.h>
using namespace std;

int s[42][42][42];
int p[42 * 42 * 42];

int cn;
int a[1234][1234];
int was[1234];
int id[1234];

int get(int x) {
    return x == p[x] ? x : (p[x] = get(p[x]));
}

int dfs(int v) {
    was[v] = true;
    int ret = 1;
    for (int i = 0; i < cn; i++) if (a[v][i] && !was[i]) ret += dfs(i);
    return ret;
}


void solve() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                int c = getchar();
                while (c <= 32) c = getchar();
                s[i][j][k] = c;
            }
        }   
    }
    for (int i = 0; i < n * n * n; i++) p[i] = i;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            if (dx * dx + dy * dy + dz * dz != 1) continue;
                            int x = i + dx;
                            int y = j + dy;
                            int z = k + dz;
                            if (x < 0 || y < 0 || z < 0 || x >= n || y >= n || z >= n || s[i][j][k] != s[x][y][z]) continue;
                            p[get(i * n * n + j * n + k)] = get(x * n * n + y * n + z);
                        }
                    }
                }
            }
        }
    }
    cn = 0;
    for (int i = 0; i < n * n * n; i++) if (i == get(i)) id[i] = cn++;
    for (int dx = -1; dx <= 1; dx++) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dz = -1; dz <= 1; dz++) if (dx * dx + dy * dy + dz * dz == 1) {
                for (int i = 0; i < cn; i++)
                    fill(a[i], a[i] + cn, 0);
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        for (int z = 0; z < n; z++) {
                            int nx = x + dx;
                            int ny = y + dy;
                            int nz = z + dz;
                            if (nx < 0 || ny < 0 || nz < 0 || nx >= n || ny >= n || nz >= n || s[x][y][z] == s[nx][ny][nz]) {
                                
                            } else {
                                int f = id[get(x * n * n + y * n + z)];
                                int g = id[get(nx * n * n + ny * n + nz)];
                                a[f][g] = 1;
                            }
                        }
                    }
                }
                fill(was, was + cn, 0);
                if (dfs(0) != cn) {
                    puts("No");
                    return;
                }
                for (int i = 0; i < cn; i++)
                    for (int j = i + 1; j < cn; j++) swap(a[i][j], a[j][i]);
                fill(was, was + cn, 0);
                if (dfs(0) != cn) {
                    puts("No");
                    return;
                }
            }
        }
    }
    puts("Yes");
}


int main() {
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
