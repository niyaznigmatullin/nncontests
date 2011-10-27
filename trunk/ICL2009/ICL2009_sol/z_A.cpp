#include <iostream>
#include <stdio.h>
using namespace std;

char a[6][6];

void inverse(int x, int y) {
    if (a[x-1][y]=='W') a[x-1][y]='B'; else a[x-1][y]='W';
    if (a[x][y-1]=='W') a[x][y-1]='B'; else a[x][y-1]='W';
    if (a[x][y+1]=='W') a[x][y+1]='B'; else a[x][y+1]='W';
    if (a[x+1][y]=='W') a[x+1][y]='B'; else a[x+1][y]='W';
}

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    for (int i=1; i<=4; i++)
    for (int j=1; j<=4; j++) cin >> a[i][j];
    for (int i=1; i<=3; i++)
    for (int j=1; j<=4; j++) if (a[i][j]=='W') {
        inverse(i+1,j);
    }
    for (int i=1; i<=4; i++) if (a[4][i]=='W') {
        cout << "NO";
        return 0;
    }
    cout << "YES";
	return 0;
}
