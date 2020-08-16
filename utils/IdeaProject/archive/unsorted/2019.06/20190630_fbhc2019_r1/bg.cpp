#include "testlib.h"
#include <iostream>

using namespace std;

int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	int t = 1000000;
	cout << t << '\n';
	for (int i = 0; i < t; i++) {
		int n = rnd.next(1, 23);
		int k = rnd.next(0, n - 1);
		cout << n << ' ' << k << '\n';
		cout << rnd.next(format("[AB]{%d}", n)) << '\n';
	}
}
