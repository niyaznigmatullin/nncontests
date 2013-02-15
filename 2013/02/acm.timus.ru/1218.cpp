#include <cstdio>
#include <string>

using std::string;


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

string names[234];
int ed[234][234], was[234], q[234], a[234], b[234], c[234];

int ia(int c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
}

int main() {
    int n = ni();
    for (int i = 0; i < n; i++) {
        string s = "";
        int ch = getchar();
        while (!ia(ch)) ch = getchar();
        while (ia(ch)) {
            s += (char) ch;
            ch = getchar();
        }
        a[i] = ni();
        b[i] = ni();
        c[i] = ni();
        names[i] = s;
    }
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++) {            
            ed[i][j] = (a[i] > a[j]) + (b[i] > b[j]) + (c[i] > c[j]) > 1;
        }
    for (int i = 0; i < n; i++) {
        int head = 0;
        for (int j = 0; j < n; j++) was[j] = 0;
        q[head++] = i;
        was[i] = 1;
        int cn = 0;
        while (head > 0) {
            int v = q[--head];
            ++cn;
            for (int j = 0; j < n; j++) {
                if (was[j] || !ed[v][j]) continue;
                was[j] = 1;
                q[head++] = j;
            }
        }
        if (cn == n) {
            printf("%s\n", names[i].c_str());
        }
    }
}