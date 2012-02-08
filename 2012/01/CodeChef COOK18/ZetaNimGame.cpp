#include <iostream>
#include <math.h>
#include <cstdio>

using namespace std;
typedef unsigned long long ll;
const int MAXN = 2013;

ll g[MAXN][MAXN];

int nOZ(ll i)
{
	unsigned int x, y;
	if (i == 0) return 64;
	int n = 63;
	y = (unsigned int)i; if (y != 0) { n = n -32; x = y; } else x = (unsigned int)(i>>32);
	y = x <<16; if (y != 0) { n = n -16; x = y; }
	y = x << 8; if (y != 0) { n = n - 8; x = y; }
	y = x << 4; if (y != 0) { n = n - 4; x = y; }
	y = x << 2; if (y != 0) { n = n - 2; x = y; }
	return n - ((x << 1) >> 31);
  
}

void getit()
{
	for (int i = 0; i < MAXN; i++) {
		ll last = 0;
		for (int j = 1; j < MAXN; j++) {
			ll current = ~(g[i][j] | last);
			last = ~current;
			current = nOZ(current & -current);
			g[i][j] = current;
			if (i + j < MAXN) {
				g[i + j][j] |= 1LL << current;
			}
		}
	}
}

void solve() {
	int n, k;
	scanf("%d%d", &n, &k);
	int ans = 0;
	for (int i = 0; i < n; i++) {
		int x;
		scanf("%d", &x);
		int cur = x < k ? x : k;
		ans ^= g[x][cur];
	}
	printf(ans == 0 ? "Zeta\n" : "Nancy\n");
}

int main()
{
	getit();
	int t;
	scanf("%d", &t);
	for (int i = 0; i < t; i++) {
		solve();
	}
	return 0;
}
