#include <stdio.h>
#include <math.h>
#include <iostream>
using namespace std;
char c;
double dx[] = {0,-sqrt(2.)/2, 0, sqrt(2.)/2,-1,0,1,-sqrt(2.)/2,0,sqrt(2.)/2};
double dy[] = {0,-sqrt(2.)/2,-1,-sqrt(2.)/2, 0,0,0, sqrt(2.)/2,1,sqrt(2.)/2};

int main() {
	freopen("input.txt","r",stdin);
	double xx = 0, yy = 0;
	while (cin >> c) {
		if (c == '0') break;
		xx += dx[(int)c-'0'];
		yy += dy[(int)c-'0'];
	}
	printf("%.10f %.10f", xx, yy);
	return 0;
}
