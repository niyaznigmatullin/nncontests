#include "testlib.h"
#include <iostream>


using namespace std;

int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	int max = atoi(argv[2]);
	int n = atoi(argv[1]);
	vector<int> d(max + 1);
	for (int i = 1; i <= max; i++) {
		for (int j = i; j <= max; j += i) d[j]++;
	}
	vector<int> s;
	for (int i = 1; i <= max; i++) if (d[i] <= 7) s.push_back(i);
	vector<int> a;
	for (int i = 0; i < n; i++) {
		while (true) {
			int j = rnd.next(0, (int) s.size() - 1);
			if (s[j] < 0) continue;
			a.push_back(s[j]);
			s[j] = -1;
			break;
		}
	}
	cout << a.size() << '\n';
	for (int i : a) cout << i << ' ';
		cout << '\n';
}