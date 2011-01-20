#include <iostream>
#include <stdio.h>
using namespace std;
int n,x;
bool u[100001];
int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n;
    x=1;
    memset(u,false,sizeof(u));
    for (int i=2; i<=n; i++) {
        if (u[i]) x+=3; else x+=2;
        if (x<=100000) u[x]=true;
    }
    cout << x;
    return 0;
}
