#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <sstream>
#define sz size()
using namespace std;

string ans;
int a[2000],b[2000],n,m;

string toString(int x) {
	string ret="";
	ostringstream is(ret);
	is << x;
	ret=is.str();
	return ret;
}

string pars1(int l, int r) {
	string ret="";
	for (int i=l; i<=r; i++) {
		if (i!=r) ret+=toString(a[i])+", "; else ret+=toString(a[i]);
	}
	return ret;
}

string pars2(int l, int r) {
	string ret="";
	ret+=toString(a[l])+", ..., ";
	ret+=toString(a[r]);
	return ret;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d",&n);
	for (int i=0; i<n; i++) scanf("%d", &b[i]);
	int m=n;
	n=1;
	sort(b,b+m);
	a[0]=b[0];
	for (int i=1; i<m; i++) {
		if (b[i]!=b[i-1]) a[n++]=b[i];
	}
	sort(a,a+n);
	for (int i=0; i<n; ) {
		int j=i;
		for (;j<n-1 && a[j+1]-a[j]<2;j++);
		string s1=pars1(i,j), s2=pars2(i,j);
		if (s1.length()>s2.length()) {
			if (i!=0) ans+=", "+s2; else ans+=s2;
		} else {
			if (i!=0) ans+=", "+s1; else ans+=s1;
		}
		i=j+1;
	}
	cout << ans;
	return 0;
}
