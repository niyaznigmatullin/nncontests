#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;

int n,m,p[200],q[200],r[200],f[200];
long long a[1000000];

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	for (int i=0; i<n; i++) {
		cin >> p[i] >> r[i] >> q[i] >> f[i];
	}
	if (m==0) {
		cout << 0;
		return 0;
	}
	memset(a,0,sizeof(a));
	a[0]=1;
	long long ans=((long long)1)<<50;
	for (int k=0; k<n; k++)
	for (int i=m-1; i>=0; i--) if (a[i] && ans>a[i])
	for (int j=1; j<=f[k]; j++) {
			if (j>=r[k]) {
				if (a[i+j]==0 || a[i+j]>a[i]+q[k]*j) a[i+j]=a[i]+q[k]*j;
			} else {
				if (a[i+j]==0 || a[i+j]>a[i]+p[k]*j) a[i+j]=a[i]+p[k]*j;
			}
			if (i+j>=m) ans=min(a[i+j],ans);
	}
	if (ans>1000000000) cout << -1; else
	cout << ans-1;
	return 0;
}
