#include <iostream>
#include <math.h>
using namespace std;

double p, q, eps = 1e-10;
int main() {
    cin >> p >> q;
	for (int i=1; ;i++) {
		if ( (int)floor(.01*i*p+eps) < (int)floor(.01*i*q-eps)) {
			cout << i;
			break;
		}
	}	
    return 0;
}
