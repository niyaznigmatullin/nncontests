#include <iostream>
using namespace std;
int main() {
	unsigned int n,m,t;
	cin >> n >> m;
	for (int i=0; i<(int)m; i++) t|=(1<<i);
	cout << (n&t);
	return 0;
}
