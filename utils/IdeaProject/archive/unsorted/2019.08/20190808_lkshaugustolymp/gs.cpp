#include <algorithm>
#include <iostream>
#include <random>
#include <vector>
#include "testlib.h"

using namespace std;

int n, m;
int f[1110][1110];
int u[1110][1110];
int dx[8] = { 0, 1, 0, -1, 1, 1, -1, -1 };
int dy[8] = { 1, 0, -1, 0, 1, -1, 1, -1 };

void recursive(int x, int y)
{
    u[x][y] = 1;
    vector<int> fr;
    for (int i = 0; i < 4; i++)
    {
        int nx = x + 2 * dx[i];
        int ny = y + 2 * dy[i];
        if (nx < 0 || nx >= n || ny < 0 || ny >= m || u[nx][ny])
            continue;
        fr.push_back(i);
    }
    if (fr.empty())
        return;
    shuffle(fr.begin(), fr.end());
    for (int i = 0; i < (int)fr.size(); i++)
    {
        int nx = x + 2 * dx[fr[i]];
        int ny = y + 2 * dy[fr[i]];
        int tx = x + dx[fr[i]];
        int ty = y + dy[fr[i]];
        if (u[nx][ny] == 1)
            continue;
        f[tx][ty] = 0;
        recursive(nx, ny);
    }
}

int main(int argc, char * argv[])
{
    registerGen(argc, argv, 1);

    n = 999;
    m = 999;
    int a = 1000;
    int b = 1;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            for (int k = 0; k < 8; k++) {
                f[i][j] = 0;
            }
        }
    }
    for (int i = 0; i < n; i += 2) {
        for (int j = 0; j < m; j += 2) {
            for (int k = 0; k < 8; k++) {
                if (i + dx[k] >= 0 && j + dy[k] >= 0) {
                    f[i + dx[k]][j + dy[k]] = 1000000000;
                }
            }
        }
    }
    recursive(0, 0);
    f[n - 1][m - 1] = 0;
    f[n - 2][m - 1] = 0;
    f[n - 1][m - 2] = 0;
    cout << n << ' ' << m << ' ' << a << ' ' << b << '\n';
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cout << f[i][j];
            if (j + 1 != m) {
                cout << ' ';
            }
        }
        cout << '\n';
    }

    return 0;
}
