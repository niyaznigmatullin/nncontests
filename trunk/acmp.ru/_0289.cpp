#include <iostream>
#include <stdio.h>
using namespace std;

long long lim=(long long)10000000*100000000+1,q=lim;
int n,a[50];
int p[]={2,3,5,7,11,13,17,19,23,29,31,37,41,43,47};

void rec(int x, int kol) {
	if (x==1) {
		long long w=1;
		for (int i=0; i<kol; i++)
		for (int j=0; j<a[i]-1; j++) {
			w*=p[i];
			if (w>lim) {
				return;
			}
		}
		if (q>w) q=w;
	}
	int i=x;
	while (i>1) {
		if (x%i==0) {
			a[kol]=i;
			rec(x/i,kol+1);
		}
		i--;
	}
}
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	rec(n,0);
	if (q==lim) cout << 0; else cout << q;
	return 0;
}
