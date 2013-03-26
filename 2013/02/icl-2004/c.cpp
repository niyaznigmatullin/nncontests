#include <iostream>
#include <cassert>
#include <cstdio>

using std::cin;
using std::string;

const int NOT = 1;
const int OR = 2;
const int AND = 3;
const int TRUE = 4;
const int FALSE = 5;

int ans[5];
int idv[26];
int v[26];
int cntv;
int val[26];

string s;
int len;
int cur;
int fr;

int left[555], right[555], type[555];

int pV();
int pE();
int pO();
int pA();
int pN();

int pE() {  
    return pO();
}

int pO() {
    int d = pA();
    while (cur < len && s[cur] == '|') {
        ++cur;
        int e = pA();
        left[fr] = d;
        right[fr] = e;
        type[fr] = OR;
        d = fr++;
    }
    return d;
}

int pA() {  
    int d = pN();
    while (cur < len && s[cur] == '&') {
        ++cur;
        int e = pN();
        left[fr] = d;
        right[fr] = e;
        type[fr] = AND;
        d = fr++; 
    }
    return d;
}

int pN() {
    int ew = 0;
    while (s[cur] == '~') {
        ++cur;
        ew = !ew;
    }
    int d = pV();
    if (ew) {
        left[fr] = d; 
        type[fr] = NOT;
        d = fr++;
    }
    return d;
}

int pV() {  
    if (s[cur] == 'T' || s[cur] == 'F' || (s[cur] >= 'a' && s[cur] <= 'z')) {
        type[fr] = s[cur] == 'T' ? TRUE : s[cur] == 'F' ? FALSE : ~(s[cur] - 'a');
        if (s[cur] >= 'a' && s[cur] <= 'z') {
            if (idv[s[cur] - 'a'] < 0) {
                v[cntv] = s[cur] - 'a';
                idv[s[cur] - 'a'] = cntv; 
                ++cntv;
            }
        }
        ++cur;
        return fr++;
    }
    if (s[cur] == '(') {
        ++cur;
        int d = pE();
        ++cur;
        return d;
    }
    assert(false);
}

int calc(int v) {
//    printf("%d %d %d %d %d\n", v, type[v], ~type[v], left[v], right[v]);
    if (type[v] == TRUE) return 1;
    if (type[v] == FALSE) return 0;
    if (type[v] == NOT) return !calc(left[v]);
    if (type[v] == AND) return calc(left[v]) && calc(right[v]);
    if (type[v] == OR) return calc(left[v]) || calc(right[v]);
    if (type[v] < 0) {
        return val[~type[v]];
    }
    assert(false);
}

int solve() {    
    cin >> s;
    int pos = 0;
    while (s[pos] != '=') ++pos;
    string s1 = s.substr(0, pos);
    string s2 = s.substr(pos + 1, s.length() - pos - 1);
    for (int i = 0; i < 26; i++) v[i] = idv[i] = -1;
    cntv = 0;
    s = s1;
    len = s.length();
    cur = 0;
    int r1 = pE();
    s = s2;
    len = s.length();
    cur = 0;
    int r2 = pE();
    int ok = 1;
    for (int i = 0; i < 1 << cntv; i++) {
        for (int j = 0; j < cntv; j++) {
            val[v[j]] = (i >> j) & 1;
        } 
        if (calc(r1) != calc(r2)) {
            ok = 0;
            break; 
        }
    }
    return ok;
}

int main() {
    int cnt = 0;
    for (int i = 0; i < 5; i++) {
        ans[i] = solve(); 
        cnt += ans[i];
    }
    if (cnt < 2) cnt = 2;
    for (int i = 0; i < 5; i++) printf(ans[i] ? "+" : "-");
    printf("\n");
    printf("%d\n", cnt);
}