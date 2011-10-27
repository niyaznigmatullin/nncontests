#include <iostream>
#include <stdio.h>
#include <string>
#include <set>
using namespace std;
int n,a[256],ans;
string s;
char c;
int main() {
    freopen("printer.in","r",stdin);
    freopen("printer.out","w",stdout);
    cin >> n;
    int x,y;
    for (int i=0; i<n; i++) {
        cin >> c >> x >> y;
        a[(int)c]=x;
        a[(int)c-'a'+'A']=y;
    }
    cin >> s;
    for (int i=0; i<(int)s.size(); i++) {
        ans+=a[(int)s[i]];
    }
    cout << ans;
	return 0;
}
