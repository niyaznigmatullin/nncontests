#include <iostream>
#include <stdio.h>
using namespace std;
int n,q=0;

int main() {
//	freopen("input.txt","r",stdin);
//	freopen("output.txt","w",stdout);
	cin >> n;
	while (n>1) {
		n=(n+1)>>1;
		q++;
	}
	cout << q;
	return 0;
}
