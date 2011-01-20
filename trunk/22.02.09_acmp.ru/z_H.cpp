#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

bool is_palin(string s) {
    bool ret=true;
    for (int i=0; i<(int)s.size()/2; i++) ret&=(s[i]==s[s.size()-i-1]);
    return ret;
}
string s;
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> s;
    bool ok=true;
    for (int i=0; i<(int)s.size(); i++) ok&=(s[i]==s[0]);
    if (ok) {
        cout << "NO SOLUTION";
        return 0;
    }
    if (is_palin(s)) cout << s.substr(0,s.size()-1); else cout << s;
    return 0;
}
