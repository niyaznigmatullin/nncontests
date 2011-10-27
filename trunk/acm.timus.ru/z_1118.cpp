#include <iostream>
#include <string>

using namespace std;
int n, m, ans, last = 1;
double ansd = 1e10;
bool p[2000000];

int main() {
    cin >> n >> m;
	if (n == 1) {
		cout << 1;
		return 0;
	}
	if (m-n+1<5000) {
		for (int i=n; i<=m; i++) {
			int sum = 0;
			for (int j=2; j*j<=i; j++) {
				if (i % j == 0) {
					sum += j;
					if (j != i / j) {
						sum += i / j;
					}
				}
			}
			if (1. * sum / i < ansd) {
				ansd = 1. * sum / i;
				ans = i;
			}
		}
		cout << ans;
	} else {
		memset(p,0,sizeof(p));
		for (int i=2; i<=m; i++) {
			if (!p[i]) {
				last = i;
				for (long long y=(long long)i*i; y<=m; y+=i) {
					p[(int)y] = true;
				}
			}
		}
		cout << last;
	}
    return 0;
}
