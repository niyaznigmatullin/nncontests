#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <math.h>
#include <algorithm>
#define sz size()
#define pb push_back
#define all(a) a.begin(),a.end()
using namespace std;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	string s;
	cin >> s;
	vector <int> a;
	int sum=0;
	for (int i=0; i<(int)s.sz; i++) {
		a.pb(s[i]-'0');
		sum+=a.back();
	}
	int k=a.sz;
	a[k-1]++;
	for (int i=k-2; a[i+1]>10 && i>=0; i--) {
		a[i+1]%=10;
		a[i]++;
	}
	if (a[0]>9) {
		cout << -1;
		return 0;
	}
	int sum1=0;
	for (int i=0; i<k; i++) {
		sum1+=a[i];
	}
	for (int i=k-1; i>=0; i--) {
		if (sum1>sum) {
			sum1-=a[i];
			a[i]=0;
		} else if (9-a[i]>=sum-sum1) {
			a[i]+=sum-sum1;
			sum1=sum;
			break;
		} else {
			a[i]=9;
			sum1+=9-a[i];
		}
	}
	if (sum1!=sum) {
		cout << -1;
		return 0;
	}
	for (int i=0; i<k; i++) cout << a[i];
	return 0;
}
