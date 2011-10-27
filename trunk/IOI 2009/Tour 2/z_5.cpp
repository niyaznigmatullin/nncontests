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
int weight[2000], rate[100], g[2001], n, m;
bool is_em[100];
int main() {
	scanf("%d%d", &n, &m);
	for (int i = 0; i < n; i++) {
		scanf("%d", &rate[i]);
	}
	for (int i = 0; i < m; i++) {
		scanf("%d", &weight[i]);
	}
	memset(is_em, true, sizeof is_em);
	int ans = 0;
	queue<int> w;
	for (int i = 0; i < 2 * m; i++) {
		for (int j = 0; j < n; j++) {
			if (w.empty()) break;
			if (is_em[j]) {
				int y = w.front();
				ans += rate[j] * weight[y - 1];
				g[y] = j;
				w.pop();
				is_em[j] = false;
			}
		}
		int x;
		scanf("%d", &x);
		if (x < 0) {
			is_em[g[-x]] = true;
		} else {
			w.push(x);
		}
	}
	printf("%d\n", ans);
	fflush(stdout);
	return 0;
}
