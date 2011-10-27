#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;
int q=0,m,n,a[200],w[200],e[200];
bool u[200];
void rec(int x, int k, int r, int last) {
	if (m-x-1+k<=q) return;
	if (x==m) {
		if (q<k) q=k;
		return;
	}
	if (r<a[x])	for (int i=0; i<n; i++) if (a[x]-w[i]+1>last) rec(x+1,k+1,a[x]+e[i]-1,a[x]);
	rec(x+1,k,r,last);
}
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	for (int i=0; i<n; i++) cin >> w[i] >> e[i];
	cin >> m;
	for (int i=0; i<m; i++) cin >> a[i];
	memset(u,false,sizeof(u));
	rec(0,0,-100000,-100000);
	cout << q;
	return 0;
}
