#include <iostream>
#include <stdio.h>
#include <string>
using namespace std;
long long a[100][2],k;
int n;

int main() {
    cin >> n >> k;
	a[1][0]=1;
	a[1][1]=1;
	for (int i=2; i<=n; i++) {
	    a[i][0]=a[i-1][1]+a[i-1][0];
	    a[i][1]=a[i-1][0];
	}
	if (a[n][0]+a[n][1]<k) {
	    cout << -1;
	    return 0;
	}
	int i=n+1,j;
	string s="";
	while (i-->1) {
	    if (a[i][0]<k) {
	        k-=a[i][0];
	        j=1;
	    } else j=0;
	    s+=(char)(j+'0');
	}
	cout << s;
	return 0;
}
