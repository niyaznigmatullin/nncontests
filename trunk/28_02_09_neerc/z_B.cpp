#include <iostream>
#include <stdio.h>
#include <string>
using namespace std;
int n,q;
string a[100],b[100],c[100],d[100];

int main() {
    freopen("set.in","r",stdin);
    freopen("set.out","w",stdout);
    cin >> n;
    for (int i=0; i<n; i++) {
        cin >> a[i] >> b[i] >> c[i] >> d[i];
        int p=d[i].size()-1;
        if (d[i][p]=='s')
            d[i].resize(p);
    }
    for (int i=0; i<n-2; i++)
    for (int j=i+1; j<n-1; j++)
    for (int k=j+1; k<n; k++) if (((a[i]==a[j] && a[j]==a[k] && a[i]==a[k]) || (a[i]!=a[j] && a[j]!=a[k] && a[i]!=a[k])) && ((b[i]==b[j] && b[j]==b[k] && b[i]==b[k]) || (b[i]!=b[j] && b[j]!=b[k] && b[i]!=b[k])) && ((c[i]==c[j] && c[j]==c[k] && c[i]==c[k]) || (c[i]!=c[j] && c[j]!=c[k] && c[i]!=c[k])) && ((d[i]==d[j] && d[j]==d[k] && d[i]==d[k]) || (d[i]!=d[j] && d[j]!=d[k] && d[i]!=d[k]))) {
        q++;
    }
    cout << q;
	return 0;
}
