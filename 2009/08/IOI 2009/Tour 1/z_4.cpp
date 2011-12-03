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
int n, m, a[51][51], dp[51][51], dict[51][51][51][51];

int sum(int i1, int i2, int j1, int j2) {
	return dp[i2][j2] - dp[i2][j1 - 1] - dp[i1 - 1][j2] + dp[i1 - 1][j1 - 1];
}
int rec(int i1, int i2, int j1, int j2) {	
	int &ret = dict[i1][i2][j1][j2];
	if (i1 == i2 && j1 == j2) return ret = 0;
	if (ret >= 0) return ret;
	int s = sum(i1, i2, j1, j2);
	ret = 1 << 30;
	for (int i = i1; i < i2; i++) {
		ret = min(ret, rec(i1, i, j1, j2) + rec(i + 1, i2, j1, j2) + s);
	}
	for (int j = j1; j < j2; j++) {
		ret = min(ret, rec(i1, i2, j1, j) + rec(i1, i2, j + 1, j2) + s);
	}
	return ret;
}
int main() {
	scanf("%d%d", &n, &m);
	for (int i = 1; i <= n; i++) 
		for (int j = 1; j <= m; j++) scanf("%d", &a[i][j]);	
	memset(dp, 0, sizeof dp);
	memset(dict, -1, sizeof(dict));
	for (int i = 1; i <= n; i++)
		for (int j = 1; j <= m; j++) dp[i][j] = a[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
	printf("%d\n", rec(1, n, 1, m));
	fflush(stdout);
	return 0;
}
