#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
using namespace std;

char c;
int n;
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%c%d",&c,&n);
	for (int i=-2; i<=2; i++)
	for (int j=-2; j<=2; j++) if (i!=0 && j!=0 && abs(i)+abs(j)==3) {
        if (c+i>='a' && c+i<='h' && n+j>=1 && n+j<=8) {
            printf("%c%d\n",c+i,n+j);
        }
	}
	return 0;
}
