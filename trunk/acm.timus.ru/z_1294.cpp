#include <iostream>
#include <math.h>

using namespace std;

double a, b, c, d, ca;
int main() {
	cin >> a >> b >> c >> d;	
	if (fabs(c * d - a * b) <= 1e-10) {
		cout << "Impossible.";
		return 0;
	}
	a *= 1000;
	b *= 1000;
	c *= 1000;
	d *= 1000;
	ca = (c * c + d * d - a * a - b * b) / (2 * (c * d - a * b));
	printf("Distance is %d km.\n", (int)(sqrt(a * a + b * b - 2 * a * b * ca) + .5));
	return 0;
}
