#include <iostream>
#include <stdio.h>
#include <map>

using namespace std;

bool u[101];
map <int,int> c;
int n,k;

void add(int x, int y) {
	map<int,int>::iterator it=c.find(x);
	if (it==c.end()) {
		c[x]=y;
	} else {
		int e=y+it->second;
		if (e==0) c.erase(it); else it->second=e;
	}
}

long long gcd(int x, int y) {
	for (;x*y;) if (x>y) x%=y; else y%=x;
	return x+y;
}

long long lcm(int x, int y) {
	return x/gcd(x,y)*y;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> k;
	int x;
	for (int i=0; i<k; i++) {
		cin >> x;
		u[x]=!u[x];
	}
	for (int i=1; i<=50; i++) if (u[i]) {
		map<int,int> d(c);
		for (map<int,int>::iterator it=d.begin(); it!=d.end(); ++it) {
			long long h=lcm(it->first,i);
			if (h<=n) add(h,-2*it->second);
		}
		add(i,1);
	}
	int q=0;
	for (map<int,int>::iterator it=c.begin(); it!=c.end(); ++it) {
		q+=n/it->first*it->second;
	}
	cout << q;
	return 0;
}
