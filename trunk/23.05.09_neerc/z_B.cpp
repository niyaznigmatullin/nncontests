#include <iostream>
#include <algorithm>
#include <memory.h>
using namespace std;

int b[10], n, m;
bool a[10][10];

int main() {
	freopen("circuit.in","r",stdin);
	freopen("circuit.out","w",stdout);
	cin >> n >> m;
	memset(a,false,sizeof(a));
	for (int i=0; i<m; i++) {
		int x, y;
		cin >> x >> y;
		x--;
		y--;
		a[x][y] = true;
		a[y][x] = true;
	}
	for (int i=0; i<n; i++) b[i] = i;
	int ans = 0;
	do {
		bool ok = true;
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++) {
				ok &= !(a[i][j] ^ a[b[i]][b[j]]);
			}
		if (ok) ans++;
	} while (next_permutation(b,b+n));
	cout << ans;
	return 0;
}