#include <stdio.h>
#include <map>
using namespace std;

int n;
map<int, int> w;
bool check(int x) {
	int t = x;
	for (int i = 2; i < 33; i++) {
		int k = 0;
		while(x % i == 0) k++, x /= i;
		k *= t;
		if (w.count(i) && w[i] > k) return false;
	}
	return true;
}

int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	scanf("%d", &n);	
	int ans = 1;
	for (int i = 2; i * i <= n; i++) {
		int k = 0;
		while (n % i == 0) n /= i, k++;
		if (k) ans *= i, w[i] = k;
	}
	ans *= n;	
	if (n > 1) w[n] = 1;
	if (ans < 33 && ans != n) {
		for (int i = 2; i < 33; i++) {
			if (check(i)) {
				ans = i;
				break;
			}
		}
	}
	printf("%d", ans);
	return 0;
}