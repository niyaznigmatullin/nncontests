#include "testlib.h"
#include <iostream>

using namespace std;

int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	int n = atoi(argv[1]);
	int k = atoi(argv[2]);
	cout << n << ' ' << k << endl;
	for (int i = 0; i < n; i++) {
		int x = rnd.next(1, k);
		if (rnd.next(1, 2) == 1) {
			x = -x;
		}
		if (i > 0) cout << ' ';
		cout << x;
	}
	cout << '\n';
	int q = atoi(argv[3]);
	cout << q << '\n';
	for (int i = 0; i < q; i++) {
		int op = rnd.next(1, 2);
		if (false && op == 1) {
			int pos = rnd.next(1, n);
			int x = rnd.next(1, k);
			if (rnd.next(1, 2) == 1) x = -x;
			cout << 1 << ' ' << pos << ' ' << x << '\n';
		} else {
			int left = rnd.next(1, n);
			int right = rnd.next(left, n);
			cout << 2 << ' ' << left << ' ' << right << '\n';
		}
	}
}