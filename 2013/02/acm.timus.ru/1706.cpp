#include <iostream>
#include <string>

using namespace std;

int sl[2222], len[2222], lk[26][2222], fr;

int newNode(int l) {
    len[fr] = l;
    for (int i = 0; i < 26; i++) lk[i][fr] = -1;
    sl[fr] = -1;
    return fr++;
}

int copyNode(int v, int l) {
    len[fr] = l;
    sl[fr] = sl[v];
    for (int i = 0; i < 26; i++) lk[i][fr] = lk[i][v];
    return fr++;
}

int append(int v, int c) {
    int n = newNode(len[v] + 1);
    while (v >= 0 && lk[c][v] < 0) {
        lk[c][v] = n;
        v = sl[v];
    }
    if (v < 0) {
        sl[n] = 0;
        return n;
    }
    int q = lk[c][v];
    if (len[q] == len[v] + 1) {
        sl[n] = q;
        return n;
    }
    int cp = copyNode(q, len[v] + 1);
    while (v >= 0 && lk[c][v] == q) {
        lk[c][v] = cp;
        v = sl[v];
    }
    sl[q] = sl[n] = cp;
    return n;
}

int main() {
    int k;
    cin >> k;
    string s;
    cin >> s;
    int n = s.length();
    for (int i = 0; i < n; i++) {
        fr = 0;
        int v = newNode(0);
        int ans = 0;
        for (int j = 0, id = i; j < k; j++) {
            v = append(v, s[id] - 'a');
            ans += len[v] - len[sl[v]];
            ++id;
            if (id == n) id = 0;
        }
        if (i > 0) cout << ' ';
        cout << ans;
    }
    cout << "\n";
}