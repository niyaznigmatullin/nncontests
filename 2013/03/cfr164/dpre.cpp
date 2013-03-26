#include <cstdio>
#include <memory.h>


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

const int MOD = 1000000009;
int dp[33][33][33][33], ne[33][33][33][33], n, h;


inline void add(int & a, int b) {
    a += b;
    if (a >= MOD) a -= MOD;
}

inline int f(int i) {
    if (i < 0) {
        i = ~i;
        if (i < h) i = 0;
    } else {
        if (i < h) ++i;
    }
    return i;
}

inline void up(int i, int j, int k, int l, int val) {
    i = f(i);
    j = f(j);
    k = f(k);
    l = f(l);
    add(ne[i][j][k][l], val);
}

int main() {
    n = 1000;
    for (h = 22; h <= 30; h++) {
        fprintf(stderr, "h = %d\n", h);
        printf("{");
        memset(dp, 0, sizeof dp);
        memset(ne, 0, sizeof ne);
        dp[0][0][0][0] = 1;
        for (int it = 0; it < n; it++) {
            for (int i = 0; i <= h; i++) {
                for (int j = 0; j <= h; j++) {
                    for (int k = 0; k <= h; k++) {
                        for (int l = 0; l <= h; l++) {
                            int val = dp[i][j][k][l];
                            if (val == 0) continue;
                            up(~i, j, k, l, val);
                            up(i, ~j, k, l, val);
                            up(i, j, ~k, l, val);
                            up(i, j, k, ~l, val);
                        }
                    }
                }
            }
            int cur = 0;
            for (int i = 0; i <= h; i++) {
                for (int j = 0; j <= h; j++) {
                    for (int k = 0; k <= h; k++) {
                        for (int l = 0; l <= h; l++) {
                            if (i < h || j < h || k < h || l < h) add(cur, ne[i][j][k][l]);
                            dp[i][j][k][l] = ne[i][j][k][l];
                            ne[i][j][k][l] = 0;
                        }
                    }
                }
            }
            if (it > 200)
            printf("0x%x,", cur);
        }
        puts("},");
    }
}
