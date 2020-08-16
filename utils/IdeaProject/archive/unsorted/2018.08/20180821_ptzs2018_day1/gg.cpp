#include "testlib.h"
#include <iostream>

using namespace std;

bool isPrime(int x) {
	for (int i = 2; i < x; i++) if (x % i == 0) return false;
		return true;
}
int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	int P = rnd.next(999999000);
	while (!isPrime(P)) P++;
	int n = rnd.next(100000, 100000);
	int q = 100000;
	cout << P << ' ' << n << " " << q << endl;
	for (int i = 0; i < n; i++) {
		if (i > 0) cout << ' ';
		cout << rnd.next(1, P - 1);
	}
	cout << endl;
	for (int i = 0; i < q; i++) {
		int type = rnd.next(1, 2);
		int left = rnd.next(1, n);
		int right = rnd.next(left, n);
		cout << type << ' ' << left << ' ' << right;
		if (type == 1) {
			cout << " " << rnd.next(1, P - 1);
		}
		cout << endl;
	}
}