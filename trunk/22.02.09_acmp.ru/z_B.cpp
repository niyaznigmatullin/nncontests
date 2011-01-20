#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;
int n,l,q,a[200];
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> l >> n;
    for (int i=0; i<n; i++) cin >> a[i];
    sort(a,a+n);
    for (int i=0; i<n; q++) {
        int j=i;
        for (;j<n-1 && a[j+1]<=a[i]+l+l;j++);
        i=j+1;
    }
    cout << q;
    return 0;
}
