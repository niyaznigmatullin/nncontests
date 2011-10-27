#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;
double eps=1e-9,a[101][101];
bool u[110],res;
int n;
bool eq(double x, double y) { return fabs(x-y)<=eps; }
bool less_d(double x, double y) { return x-y<=-eps; }
int sign(int x) {
    if (x==0) return 0; else if (x<0) return -1; else return 1;
}
double getdis(int x, int y) {
    double rs=-1;
    u[x]=true;
    if (!eq(a[x][y],-1)) return sign(y-x)*a[x][y]; else {
        int i=1;
        for (;i<=n && eq(rs,-1);i++) {
            if (!u[i] && !eq(a[x][i],-1)) rs=getdis(i,y);
        }
        if (i<=n) {
            --i;
            a[x][y]=sign(i-x)*a[x][i]+rs;
            double ret=a[x][y];
            a[x][y]=fabs(a[x][y]);
            return ret;
        } else return -1;
    }
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    int e,x,y;
    double z;
    cin >> n >> e;
    res=true;
    for (int i=1; i<=n; i++)
    for (int j=1; j<=n; j++) a[i][j]=-1;
    for (int i=0; i<e; i++) {
        cin >> x >> y >> z;
        if (x==y) {
            if (!eq(z,0)) res=false;
        } else if (!eq(a[x][y],-1) && !eq(a[x][y],z)) res=false; else if (!eq(a[y][x],-1) && !eq(a[y][x],z)) res=false;
        a[x][y]=z;
        a[y][x]=z;
    }
    if (!res) {
        cout << "NO";
        return 0;
    }
    for (int i=2; i<=n; i++) {
        memset(u,false,sizeof(u));
        a[i-1][i]=getdis(i-1,i);
        if (eq(a[i-1][i],-1) || less_d(a[i-1][i],0)) {
            cout << "NO";
            return 0;
        }
    }
    for (int i=1; i<n; i++) {
        double r=0;
        for (int j=i+1; j<=n; j++) {
            r+=a[j-1][j];
            if ( (!eq(r,a[i][j]) && !eq(a[i][j],-1)) || (!eq(r,a[j][i]) && !eq(a[j][i],-1)) ) {
                cout << "NO";
                return 0;
            }
        }
    }
    cout << "YES" << "\n";
    for (int i=1; i<n; i++) printf("%.3f ",a[i][i+1]);
    return 0;
}
