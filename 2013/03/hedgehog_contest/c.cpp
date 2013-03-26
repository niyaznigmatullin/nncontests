#include <cstdio>

int a[3][3];
int w[27 * 27 * 27];

int win(int move) {
    for (int i = 0; i < 3; i++) if (a[i][0] == move && a[i][1] == move && a[i][2] == move) return 1;
    for (int i = 0; i < 3; i++) if (a[0][i] == move && a[1][i] == move && a[2][i] == move) return 1;
    if (a[0][0] == move && a[1][1] == move && a[2][2] == move) return 1;
    if (a[0][2] == move && a[1][1] == move && a[2][0] == move) return 1;
    return 0;
}

int who() {
    int c1 = 0;
    int c2 = 0;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (a[i][j] == 1) ++c1;
            if (a[i][j] == 2) ++c2;
        }
    }
    if (c2 > c1 || c2 < c1 - 1) return 0;
    int move = c1 != c2;
    ++move;
    return move;
}

int valid() {
    int move = who();
    if (!move) return 0;
    if (win(move)) return 0;
    if (win(3 - move)) return -1;
    return 1;
}

int draw() {
    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            if (!a[i][j]) return 0;
    return 1;
}

int main() {
    int all = 27 * 27 * 27;
    for (int mask = all - 1; mask >= 0; mask--) {
        int x = mask;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = x % 3;
                x /= 3;
            }
        }
        int v = valid();
        if (v == 0) continue;
        w[mask] = -1;
        if (v >= 0) {
            int move = who();
            int cn = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {   
                    if (a[i][j]) continue;
                    a[i][j] = move;
                    int nex = 0;
                    for (int i0 = 0; i0 < 3; i0++) {
                        for (int j0 = 0; j0 < 3; j0++) {
                            nex = nex * 3 + a[2 - i0][2 - j0];
                        }
                    }
                    ++cn;
                    if (-w[nex] > w[mask]) w[mask] = -w[nex];
                    a[i][j] = 0;
                }
            }
            if (!cn) w[mask] = 0;
        }
    }
    while (true) {
        int c = getchar();
        while (c <= 32) c = getchar();
        if (c == 'Q') break;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                while (c <= 32) c = getchar();
                a[i][j] = c == 'X' ? 1 : c == '.' ? 0 : 2;
                c = getchar();
            }
        }
        int code = 0;
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                code = code * 3 + a[i][j];
            }
        }
        int v = valid();
        int move = who();
        if (!v) puts("Illegal position."); else {
            if (w[code] == 0) puts("Game is a draw."); else {
                if (w[code] < 0) move = 3 - move;
                if (move == 1) puts("X wins."); else puts("0 wins.");
            }
        }
    }
}