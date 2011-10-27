#include <iostream>
#include <stdio.h>
using namespace std;
int n,a[100000],b[100000];
int main() {
	a[0]=0;
	a[1]=1;
	for (int i=1; i<100000; i++) {
	    if (i%2==0) {
	        a[i]=a[i/2];
	    } else {
	        a[i]=a[i/2]+a[i/2+1];
	    }
	}
	b[0]=0;
	for (int i=1; i<100000; i++) {
	    b[i]=max(b[i-1],a[i]);
	}
	for (cin >> n; n!=0; cin >> n) {
	    cout << b[n] << "\n";
	}
	return 0;
}
