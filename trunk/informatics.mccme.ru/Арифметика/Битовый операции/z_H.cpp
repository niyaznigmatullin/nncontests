#include <iostream>
using namespace std;
int main() {
	unsigned int n,m,t;
	cin >> n >> m;
	cout << (int)((bool)(n&(1<<m)));
	return 0;
}
