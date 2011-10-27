#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;

int xx[100], yy[100], c[100], n, m, k;
bool less_xx(int i1, int i2) {
	return yy[i1] < yy[i2] || ( yy[i1] == yy[i2] && xx[i1] < xx[i2]);
}

int main() {
    cin >> n >> m;
	cin >> k;
	for (int i=0; i<k; i++) {
		cin >> xx[i] >> yy[i];
		xx[i]--;
		yy[i]--;
		c[i] = i;
	}
	sort(c,c+k,less_xx);
	vector <double> dp1(n+1,1e20);
	dp1[0] = 0;
	int now = 0;
	for (int i=0; i<m; i++) {
		vector <double> dp2(n+1,1e20);
		for (int j=0; j<=n; j++) {
			if (now < k && xx[c[now]] == j && yy[c[now]] == i) {
				dp2[j+1] = min(dp2[j+1], dp1[j] + sqrt(20000.));
				now++;
			}
			if (j!=n) {
				dp1[j+1] = min(dp1[j+1], dp1[j] + 100);
			}
			dp2[j] = min(dp2[j], dp1[j] + 100);
		}
		dp1 = dp2;
	}
	cout << (int)(dp1[n] + .5);
    return 0;
}
