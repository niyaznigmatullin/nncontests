#include <iostream>
#include <stdio.h>
#include <string>
using namespace std;
string s;
int v;
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> s >> v;
    int ans=0;
    for (int i=0; i<(int)s.size(); i++) {
        ans=(ans*10+s[i]-'0')%v;
    }
    cout << ans;
    return 0;
}
