#include <iostream>
#include <vector>
#include <stdio.h>
#include <string>
using namespace std;
int n,ans=-1,a[1010],b[1010],c[1010],z[1010];

int sum_cifr(int x) {
    int ret=0;
    for (;x>0;x/=10) ret+=x%10;
    return ret;
}

int main() {
    freopen("bitonic.in","r",stdin);
    freopen("bitonic.out","w",stdout);

    cin >> n;
    for (int i=1; i<=n; i++) {
        cin >> a[i];
        z[i]=sum_cifr(a[i]);
    }
    a[0]=0;
    b[0]=0;
    c[n+1]=0;
    a[n+1]=0;
    for (int i=1; i<=n; i++) {
        int maxi=-1;
        for (int j=0; j<i; j++) if (b[j]>maxi && a[j]<a[i]) {
            maxi=b[j];
        }
        b[i]=maxi+z[i];
    }
    for (int i=n; i>0; i--) {
        int maxi=-1;
        for (int j=n+1; j>i; j--) if (c[j]>maxi && a[j]<a[i]) {
            maxi=c[j];
        }
        c[i]=maxi+z[i];
    }
    for (int i=2; i<n; i++) {
        if (b[i]!=z[i] && c[i]!=z[i]) {
            ans=max(ans,b[i]+c[i]-z[i]);
        }
    }
    cout << ans;
	return 0;
}
