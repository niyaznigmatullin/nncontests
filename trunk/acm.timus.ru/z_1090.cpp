#include <iostream>
#include <stdio.h>
#include <string>

using namespace std;
int maxq=-1,maxi=0,a[10010],b[10010],n,k,q;

void mergesort(int l, int r) {
    if (l==r) return;
    int m=(l+r)/2;
    mergesort(l,m);
    mergesort(m+1,r);
    int i=l,j=m+1,k=0;
    for (;i<=m && j<=r;k++) {
        if (a[i]>a[j]) {
            b[k]=a[j++];
            q+=m-i+1;
        } else b[k]=a[i++];
    }
    for (;i<=m; k++) b[k]=a[i++];
    for (;j<=r; k++) b[k]=a[j++];
    for (int t=0; t<r-l+1; t++) a[t+l]=b[t];
}

int main() {
	cin >> n >> k;
	for (int i=0; i<k; i++) {
	    for (int j=0; j<n; j++) cin >> a[j];
	    q=0;
	    mergesort(0,n-1);
	    if (q>maxq) {
	        maxq=q;
	        maxi=i+1;
	    }
	}
	cout << maxi;
	return 0;
}
