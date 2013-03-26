#include <cstdio>
#include <algorithm>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
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

int row[1111], col[1111], id[1111], id2[1111];
int a[123456], b[123456], c[123456];

int main() {
    int n = ni();
    for (int i = 1; i < n; i++) {
        int x = ni() - 1;
        int y = ni() - 1;
        row[x]++;
        col[y]++;
    }    
    for (int i = 0; i < n; i++) id[i] = id2[i] = i;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++)
            if (row[id[i]] > row[id[j]]) {
                int t = id[i];
                id[i] = id[j];
                id[j] = t;
            }
    int cn = 0;
    for (int i = 0; i < n; i++) {
        if (id2[i] == id[i]) continue;
        for (int j = 0; j < n; j++) {
            if (id2[j] == id[i]) {
                a[cn] = 1;
                b[cn] = j;
                c[cn] = i;
                ++cn;
                int t = id2[j];
                id2[j] = id2[i];
                id2[i] = t;
            }
        }
    }
    for (int i = 0; i < n; i++) id[i] = id2[i] = i;
    for (int i = 0; i < n; i++)
        for (int j = i + 1; j < n; j++)
            if (col[id[i]] < col[id[j]]) {
                int t = id[i];
                id[i] = id[j];
                id[j] = t;
            }
    for (int i = 0; i < n; i++) {
        if (id2[i] == id[i]) continue;
        for (int j = 0; j < n; j++) {
            if (id2[j] == id[i]) {
                a[cn] = 2;
                b[cn] = j;
                c[cn] = i;
                ++cn;
                int t = id2[j];
                id2[j] = id2[i];
                id2[i] = t;
            }
        }
    }
    printf("%d\n", cn);
    for (int i = 0; i < cn; i++) {
        printf("%d %d %d\n", a[i], b[i] + 1, c[i] + 1);
    }
}
