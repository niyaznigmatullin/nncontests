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
int n, s[500000], q[500000], gone, maxN = (1 << 19) - 1, c[500000];
double tr[(1 << 20) + 4], coef, first;
int64 W;
bool ddd(int i1, int i2) {
	return 1. * q[i1] / s[i1] < 1. * q[i2] / s[i2];
}
void update(int x, int y) {
	x += maxN;
	double coef = q[y] / first;
	tr[x] = s[c[0]] * coef;
	x /= 2;
	for (;x > 0; x >>= 1) tr[x] += s[y] * coef;
}
double get(int node, int f, int l, int r, double w) {
	if (f > r) return 0;
	if (l == r) {
		if (tr[node] * coef <= w) gone = l;
		return tr[node] * coef;
	}
	if (l >= f) {
		if (tr[node] * coef < w) return tr[node] * coef;
	}
	double g1 = get(node << 1, f, l, (l + r) >> 1, w) * coef;
	if (g1 <= w) {
		get((node << 1) | 1, f, ((l + r) >> 1) + 1, r, w - g1);
	}
	return g1 + tr[(node << 1) | 1] * coef;
}
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt", "w",stdout);
	scanf("%d %lld", &n, &W);
	for (int i = 0; i < n; i++) {
		scanf("%d%d", &s[i], &q[i]);
		c[i]  = i;
	}
	sort(c, c + n, ddd);
	first = q[c[0]];
	for (int i = 0; i < n; i++) {
		update(i + 1, c[i]);
	}
	int t = -1, ans = -1;
	for (int i = 1; i <= n; i++) {
		gone = i - 1;
		coef = q[c[i]] / first;
		get(1, i + maxN, 1, maxN, (double)(W));
		if (ans < gone - i + 1) {
			ans = gone - i + 1;
			t = i;
		}
	}	
	printf("%d\n", ans);
	for (int i = 0; i < ans; i++) {
		printf("%d\n", c[t + i] + 1);
	}
//	fflush(stdout);
	return 0;
}
