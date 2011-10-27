#include <iostream>
#include <stdio.h>
using namespace std;
int n,m;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	for (int i=1; i<m; i++) {
		int l=0,r=i-1,mid;
		while (l<=r) {
			mid=(l+r)/2;
			long long f1=i,f2=mid,f;
			for (int j=2; j<n; j++) {
				f=f1+f2;
				f1=f2;
				f2=f;
			}
			if (f2==m) {
				cout << i << " " << mid;
				return 0;
			} else if (f2>m) r=mid-1; else l=mid+1;
		}
	}
	return 0;
}
