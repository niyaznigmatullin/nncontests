#include <cstdio>



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

const int OPEN = ~1;
const int CLOSE = ~2;
const int AND = ~3;
const int OR = ~4;
const int NOT = ~5;
const int TRUE = ~6;
const int FALSE = ~7;
const int dx[] = {1, 0, -1, 0};
const int dy[] = {0, 1, 0, -1};
const int D = 111;
int ex[255], len, cur, fr;
int left[255], right[255], type[255];
int val[30];
int inv[D + D][D + D];
int a[D + D][D + D];

int pO();
int pA();
int pN();
int pV();

int pO() {
    int d = pA();
    while (cur < len && ex[cur] == OR) {
        int z = fr++;
        type[z] = OR;
        left[z] = d;
        ++cur;
        right[z] = pA();
        d = z;
    }
    return d;
}

int pA() {
    int d = pN();
    while (cur < len && ex[cur] == AND) {
        int z = fr++;
        type[z] = AND;
        left[z] = d;
        ++cur;
        right[z] = pN();
        d = z;
    }
    return d;
}

int pN() {
    int f = 0;
    while (cur < len && ex[cur] == NOT) {
        f = !f;
        ++cur;
    }
    int d = pV();
    if (f) {
        int z = fr++;
        type[z] = NOT;
        left[z] = d;
        d = z;
    }
    return d;
}

int pV() {
    if (ex[cur] == OPEN) {
        ++cur;
        int d = pO();
        ++cur;
        return d;
    }
    int d = fr++;
    type[d] = ex[cur];
    ++cur;
    return d;
}

int calc(int v) {
    if (type[v] == AND) return calc(left[v]) && calc(right[v]); else
    if (type[v] == OR) return calc(left[v]) || calc(right[v]); else
    if (type[v] == NOT) return !calc(left[v]); else
    if (type[v] == TRUE) return 1; else
    if (type[v] == FALSE) return 0; else
    return val[type[v]];
}


int main() {
    fr = 0;
    int c = getchar();
    len = 0;
    while (c != 10 && c != 13) {
        while (c == ' ') c = getchar();
        if (c == '(') {
            ex[len++] = OPEN; 
            c = getchar();
        } else if (c == ')') {
            ex[len++] = CLOSE;
            c = getchar();
        } else {
            int cn = 0;
            int ch = c;
            while (ch >= 'A' && ch <= 'Z') {
                ++cn;
                ch = getchar();
            }
            if (cn > 1) {
                if (c == 'N') ex[len++] = NOT; else
                if (c == 'A') ex[len++] = AND; else
                if (c == 'T') ex[len++] = TRUE; else
                if (c == 'F') ex[len++] = FALSE; else
                if (c == 'O') ex[len++] = OR; 
            } else {
                ex[len++] = c - 'A';
            }
            c = ch;
        }
    }
    cur = 0;
    int root = pO();
    int n = ni();
    int m = ni();
    int k = ni();
    for (int i = -n; i <= n; i++) {
        for (int j = -n; j <= n; j++) {
            inv[i + D][j + D] = -1;
            a[i + D][j + D] = 0;
        }
    }
    for (int i = 0; i < m; i++) {
        int x = ni();
        int y = ni();
        a[x + D][y + D] = 1;
    }
    for (int i = 0; i < k; i++) {
        int x = ni();
        int y = ni();
        int c = getchar();
        while (c < 'A' || c > 'Z') c = getchar();
        inv[x + D][y + D] = c - 'A';
    }
    int x = 0;
    int y = 0;
    for (int i = 0; i < 26; i++) val[i] = 0;
    int tt = calc(root);
    int d = 0;
    while (true) {
        printf("%d %d\n", x, y);
        x += dx[d];
        y += dy[d];
        if (!(x >= -n && x <= n && y >= -n && y <= n)) {
            break;
        }
        if (inv[x + D][y + D] >= 0) {
            val[inv[x + D][y + D]] ^= 1;
            tt = calc(root);
        }
        if (a[x + D][y + D]) {
            if (tt) {
                d = (d - 1) & 3;
            } else {
                d = (d + 1) & 3;
            }
        }
    }
}