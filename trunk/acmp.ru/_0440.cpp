#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#include <queue>
#define sz size()
using namespace std;

int n,q=0;
bool ans[5],eps=1e-9;
int x[5],y[5];
bool ge(double x, double y) {
	return x-y>-eps;
}
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	for (int i=0; i<5; i++) scanf("%d%d",&x[i],&y[i]);
	for (int i=0; i<5; i++) if (ge(10.,sqrt(1.*(x[i]-25*i)*(x[i]-25*i)+1.*y[i]*y[i]))) ans[i]=true;
	int q=0;
	for (int i=0; i<5; i++) if (ans[i]) q++;
	cout << q;
	return 0;
}
