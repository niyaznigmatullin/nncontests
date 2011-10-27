#include <iostream>
#include <stdio.h>
using namespace std;
long long n,m;

int main() {
    cin >> n >> m;
    long long x,q=0;
	for (int i=0; i<m; i++) {
	    cin >> x;
	    q+=x/3;
	    if (q>=n) {
	        printf("Free after %d times.",i+1);
	        return 0;
	    }
	}
	cout << "Team.GOV!";
	return 0;
}
