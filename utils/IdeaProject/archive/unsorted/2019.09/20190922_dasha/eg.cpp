#include "testlib.h"
#include <iostream>
using namespace std;


int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	cout << 200000 << '\n';
	vector<int> p(5);
	for (int i = 0; i < 5; i++) p[i] = i + 1;
	for (int i = 0; i < 200000; i++) {
		shuffle(p.begin(), p.end());
		for (int i = 0; i < 5; i++) {
			if (i > 0 )cout << ' ';
			cout << p[i];
		}
		cout << '\n';
	}
}