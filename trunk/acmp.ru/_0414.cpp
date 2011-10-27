#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#define sz size()
using namespace std;
typedef long long int64;

bool u[40000];
int p[40000],n,x,y;

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d",&n);
	scanf("%d%d",&x,&y);
	memset(u,false,sizeof(u));
	for (int i=2; i<=n; i++) scanf("%d",&p[i]);
	for (int i=x; i>1; i=p[i]) u[i]=true;
	for (int i=y; i>1; i=p[i]) if (u[i]) {
		cout << i;
		return 0;
	}
	cout << 1;
	return 0;
}
