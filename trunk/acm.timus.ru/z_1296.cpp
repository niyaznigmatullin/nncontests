#include <iostream>
#include <stdio.h>
#include <vector>
#include <cmath>
#define pb push_back
#define sz size()
#define vi vector <int>

using namespace std;

int main() {
//	freopen("input.txt","r",stdin);
//	freopen("output.txt","w",stdout);
	int n,x;
	vi a;
	cin >> n;
	for (int i=0; i<n; i++) {
		cin >> x;
		a.pb(x);
	}
	int sum=0,minsum=0,ans=0;
	for (int i=0; i<n; i++) {
		sum+=a[i];
		minsum=min(sum,minsum);
		ans=max(sum-minsum,ans);
	}
	cout << ans;
	return 0;
}
