#include <iostream>
using namespace std;
int main() {
	unsigned int n,m;
	cin >> n >> m;
	cout << (n^(1<<m));
	return 0;
}
