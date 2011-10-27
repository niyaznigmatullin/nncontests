#include <iostream>
#include <stdio.h>
using namespace std;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	int n,x,q=0;
	cin >> n;
	for (int i=0; i<n; i++)
	for (int j=0; j<n; j++) {
		cin >> x;
		q+=x;
	}
	cout << q/2;
	return 0;
}
