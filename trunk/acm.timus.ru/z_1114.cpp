#include <iostream>
#include <stdio.h>

using namespace std;

unsigned long long n,a,b,c[50][50];

int main() {
    cin >> n >> a >> b;
    c[0][0]=1;
    for (int i=1; i<=35; i++) {
        c[i][0]=1;
        for (int j=1; j<=i; j++) c[i][j]=c[i-1][j-1]+c[i-1][j];
    }
    cout << c[n+a][a]*c[n+b][b];
	return 0;
}
