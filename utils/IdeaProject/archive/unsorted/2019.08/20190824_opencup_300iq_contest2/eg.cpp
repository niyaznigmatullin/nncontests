#include "testlib.h"

#include <iostream>

using namespace std;

int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	cout << "64 200000\n";
	for (int i = 0; i < 200000; i++) {
		int v = rnd.next(1, 64);
		int u = rnd.next(1, 64);
		while (v == u) {
			v = rnd.next(1, 64);
			u = rnd.next(1, 64);
		}
		long long a = rnd.next(1LL, (1LL << 60) - 1);
		int w = i + 1;
		cout << v << ' ' << u << ' ' << a << ' ' << w << '\n';
	}
}