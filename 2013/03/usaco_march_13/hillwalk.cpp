#include <cstdio>
#include <cassert>
#include <set>
#include <algorithm>
#define y1 y1nouse
using std::set;

inline int ni() {
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

const int INF = ~(1 << 31);
const int N = 111111;
int x1[N], y1[N], x2[N], y2[N], st[N], fi[N], go[N];

bool le(int i, int j) {
    return x1[i] < x1[j];
}

bool ri(int i, int j) {
    return x2[i] < x2[j];   
}

int ct;

double gy(int i) {
    if (y1[i] == y2[i]) {
        return y1[i];
    }
    return y1[i] + 1. * (ct - x1[i]) * (y2[i] - y1[i]) / (x2[i] - x1[i]);
}

class tt {
public:
    bool operator()(int i, int j) {
        return gy(i) < gy(j) - 1e-9;
    }
};

int main() {
    freopen("hillwalk.in", "r", stdin);
    freopen("hillwalk.out", "w", stdout);
    int n = ni();
    for (int i = 0; i < n; i++) {
        x1[i] = ni();
        y1[i] = ni();
        x2[i] = ni();
        y2[i] = ni();
        st[i] = i;
        fi[i] = i;
        go[i] = -2;
    }    
    std::sort(st, st + n, le);
    std::sort(fi, fi + n, ri);
    set<int, tt> q;
    set<int, tt> :: iterator it;
    int cur = 0;
    for (int i = 0; i <= n; i++) {
        int c1 = cur;
        int zi = i == n ? INF : x1[st[i]];
        while (cur < n && x2[fi[cur]] < zi) {
            ++cur;
        }
        for (int j = c1; j < cur; ) {
            int k = j;
            ct = x2[fi[j]];
            while (k < cur && x2[fi[j]] == x2[fi[k]]) {
                it = q.find(fi[k]);
                assert(it != q.end());
                q.erase(it);
                k++;
            }
            while (j < k) {
                it = q.upper_bound(fi[j]);
                if (it == q.begin()) go[fi[j]] = -1; else go[fi[j]] = *(--it);
                j++;
            }
        }
        if (i == n) break;
        ct = x1[st[i]];
        q.insert(st[i]);
    }
    int ans = 0;
    for (int i = 0; i >= 0; i = go[i]) ++ans;
    printf("%d\n", ans);
}