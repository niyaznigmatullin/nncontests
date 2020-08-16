/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	srand(time(NULL));
	int n = 1001;
	vector<int> a(n);	
	for (int i = 0; i < n; i++) a[i] = i;
	random_shuffle(a.begin(), a.end());
	double prob = 1. / 7;
	vector<int> need(n);
	for (int i = 0; i < n; i++) {
		if (rand() % 1000 < 1000 * prob) {
			need[i] = 1;
		}
	}
	double z = 0;
	for (int i = 0; i < n; i++) {
		if (need[i])
			a[i] -= n / pow(2, z++);
	}
	int good = 0;
	int all = 0;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (a[i] < 0 || a[j] < 0) continue;
			if (a[i] + a[j] >= n) {
				good++;
			}
			all++;
		}
	}
	cout << 1. * good / all << ' ' << good << ' ' << all << endl;
}