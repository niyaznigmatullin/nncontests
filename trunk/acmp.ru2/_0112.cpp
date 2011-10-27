#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int n,a[10001],b[10001],q;

void msort(int l, int r) {
	if (l==r) return;
	int m=(l+r)/2;
	msort(l,m);
	msort(m+1,r);
	int i=l,j=m+1,k=0;
	for (;i<=m && j<=r;k++) {
		if (a[i]>a[j]) {
			q+=m-i+1;
			b[k]=a[j];
			j++;
		} else {
			b[k]=a[i];
			i++;
		}
	}
	for (;i<=m;k++) {
		b[k]=a[i];
		i++;
	}
	for (;j<=r;k++) {
		b[k]=a[j];
		j++;
	}
	for (int e=l; e<=r; e++) {
		a[e]=b[e-l];
	}
}

int main() {
	int k;
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> k;
	q=0;
	for (int i=0; i<k; i++) {
		for (int j=0; j<n; j++) {
			cin >> a[j];
		}
		msort(0,n-1);
	}
	cout << q;
	return 0;
}
