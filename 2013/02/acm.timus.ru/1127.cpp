#include <cstdio>
#include <string>
#include <map>
#include <set>

using std::map;
using std::string;
using std::set;


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


map<string, int> cnt;
set<string> was;

int DX[2][6] = {{5, 1, 2, 4, 0, 3}, {0, 4, 5, 3, 2, 1}};

void go(string a) {
    string b = a.substr(0, 4);
    if (was.find(b) != was.end()) return;
    was.insert(b);
    cnt[b]++;
    for (int g = 0; g < 2; g++) {
        string c = "aaaaaa";
        for (int i = 0; i < 6; i++) c[i] = a[DX[g][i]];
        go(c);
    }
}

int main() {
    int n = ni();
    for (int i = 0; i < n; i++) {
        int c = getchar();
        while (c < 'A' || c > 'Z') c = getchar();
        string a = "";
        while (c >= 'A' && c <= 'Z') {
            a += (char) c;
            c = getchar();
        }
        was.clear();
        go(a);
    }
    int ans = 0;
    for (map<string, int>::iterator it = cnt.begin(); it != cnt.end(); ++it) {
        int z = it->second;
        if (ans < z) ans = z;
    }
    printf("%d\n", ans);
}