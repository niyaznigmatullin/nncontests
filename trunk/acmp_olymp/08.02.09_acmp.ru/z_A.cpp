#include <iostream>
#include <stdio.h>
using namespace std;

int rec(int x, int k) {
	if (x==1) {
		return 0;
	}
	if (k%2==1) return 2*rec(x/2,k+1)+1; else {
		if (x%2==0) return 2*rec(x/2,k+1); else return 2*rec(x/2+1,k+1);
	}
}

int n,m;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	cout << n+rec(m-n+1,1);
	return 0;
}
