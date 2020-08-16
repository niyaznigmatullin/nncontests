#include "testlib.h"
#include <iostream>
using namespace std;

int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	int n = 100000;
	cout << n << '\n';
	for (int i = 0; i < n; i++) {
		int x = rnd.next(-100000, 100000);
		int y = rnd.next(-100000, 100000);
		cout << x << ' ' << y << '\n';
	}
}