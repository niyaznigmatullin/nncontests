#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

double pi=acos(-1.),eps=1e-7;

int x1[100],z1[100],x2[100],z2[100],n,m,r[100],p[100],ans=0;

bool u[100],a[100][100];

bool check(int xx, int yy) {
    int a=x1[xx],b=z1[xx],x=x2[yy],y=z2[yy];
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
                return true;
            } else {
                if (y1>y2) l=m1; else r=m2;
            }
        } else {
            if (x1-x2>eps) l=m1; else r=m2;
        }
    }
    return false;
}

bool dfs(int x) {
    if (!u[x]) {
        u[x]=true;
        for (int i=0; i<m; i++) {
            if (a[x][i] && (r[i]==-1 || dfs(r[i]))) {
                r[i]=x;
                p[x]=i;
                return true;
            }
        }
        return false;
    } else {
        return false;
    }
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    scanf("%d%d",&n,&m);
    for (int i=0; i<n; i++) scanf("%d%d",&x2[i],&z2[i]);
    for (int i=0; i<m;  i++) scanf("%d%d",&x1[i],&z1[i]);
    for (int i=0; i<n; i++)
    for (int j=0; j<m; j++) a[i][j]=check(i,j);
    memset(r,-1,sizeof(r));
    memset(p,-1,sizeof(p));
    for (int i=0; i<n; i++) {
        memset(u,0,sizeof(u));
        if (dfs(i)) ans++;
    }
    printf("%d\n",ans);
    for (int i=0; i<n; i++) if (p[i]!=-1) printf("%d %d\n",i+1,p[i]+1);
    return 0;
}
