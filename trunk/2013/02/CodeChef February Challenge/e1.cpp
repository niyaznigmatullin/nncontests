#include <cstdio>


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

char c[55][55];

int main() {
    int n = ni();
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            int ch = getchar();
            while (ch != 'W' && ch != 'B') ch = getchar();
            c[i][j] = ch;
        }
    }
    int wh = 0;
    for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) if (c[i][j] == 'W') ++wh;
    char d = wh * 2 > n * n ? 'B' : 'W';
    int ans = d == 'W' ? wh + 1 : (n * n - wh);
    printf("%d\n", ans);
    if (d == 'W') printf("%d %d %d %d B\n", 0, 0, n - 1, n - 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == d)
                    printf("%d %d %d %d %c\n", i, j, i, j, d);
            }
        }
}