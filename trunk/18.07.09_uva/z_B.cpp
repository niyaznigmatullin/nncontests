#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <map>
#define sz size()
using namespace std;
typedef long long int64;
map <string, double> w;
int p, g;
double eps = 1e-9;
int main() {
	cin >> p >> g;
	for (int i = 0; i < p; i++) {
		string name;
		double x;
		cin >> name >> x;
		w[name] = x;
	}
	for (int i = 0; i < g; i++) {
		string name, op;
		double sum = 0, y;
		do {
			cin >> name;
			if (w.count(name) != 0) sum += w[name];
			cin >> op;
		} while (op == "+");
		bool ok = true;
		cin >> y;
		if (op == "=") {
			ok &= fabs(sum - y) < eps;
		} else if (op == "<") {
			ok &= sum < y - eps;
		} else if (op == ">") {
			ok &= sum > y + eps;
		} else if (op == "<=") {
			ok &= sum < y + eps;
		} else if (op == ">=") {
			ok &= sum > y - eps;
		}
		if (ok) printf("Guess #%d was correct\n", i + 1); else printf("Guess #%d was incorrect\n", i + 1);
	}
    return 0;
}