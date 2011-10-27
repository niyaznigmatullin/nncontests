#include <iostream>
#include <stdio.h>
using namespace std;
int a[10002],n,m,x;

bool found(int x) {
	int l=0,r=n-1,mid;
	while (l<=r) {
		mid=(l+r)>>1;
		if (a[mid]==x) return true; else
		if (a[mid]>x) r=mid-1; else l=mid+1;
	}
	return false;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	for (int i=0; i<n; i++) cin >> a[i];
	for (int i=0; i<m; i++) {
		cin >> x;
		if (found(x)) printf("YES\n"); else printf("NO\n");
	}
	return 0;
}
