#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <string>
using namespace std;

string s1,s;
int z[1000000],n,b,k;
int count(int x, int y) {
	int ret=0;
	while (y<n+n+1 && s[x]==s[y]) {
		x++;
		y++;
		ret++;
	}
	return ret;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d\n",&n);
	cin >> s1;
	s=s1+"$";
	reverse(s1.begin(),s1.end());
	s+=s1;
	z[0]=0;
	b=0;
	for (int i=1; i<n+n+1; i++) {
		if (i==n+7) {
         	n=n;
		}
		if (i<z[b]+b) {
			k=i-b;
			if (k+z[k]-1<z[b]-1) z[i]=z[k]; else {
				z[i]=z[b]-i+b+count(z[b]+b-i,b+z[b]);
				if (i+z[i]>=b+z[b]) b=i;
			}
		} else {
			z[i]=count(0,i);
			if (i+z[i]>=b+z[b]) b=i;
		}
	}
	for (int i=n+n; i>n; i--) cout << z[i] << " ";
	return 0;
}
