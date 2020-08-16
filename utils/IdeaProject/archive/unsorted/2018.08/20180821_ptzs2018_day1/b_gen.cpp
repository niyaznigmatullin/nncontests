#include "testlib.h"
#include <iostream>

using namespace std;

#define forn(i, n) for (int i = 0; i < (int)(n); i++)


bool isPrime(int x) {
	for (int i = 2; i < x; i++) if (x % i == 0) return false;
		return true;
}
int main(int argc, char **argv) {
	registerGen(argc, argv, 1);
	int n = atoi(argv[1]), c = atoi(argv[2]), k = atoi(argv[3]);
	printf("%d\n", n);
	for (int i = 2; i <= n; i++)
		printf("%d %d %d\n", rnd.next(1, i - 1), i, rnd.next(1, c));
	printf("%d\n", k);
	vector<int> s1, s2;
	for (int i = 1; i <= n; i++)
		s2.push_back(i);
	forn(i, k) {
		if (!s1.size() || (s2.size() && rnd.next(0, 1))) {
			int i = rnd.next(0, (int)s2.size() - 1);
			printf("+ %d\n", s2[i]);
			swap(s2[i], s2.back());
			s1.push_back(s2.back());
			s2.pop_back();
		} else {
			int i = rnd.next(0, (int)s1.size() - 1);
			printf("- %d\n", s1[i]);
			swap(s1[i], s1.back());
			s2.push_back(s1.back());
			s1.pop_back();
		}
	}
}
