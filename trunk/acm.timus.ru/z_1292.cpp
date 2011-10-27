#include <iostream>
#include <string>

using namespace std;
int n, k, L, T;
int main() {
	cin >> T;
	for (int i=0; i<T; i++) {
		cin >> n >> k >> L;
		int m = k;
		for (int i=2; i<=n; i++) {
			int p = 0, mm = m;
			while (m>0) {
				p += (m % 10) * (m % 10) * (m % 10);
				m /= 10;
			}
			m = p;
			if (mm==p) break;
		}
		printf("%d\n", m - L);
	}
    return 0;
}
