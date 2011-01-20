#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <vector>
#include <string>
using namespace std;

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    long long n,k;
    cin >> n >> k;
    long long ans=1;
    for (int i=0; i<n; i++) ans*=n;
    cout << ans-k*(n-1);
    return 0;
}
