#include <iostream>
#include <iomanip>

using namespace std;

int fr;
int len[234234], sl[234234], ln[10][234234];

int newNode(int l) {
    len[fr] = l;
    for (int i = 0; i < 4; i++) ln[i][fr] = -1;
    sl[fr] = -1;
    return fr++;   
}

int copyNode(int v, int l) {
    len[fr] = l;
    for (int i = 0; i < 4; i++) ln[i][fr] = ln[i][v];
    sl[fr] = sl[v];
    return fr++;
}

int append(int v, int c) {
    int u = newNode(len[v] + 1);
    while (v >= 0 && ln[c][v] < 0) {
        ln[c][v] = u;
        v = sl[v];
    }
    if (v < 0) {
        sl[u] = 0;
        return u;
    }
    int q = ln[c][v];
    if (len[q] == len[v] + 1) {
        sl[u] = q;
        return u;
    }
    int cp = copyNode(q, len[v] + 1);
    while (v >= 0 && ln[c][v] == q) {
        ln[c][v] = cp;
        v = sl[v];
    }
    sl[u] = sl[q] = cp;
    return u;
}

int main() {
    string s;
    cin >> s;
    fr = 0;
    long long nsub = 0;
    int v = newNode(0);
    string nucleo = "ATGC";
    for (int i = 0; i < (int) s.length(); i++) {
        v = append(v, nucleo.find(s[i]));
        nsub += len[v] - len[sl[v]];
    }
    long long z = 1;
    long long maxsum = 0;
    for (int i = 1; i <= (int) s.length(); i++) {
        z *= 4;
        if (z > 123456789) z = 123456789;
        long long d = z;
        if (d > s.length() - i + 1) d = s.length() - i + 1;
        maxsum += d;
    }
    cout << fixed << setprecision(10) << 1. * nsub / maxsum << "\n";
}