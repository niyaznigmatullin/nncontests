#include <iostream>
#include <stdio.h>
#include <math.h>

using namespace std;
struct TLine{
    double a,b,c;
};
struct TCircle {
    double x,y,r;
};
double eps=1e-9;

TCircle w[300];
int n;
bool checkline(TLine l) {
    int wl=0,wr=0;
    double r;
    for (int i=0; i<n; i++) {
        r=l.a*w[i].x+l.b*w[i].y+l.c;
        if (fabs(r)-w[i].r<eps) return false;
        if (r<0) wl++; else wr++;
    }
    if (wl==0 || wr==0) return false;
    cout << "NO";
    return true;
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n;
    for (int i=0; i<n; i++) {
        cin >> w[i].x >> w[i].y >> w[i].r;
    }
    TLine l;
    double dx,dy,d,ca,sa,ex,ey;
    for (int i=0; i<n-1; i++)
    for (int j=i+1; j<n; j++) {
        dx=w[j].x-w[i].x;
        dy=w[j].y-w[i].y;
        d=sqrt(dx*dx+dy*dy);
        dx/=d;
        dy/=d;
        ca=(w[i].r-w[j].r)/d;
        sa=sqrt(1-ca*ca);
        ex=dx*ca-dy*sa;
        ey=dy*ca+dx*sa;
        l.a=ex;
        l.b=ey;
        l.c=-(w[i].x*ex+w[i].y*ey+w[i].r+2*eps);
        if (checkline(l)) return 0;
        ex=dx*ca+dy*sa;
        ey=dy*ca-dx*sa;
        l.a=ex;
        l.b=ey;
        l.c=-(w[i].x*ex+w[i].y*ey+w[i].r+2*eps);
        if (checkline(l)) return 0;
        ca=(w[i].r+w[j].r+4*eps)/d;
        if (ca-1<eps) sa=sqrt(1-ca*ca); else sa=0;
        ex=dx*ca-dy*sa;
        ey=dy*ca+dx*sa;
        l.a=ex;
        l.b=ey;
        l.c=-(w[i].x*ex+w[i].y*ey+w[i].r+2*eps);
        if (checkline(l)) return 0;
        ex=dx*ca+dy*sa;
        ey=dy*ca-dx*sa;
        l.a=ex;
        l.b=ey;
        l.c=-(w[i].x*ex+w[i].y*ey+w[i].r+2*eps);
        if (checkline(l)) return 0;
    }
    cout << "YES";
    return 0;
}
