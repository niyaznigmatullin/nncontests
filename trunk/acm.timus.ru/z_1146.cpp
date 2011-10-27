#include <stdio.h>

int n,m,a[101][101],c[101][101],maxx,maxi;

int main()
{
    scanf("%d",&n);
m = n;
    for (int i=0; i<=n; i++) {
        for (int j=0; j<=m; j++) {
            a[i][j]=0;
            c[i][j]=0;
        }
    }
    for (int i=1; i<=n; i++) {
        for (int j=1; j<=m; j++) {
            scanf("%d",&a[i][j]);
            c[i][j]=c[i-1][j]+a[i][j];
        }
    }
    maxx=a[1][1];
    for (int i=1; i<=n; i++) {
        for (int k=1; k<=i; k++) {
            maxi=0;
            for (int j=1; j<=m; j++) {
                if (maxi<0) {
                    maxi=c[i][j]-c[i-k][j];
                } else maxi+=c[i][j]-c[i-k][j];
                if (maxx<maxi) {
                maxx=maxi;
                }
            }
        }
    }
    printf("%d",maxx);
    return 0;
}