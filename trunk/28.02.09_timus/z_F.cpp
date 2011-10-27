#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
using namespace std;
vector <int> w;
string s;
void rec(int l, int r) {
    if (l==r) {
        w.push_back(l);
        return;
    }
    int m=(l+r)/2;
    w.push_back(m);
    if (l!=m) rec(l,m-1);
    if (m!=r) rec(m+1,r);
}

int main() {
    getline(cin,s);
    int n=s.size();
    rec(1,n);
    string ans=s;
    for (int i=0; i<(int)w.size(); i++) {
        ans[w[i]-1]=s[i];
    }
    cout << ans;
	return 0;
}
