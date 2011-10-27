#include <iostream>
#include <stdio.h>
typedef long long int64;
using namespace std;

long long ans=0,a[20],n;
int64 fact(int x) {
    int64 ret=1;
    for (int64 i=2; i<=x; i++) ret*=i;
    return ret;
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n;
    for (int i=0; i<n; i++) cin >> a[i];
    for (int i=0; i<n; i++) {
        ans+=fact(n-i-1)*(a[i]-1);
        for (int j=i+1; j<n; j++) if (a[j]>a[i]) a[j]--;
    }
    cout << ans+1;
	return 0;
}
