#include <iostream>
#include <string>
#include <vector>
#include <stdio.h>
using namespace std;

int n;
vector <int> ans;
bool is_palin(string s) {
    int r=s.size();
    for (int i=0; i<r/2; i++) if (s[i]!=s[r-i-1]) return false;
    return true;
}

string to_rad(int x, int k) {
    if (x==0) return "0";
    string ret="";
    int p;
    for (;x>0;x/=k) {
        p=x%k;
        if (p>=0 && p<=9) ret=(char)(p+'0')+ret; else ret=(char)(p+'a'-10)+ret;
    }
    return ret;
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n;
    for (int i=2; i<=36; i++) if (is_palin(to_rad(n,i))) {
        ans.push_back(i);
    }
    if (ans.empty()) {
        cout << "none\n";
    } else if (ans.size()==1) {
        cout << "unique\n";
        cout << ans.back();
    } else {
        cout << "multiple\n";
        for (int i=0; i<(int)ans.size(); i++) cout << ans[i] << " ";
    }
    return 0;
}
