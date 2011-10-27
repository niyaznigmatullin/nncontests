#include <iostream>
#include <string>
#include <stdio.h>
using namespace std;

int p[50000], d[50000], n, k, kol = 0, ans;
bool re[100000];

int main() {
    cin >> n >> k;
	memset(re, 0, sizeof(re));
	memset(d, 0, sizeof(d));
	for (int i=2; i<=n; i++) {
		if (!re[i]) {
			p[kol++] = i;
			for (long long y = (long long)i * i; y<=n; y+=i) {
				re[y] = true;
			}
		}
	}
	if (n - k < k) k = n - k;
	for (int i=n-k+1; i<=n; i++) {
		int y = i;
		for (int j=0; y>1 && j<kol; j++) {
			while (y % p[j] == 0) {
				d[j]++;
				y /= p[j];
			}
		}
	}
	for (int i=2; i<=k; i++) {
		int y = i;
		for (int j=0; y>1 && j<kol; j++) {
			while (y % p[j] == 0) {
				d[j]--;
				y /= p[j];
			}
		}
	}
	for (int i=0; i<kol; i++) {
		if (d[i]) {
			ans++;
		}
	}	
	printf("%d", ans);
    return 0;
}
