#include <iostream>
#include <stdio.h>

using namespace std;

unsigned long long n,a,b,c[50][50];

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n >> a >> b;
    c[0][0]=1;
    for (int i=1; i<=41; i++) {
        c[i][0]=1;
        for (int j=1; j<=i; j++) c[i][j]=c[i-1][j-1]+c[i-1][j];
    }
    cout << c[n+a][a]*c[n+b][b];
    cout << "\n" << c[n+a][b];
	return 0;
}
