#include <iostream>
#include <stdio.h>
#include <string>

using namespace std;
int maxp=1,sum=0;
char c;

int main() {
    int p;
    while (cin >> c) {
        if (c<='9' && c>='0') p=c-'0'; else p=c-'A'+10;
        if (maxp<p) maxp=p;
        sum+=p;
    }
    for (int i=maxp+1; i<=36; i++) if (sum%(i-1)==0) {
        cout << i;
        return 0;
    }
    cout << "No solution.";
	return 0;
}
