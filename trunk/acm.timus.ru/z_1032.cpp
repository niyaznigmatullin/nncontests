#include <iostream>
#include <algorithm>
#include <string>
using namespace std;
int c[20000], a[20000], n;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
    scanf("%d", &n);
	memset(c,-1,sizeof(c));
	c[0] = 0;
	int sum = 0;
	for (int i=0; i<n; i++) {
		scanf("%d", &a[i]);
		sum = (sum + a[i]) % n;
		if (c[sum] != -1) {
			cout << i-c[sum]+1 << "\n";
			for (int j=c[sum]; j<=i; j++) {
				cout << a[j] << "\n";
			}
			break;
		}
		c[sum] = i+1;
	}
    return 0;
}
