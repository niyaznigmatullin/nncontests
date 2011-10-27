#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

class TPoint{
    public:
    double x,y;
    TPoint(){x=y=0;}
};

class TLine{
    public:
    double a,b,c;
    TLine(){a=b=c=0;}
};

TLine L;
TPoint p1,p2,p3;
double r,d,a,b,c,eps=1e-9,pi=acos(-1.),ans=0.;

double gr(double x, double y) {
    return (x-y)>eps;
}

double eq(double x, double y) {
    return fabs(x-y)<eps;
}

double ge(double x, double y) {
    return gr(x,y) || eq(x,y);
}
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> p1.x >> p1.y >> p2.x >> p2.y >> p3.x >> p3.y >> r;
    p1.x-=p3.x;
    p1.y-=p3.y;
    p2.x-=p3.x;
    p2.y-=p3.y;
    L.a=p2.y-p1.y;
    L.b=p1.x-p2.x;
    L.c=-(L.a*p1.x+L.b*p1.y);
    a=sqrt(p1.x*p1.x+p1.y*p1.y);
    b=sqrt(p2.x*p2.x+p2.y*p2.y);
    if (gr(r,a) || gr(r,b)) {
        printf("-1.000000");
        return 0;
    }
    c=sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    d=fabs(L.c/sqrt(L.a*L.a+L.b*L.b));
    double f;
    if (ge(a*a+c*c,b*b) && ge(b*b+c*c,a*a)) {
        f=d;
    } else if (gr(c*c,a*a+b*b)) f=a; else f=b;
    if (ge(f,r)) {
        printf("%.6lf",c);
        return 0;
    }
    ans+=sqrt(a*a-r*r);
    ans+=sqrt(b*b-r*r);
    double alf=acos(d/a)+acos(d/b)-acos(r/a)-acos(r/b);
    while (gr(0.,alf)) alf+=2*pi;
    alf=min(alf,2*pi-alf);
    ans+=alf*r;
    printf("%.6lf",ans);
	return 0;
}
