/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>
#include "testlib.h"

using namespace std;

int main(int, char **argv) {
	int T = 1000000;
	cout << T << '\n';
	int C = atoi(argv[1]);
	for (int i = 0; i < T; i++) {
		int x = rnd.next(1, C);
		int y = rnd.next(1, C);
		cout << x << ' ' << y << '\n';
	}
}