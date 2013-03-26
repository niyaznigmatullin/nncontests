#include <cstdio>
#include <string>
#include <algorithm>

using std::string;

struct point {
    int x, y, z;
    
    point(int x, int y, int z) : x(x), y(y), z(z) {}
    point() : x(0), y(0), z(0) {}
    
    point operator - (point const & p) const {
        return point(x - p.x, y - p.y, z - p.z);
    }
    
    point operator * (point const & p) const {
        return point(y * p.z - z * p.y, z * p.x - x * p.z, x * p.y - y * p.x);
    }
    
    long long operator % (point const & p) const {
        return (long long) x * p.x + (long long) y * p.y + (long long) z * p.z;
    }
};

string name[111];
point p[111];
int ans[111];
char s[1233333];

int main() {
    int n = 0;
    gets(s);
    for (int i = 0; s[i]; i++) {
        if (s[i] >= '0' && s[i] <= '9') n = n * 10 + s[i] - '0';
    }
    for (int i = 0; i < n; i++) {
        gets(s);
        int j = 0;
        while (s[j]) ++j;
        --j;
        while (s[j] < '0' || s[j] > '9') --j;
        int k = j;
        while (s[k] >= '0' && s[k] <= '9') --k;
        int nn = 0;
        for (int t = k + 1; t <= j; t++) {
            nn = nn * 10 + s[t] - '0';
        }
        if (s[k] == '-') nn = -nn;
        p[i].z = nn;
        j = k;
        while (s[j] < '0' || s[j] > '9') --j;
        k = j;
        while (s[k] >= '0' && s[k] <= '9') --k;
        nn = 0;
        for (int t = k + 1; t <= j; t++) {
            nn = nn * 10 + s[t] - '0';
        }
        if (s[k] == '-') nn = -nn;
        p[i].y = nn;
        j = k;
        while (s[j] < '0' || s[j] > '9') --j;
        k = j;
        while (s[k] >= '0' && s[k] <= '9') --k;
        nn = 0;
        for (int t = k + 1; t <= j; t++) {
            nn = nn * 10 + s[t] - '0';
        }
        if (s[k] == '-') nn = -nn;
        p[i].x = nn;
        j = k;
        for (int t = 0; t < j; t++) {
            name[i] += s[t];
        }
        ans[i] = 0;
    }
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                point pl = (p[i] - p[j]) * (p[i] - p[k]);
                long long z = pl % p[i];
                if (pl.x == 0 && pl.y == 0 && pl.z == 0) continue;
                ++cnt;
                int neg = 0;
                int pos = 0;
                for (int t = 0; t < n; t++) {
                    long long sc = pl % p[t];
                    sc -= z;
                    if (sc < 0) neg = 1;
                    if (sc > 0) pos = 1;
                    if (neg && pos) break;
                }
                if (neg && pos) continue;
                for (int t = 0; t < n; t++) if (pl % p[t] == z) ans[t] = 1;
            }
        }
    }
    if (cnt == 0) for (int i = 0; i < n; i++) ans[i] = 1;
    int cn = 0;
    for (int i = 0; i < n; i++) if (ans[i]) {
        name[cn++] = name[i];
    }
    std::sort(name, name + cn);
    printf("%d\n", cn);
    for (int i = 0; i < cn; i++) printf("%s\n", name[i].c_str());
    return 0;
}