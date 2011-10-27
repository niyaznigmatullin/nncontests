#include <iostream>
#include <stdio.h>
using namespace std;
int main() {
	int n,x,neg=-1000000,pos=1000000,y;
//	freopen("input.txt","r",stdin);
//	freopen("output.txt","w",stdout);
	scanf("%d%d",&n,&x);
	for (int i=0; i<n; i++) {
		scanf("%d",&y);
		if (y<0) {
			if (neg<y) neg=y;
		} else {
			if (pos>y) pos=y;
		}
	}
	if (x<neg || x>pos) printf("Impossible"); else {
		if (x<0) {
			printf("%d %d",pos+pos-x,-x);
		} else {
			printf("%d %d",x,x-neg-neg);
		}
	}
	return 0;
}