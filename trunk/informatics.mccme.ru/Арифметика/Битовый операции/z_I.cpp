#include <iostream>
using namespace std;
int main() {
	unsigned int n,m,t=(1<<7);
	cin >> n;
	while (t>0) {
		cout << (int)((bool)(n&t));
		t>>=1;
	}
	return 0;
}
