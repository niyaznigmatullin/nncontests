#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
using namespace std;
struct TCargo{
	int t,n;
};

int n,m,d[20],s[300],b[300];
unsigned int MAXINT=(((long long)1)<<31)-1;
TCargo a[300];
bool time_l(TCargo i1, TCargo i2) {
	return (i1.t<i2.t) || (i1.t==i2.t && i1.n>i2.n);
}
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	for (int i=0; i<n; i++) {
		cin >> d[i+1];
	}
	memset(b,0,sizeof(b));
	int en,ex;
	for (int i=0; i<m; i++) {
		cin >> s[i+1] >> en >> ex;
		a[2*i].t=en;
		a[2*i].n=i+1;
		a[2*i+1].t=ex;
		a[2*i+1].n=-i-1;
	}
	sort(a,a+2*m,time_l);
	for (int i=0; i<2*m; i++) {
		int r=a[i].n;
		if (r<0) {
			r=-r;
			if (b[r]!=0) {
				printf("take cargo %d from cell %d\n",r,b[r]);
				d[b[r]]+=s[r];
				b[r]=0;
			}
		} else {
			int e=0,mind=MAXINT;
			for (int j=1; j<=n; j++) {
				if (d[j]>=s[r] && d[j]<mind) {
					e=j;
					mind=d[j];
				}
			}
			if (e!=0) {
				d[e]-=s[r];
				b[r]=e;
				printf("put cargo %d to cell %d\n",r,e);
			} else {
				int jj=0,kk=0,ss=MAXINT,sp1=ss,sp2=ss;
				for (int j=1; j<=m; j++)
				for (int k=1; k<=n; k++) if (b[j]!=0 && b[j]!=k && s[j]+d[b[j]]>=s[r] && s[j]<=d[k]) {
					if (ss>s[j] || (ss==s[j] && s[j]+d[b[j]]>sp1) || (ss==s[j] && s[j]+d[b[j]]==sp1 && d[k]<sp2)) {
						jj=j;
						kk=k;
						ss=s[j];
						sp1=s[j]+d[b[j]];
						sp2=d[k];
					}
				}
				if (jj!=0) {
					printf("move cargo %d from cell %d to cell %d\n",jj,b[jj],kk);
					printf("put cargo %d to cell %d\n",r,b[jj]);
					d[b[jj]]=d[b[jj]]-s[r]+s[jj];
					d[kk]-=s[jj];
					b[r]=b[jj];
					b[jj]=kk;
				} else printf("cargo %d cannot be stored\n",r);
			}
		}
	}
	return 0;
}
