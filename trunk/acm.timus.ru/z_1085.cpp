#include <iostream>
#include <string>

using namespace std;

int a[200][200], n, m, k, mon[200], pr[200], st[200];

int main() {
    scanf("%d%d", &n, &m);
	for (int i=1; i<=n; i++)
		for (int j=1; j<=n; j++) {
			a[i][j] = 1 << 20;
		}
	for (int i=1; i<=n; i++) a[i][i] = 0;
	for (int i=0; i<m; i++) {
		int L, b[200];
		scanf("%d", &L);
		for (int j=0; j<L; j++) {
			scanf("%d", &b[j]);
		}
		for (int ii=0; ii<L; ii++)
			for (int j=ii+1; j<L; j++) {
				a[b[ii]][b[j]] = 1;
				a[b[j]][b[ii]] = 1;
			}
	}
	scanf("%d", &k);
	for (int i=0; i<k; i++) {
		scanf("%d%d%d", &mon[i], &st[i], &pr[i]);
	}
	for (int t=1; t<=n; t++)
		for (int i=1; i<=n; i++)
			for (int j=1; j<=n; j++) a[i][j] = min(a[i][j], a[i][t]+a[t][j]);
	int ans = 0, ans1 = 1 << 30;
	for (int i=1; i<=n; i++) {
		int r = 0;
		bool ok = true;
		for (int j=0; j<k; j++) {
			ok &= ((pr[j] && a[st[j]][i]<(1<<20)) || a[st[j]][i]*4<=mon[j]);
			if (!ok) break;
			if (!pr[j]) {
				r += a[st[j]][i] * 4;
			}
		}
		if (ok && ans1 > r) {
			ans1 = r;
			ans = i;
		}
	}
	if (ans) {
		printf("%d %d", ans, ans1);
	} else printf("0");
    return 0;
}
