#include <iostream>
#include <stdio.h>
using namespace std;

int gcd(int x, int y) {
	for (;x*y>0;) if (x>y) x%=y; else y%=x;
	return x+y;
}

int main() {
	int n,m;
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	int g=gcd(n,m);
	int f1=1,f2=1,f;
	for (int i=2; i<g; i++) {
		f=(f1+f2)%1000000000;
		f1=f2;
		f2=f;
	}
	cout << f2;
	return 0;
}
