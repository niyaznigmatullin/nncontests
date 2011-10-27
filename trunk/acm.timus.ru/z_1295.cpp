#include <iostream>
#include <string>

using namespace std;
int n;

long long MOD = 1000000000;

long long power(long long n, int k) {
	if (k == 0) return 1; else 
		if (k % 2 == 0) return power((n*n) % MOD, k / 2); else
			return (n * power(n, k-1)) % MOD;
}

int main() {
	cin >> n;
	long long e = 1 + power(2, n) + power(3, n) + power(4, n);
	int ans = 0;
	while (e % 10 == 0) {
		ans ++;
		e /= 10;
	}
	printf("%d\n", ans);
    return 0;
}
