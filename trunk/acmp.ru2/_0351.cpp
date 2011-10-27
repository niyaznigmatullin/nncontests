#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;


int main() {
	int n,m,k;
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d%d\n",&n,&k);
	string s;
	cin >> s;
	vector <int> aa;
	int x;
	for (int i=0; i<n; i++) { cin >> x; aa.push_back(x); }
	vector <int> vv;
	sort(aa.begin(),aa.end());
	int a[256],q=0;
	for (int i=0; i<256; i++) a[256]=-1000000;
	a[s[0]]=0;
	for (int i=1; i<(int)s.size(); i++) {
		if (i-a[s[i]]>k) q++;
		a[s[i]]=i;
	}
	cout << q;
	return 0;
}
