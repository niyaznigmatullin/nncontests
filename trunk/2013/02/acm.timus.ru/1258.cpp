#include <cstdio>
#include <cmath>

int ni() {
    int c = getchar();
    while (c != '-' && (c < '0' || c > '9')) c = getchar();
    int sg = c == '-';
    if (sg) c = getchar();
    int ret = 0;
    while (c >= '0' && c <= '9') {
        ret = ret * 10 + c - '0';
        c = getchar();
    }
    return sg ? -ret : ret;
}

const int INF = ~(1 << 31);


int main() {
    int w = ni();
    int d = ni();
    int x0 = ni();
    int y0 = ni();
    int x1 = ni();
    int y1 = ni();
    int c = getchar();
    while (c < 'A' && c > 'Z') c = getchar();    
    int xx = 0;
    int yy = 0;
    int dyy = INF;
    int dxx = INF;
    while (c >= 'A' && c <= 'Z') {
        if (c == 'F' || c == 'B') {
            if (dyy == INF) dyy = c == 'F' ? -d : d;
            ++yy;
        } else {
            if (dxx == INF) dxx = c == 'L' ? -w : w;
            ++xx;
        }
        c = getchar();
    }
    x1 = (xx & 1) ? w - x1 : x1;
    y1 = (yy & 1) ? d - y1 : y1;
    x1 += dxx * xx;
    y1 += dyy * yy;
    double dx = x0 - x1;
    double dy = y0 - y1;
    printf("%.4lf\n", sqrt(dx * dx + dy * dy));
}