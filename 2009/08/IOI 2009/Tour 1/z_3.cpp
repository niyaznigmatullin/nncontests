#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <map>
#include <queue>
#include <set>
#include <ctime>
#define sz size()
#define MP make_pair
#define eps (1e-10)
#define vi vector<int>
using namespace std;
typedef long long int64;
const double PI = acos(-1.);
int n, m, p, x;
bool a[2000][2000];
int pnt[2000], got[2000], prob[2000], c[2000];
bool ff(int i1, int i2) {
	if (got[i1] != got[i2]) return got[i1] > got[i2]; else if (prob[i1] != prob[i2]) return prob[i1] > prob[i2]; else
		return i1 < i2;
}
int main() {
	scanf("%d%d%d", &n, &m, &p);
	memset(pnt, 0, sizeof pnt);
	memset(got, 0, sizeof got);
	memset(prob, 0, sizeof prob);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &x);
			if (x == 1) a[i][j] = true; else a[i][j] = false;
			if (a[i][j]) pnt[j]++;
		}
	}
	for (int i = 0; i < m; i++) pnt[i] = n - pnt[i];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) if (a[i][j]) {
			got[i] += pnt[j];
			prob[i]++;
		}
		c[i] = i;
	}	
	sort(c, c + n, ff);
	int ans = -1;
	for (int i = 0; i < n; i++) if (c[i] == p - 1) ans = i + 1;
	printf("%d %d\n", got[p - 1], ans);
	fflush(stdout);
	return 0;
}
