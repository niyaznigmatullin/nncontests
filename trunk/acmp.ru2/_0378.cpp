#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <algorithm>
#define pb push_back
using namespace std;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	int n,x;
	cin >> n;
	vector <int> a;
	for (int i=0; i<n; i++) {
		cin >> x;
		a.pb(x);
	}
	bool dp[50001];
	memset(dp,false,sizeof(dp));
	dp[0]=true;
	for (int j=0; j<n; j++) {
		for (int i=50000; i>=0; i--) if (i+a[j]<=50000) dp[i+a[j]]|=dp[i];
	}
	int q=0;
	for (int i=0; i<=50000; i++) {
		if (dp[i]) q++;
	}
	cout << q;
	return 0;
}
