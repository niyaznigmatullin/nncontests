#include <iostream>
using namespace std;
int main() {
	unsigned int n,m;
	cin >> n >> m;
	if ((n&(1<<m))==0) cout << n; else cout << (n^(1<<m));
	return 0;
}
