#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;
struct TRect{
	int x1,y1,x2,y2;
};
TRect p[200];
int n;
long long q=0;

bool less_x(TRect i1, TRect i2) { return i1.x1<i2.x2; }

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	for (int i=0; i<n; i++) {
		cin >> p[i].x1 >> p[i].y1 >> p[i].x2 >> p[i].y2;
		p[i].x2--;
		p[i].y2--;
		if (p[i].x1>p[i].x2) swap(p[i].x1,p[i].x2);
		if (p[i].y1>p[i].y2) swap(p[i].y1,p[i].y2);
	}
	sort(p,p+n,less_x);
	int now,first;
	for (int i=-10000; i<=10000; i++) {
		now=-10000;
		for (int j=0; j<n; j++) if (p[j].y1>=i && p[j].y2<=i) {
			first=max(now,p[j].x1);
			q+=p[j].x2-first+1;
			now=p[j].x2+1;
		}
	}
	cout << q;
	return 0;
}
