#include <iostream>
#include <stdio.h>
#include <string>

using namespace std;
long long ans;
int n,k;
string s;
int main() {
	cin >> n >> s;
	k=s.size();
	ans=n;
	while ((n-=k)>0) {
	    ans*=n;
	}
	cout << ans;
	return 0;
}
