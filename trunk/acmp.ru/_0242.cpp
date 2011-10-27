#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;

int n,k,_1,_2,w=-1,e=-1,x,c[300][300],r[300][300];

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> k;
	for (int i=0; i<n; i++)
	for (int j=0; j<n; j++) {
		cin >> x;
		c[j][x]++;
		r[i][x]++;
	}
	for (int i=1; i<=k; i++) {
		_2=0;
		_1=0;
		for (int j=0; j<n; j++) {
			if (c[j][i]>1) _2++;
			if (c[j][i]>0) _1++;
		}
		if (_2==n) {
			cout << n << " " << i;
			return 0;
		}
		if (_2>=2 && _1==n) {
			w=i;
		}
		if (_2>0 && _1>1) e=i;
		_2=0;
		_1=0;
		for (int j=0; j<n; j++) {
			if (r[j][i]>1) _2++;
			if (r[j][i]>0) _1++;
		}
		if (_2==n) {
			cout << n << " " << i;
			return 0;
		}
		if (_2>=2 && _1==n) {
			w=i;
		}
		if (_2>0 && _1>1) e=i;
	}
	if (w!=-1) cout << n+1 << " " << w; else cout << n+2 << " " << e;
	return 0;
}
