#include <cstdio>
#include <algorithm>

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

int a[255][255];

int sum(int x1, int y1, int x2, int y2) {
    int ret = a[x2 - 1][y2 - 1];
    if (x1 > 0) ret -= a[x1 - 1][y2 - 1];
    if (y1 > 0) ret -= a[x2 - 1][y1 - 1];
    if (x1 > 0 && y1 > 0) ret += a[x1 - 1][y1 - 1];
    return ret;
}

int main() {
    int n = ni();
    int m = ni();
    for (int i = 0; i < n; i++) 
        for (int j = 0; j < m; j++) {
            a[i][j] = ni();
        }
    for (int i = 1; i < n; i++)
        for (int j = 0; j < m; j++)
            a[i][j] += a[i - 1][j];
    for (int i = 0; i < n; i++)
        for (int j = 1; j < m; j++)
            a[i][j] += a[i][j - 1];
    int ansI = -1;
    int ansJ = -1;
    int ad = (1 << 30);
    for (int i = 1; i < n; i++)
        for (int j = 1; j < m; j++) {
            int s1 = sum(0, 0, i, j);
            int s2 = sum(0, j, i, m);
            int s3 = sum(i, 0, n, j);
            int s4 = sum(i, j, n, m);
            int d = max(max(s1, s2), max(s3, s4)) - min(min(s1, s2), min(s3, s4));
            if (ad > d) {
                ad = d;
                ansI = i;
                ansJ = j; 
            }
        }
    printf("%d %d\n", ansI, ansJ);
}