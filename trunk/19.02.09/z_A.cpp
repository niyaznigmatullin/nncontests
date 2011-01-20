#include <iostream>
#include <stdio.h>
using namespace std;
long long n,ans=1;
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n;
    for (int i=2;n>1;i++) {
        int k=1;
        for (;n%i==0;n/=i,k++);
        ans*=k;
    }
    cout << ans;
    return 0;
}
