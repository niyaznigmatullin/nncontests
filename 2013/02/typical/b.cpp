#include <iostream>
#include <cstdio>
#include <cmath>

using namespace std;

const int PLUS = ~1;
const int MINUS = ~2;
const int MUL = ~3;
const int DIV = ~4;
const int POW = ~5;
const int VAR = ~6;
const int NEG = ~7;

const int N = 1111111;
long long type[N];
int lef[N], righ[N];
string s;
int len, fr;
int cur;

int pP();
int pC();
int pF();
int pV();
int pZ();

int pE() {
    return pP();
}

int pP() {
    int d = pF();
    while (cur < len && (s[cur] == '+' || s[cur] == '-')) {
        int z = fr++;
        type[z] = s[cur] == '+' ? PLUS : MINUS;
        ++cur;
        lef[z] = d;
        righ[z] = pF();
        d = z;
    }
    return d;
}

int pF() {
    int d = pC();
    while (cur < len && s[cur] == '*') {
        int z = fr++;
        type[z] = MUL;
        ++cur;
        lef[z] = d;
        righ[z] = pC();
        d = z;
    }
    return d;
}

int pC() {
    int d = pZ();
    if (cur < len && s[cur] == '^') {
        int z = fr++;
        type[z] = POW;
        lef[z] = d;
        ++cur;
        righ[z] = pZ();
        d = z;
    }
    return d;
}

int pZ() {
    int neg = 0;
    while (cur < len && s[cur] == '-') {
        neg = !neg;
        ++cur;
    }
    int d = pV();
    if (neg) {
        int z = fr++;
        lef[z] = d;
        d = z;
        type[z] = NEG;
    }
    return d;
}

int pV() {
    if (s[cur] == '(') {
        ++cur;
        int d = pE();
        ++cur;
        return d;
    }
    if (s[cur] >= 'a' && s[cur] <= 'z') {
        int z = fr++;
        type[z] = VAR;
        ++cur;
        return z;
    }
    long long num = 0;
    while (cur < len && s[cur] >= '0' && s[cur] <= '9') {
        num = num * 10 + s[cur] - '0';
        ++cur;
    }
    int d = fr++;
    type[d] = num;
    if (cur < len && s[cur] >= 'a' && s[cur] <= 'z') {
        int z = fr++;
        type[z] = MUL;
        lef[z] = d;
        int z2 = fr++;
        type[z2] = VAR;
        righ[z] = z2;
        d = z;
    }
    return d;
}

double calc(int v, double val) {
    if (type[v] == PLUS) return calc(lef[v], val) + calc(righ[v], val);
    if (type[v] == MINUS) return calc(lef[v], val) - calc(righ[v], val);
    if (type[v] == MUL) return calc(lef[v], val) * calc(righ[v], val);
    if (type[v] == POW) return pow(calc(lef[v], val), calc(righ[v], val));
    if (type[v] == NEG) return -calc(lef[v], val);
    if (type[v] == VAR) return val;
    return type[v];
}

int main() {
    cin >> s;
    int pos = 0;
    while (s[pos] != '=') ++pos;
    string s1 = s.substr(0, pos);
    string s2 = s.substr(pos + 1, s.length() - pos - 1);
    fr = 0;
    cur = 0;
    s = s1;
    len = s.length();
    int r1 = pE();
    cur = 0;
    s = s2;
    len = s.length();
    int r2 = pE();
    int ok = 1;
    int ans = 0;
    for (int i = -10000; i < 10000; i++) {
        double dif = calc(r1, i) - calc(r2, i);
        if (dif < 0) dif = -dif;
        if (dif < 1e-9) {
            ++ans;
        } else {
            ok = 0;
        }
    }
    if (ok) {
        printf("inf\n");
    } else {
        printf("%d\n", ans);
    }
} 