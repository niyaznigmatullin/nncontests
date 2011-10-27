#include <iostream>
#include <stdio.h>
#include <vector>
using namespace std;
int n;
vector <int> ans;
int main() {
    cin >> n;
	while (n>1) {
	    ans.push_back(n/2);
	    n-=n/2;
	}
	cout << ans.size() << "\n";
	for (int i=0; i<(int)ans.size(); i++) {
	    cout << ans[i] << " ";
	}
	return 0;
}
