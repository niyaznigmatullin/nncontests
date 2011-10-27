#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

int a,b,x,y;
double eps=1e-7,pi=acos(-1.0);

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> a >> b >> x >> y;
	if (a>b) swap(a,b);
	if (x>y) swap(x,y);
	double l=0,r=pi/2,m1,m2,x1,x2,y1,y2;
	while (r-l>eps*eps) {
		m1=(l+l+r)/3;
		m2=(l+r+r)/3;
		x1=cos(m1)*a+sin(m1)*b;
		x2=cos(m2)*a+sin(m2)*b;
		if (x1-x<eps && x2-x<eps) {
			y1=sin(m1)*a+cos(m1)*b;
			y2=sin(m2)*a+cos(m2)*b;
			if (y1-y<eps && y2-y<eps) {
				cout << "Possible";
				return 0;
			} else {
				if (y1>y2) l=m1; else r=m2;
			}
		} else {
			if (x1-x2>eps) l=m1; else r=m2;
		}
	}
	cout << "Impossible";
	return 0;
}
