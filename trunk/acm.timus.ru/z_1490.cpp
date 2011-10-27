#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <math.h>
#define sz size()
using namespace std;
int main() {
//	freopen("input.txt","r",stdin);
//	freopen("output.txt","w",stdout);
	int n;
	cin >> n;
	long long q=0;
	for (int i=0; i<n; i++) {
		int f=ceil(sqrt(1.*n*n-1.*i*i));
		q+=f;
	}
	cout << q*4;
	return 0;
}
