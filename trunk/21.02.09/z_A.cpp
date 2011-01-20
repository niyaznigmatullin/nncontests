#include <iostream>
#include <stdio.h>
using namespace std;
int n,q;

int count(int x, int y) {
    int ret=0;
    for (;x!=0 || y!=0;x/=10, y/=10) if (x%10!=y%10) ret++;
    return ret;
}

int main() {
    freopen("tableau.in","r",stdin);
    freopen("tableau.out","w",stdout);
    cin >> n;
    for (int i=n; i<9999; i++) q+=count(i,i+1);
    cout << q;
    return 0;
}
