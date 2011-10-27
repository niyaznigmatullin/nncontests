#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;
struct TRect{
	long long x1,y1,x2,y2;
};
TRect p[200];
int n;
long long q=0;

bool less_x(TRect i1, TRect i2) { return i1.x1<i2.x1; }

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	for (int i=0; i<n; i++) {
		cin >> p[i].x1 >> p[i].y1 >> p[i].x2 >> p[i].y2;
		if (p[i].x1>p[i].x2) swap(p[i].x1,p[i].x2);
		if (p[i].y1>p[i].y2) swap(p[i].y1,p[i].y2);
		p[i].x2--;
		p[i].y2--;
	}
	sort(p,p+n,less_x);
	long long now,first;
	for (int i=-10001; i<=10001; i++) {
		now=-10001;
		for (int j=0; j<n; j++) if (p[j].y1<=i && p[j].y2>=i) {
			first=max(now,p[j].x1);
			q+=max((long long)0,p[j].x2-first+1);
			now=max(p[j].x2+1,now);
		}
	}
	cout << q;
	return 0;
}
