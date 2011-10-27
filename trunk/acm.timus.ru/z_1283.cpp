#include <iostream>
#include <stdio.h>
#include <vector>
#include <cmath>
#define pb push_back
#define sz size()
#define vi vector <int>

using namespace std;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	int a,b,c;
	cin >> a >> b >> c;
	int q=0;
	for (;b<a;q++) {
		a-=c*0.01*a;
	}
	cout << q;
	return 0;
}
