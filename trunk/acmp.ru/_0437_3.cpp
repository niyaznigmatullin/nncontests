#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <map>
#include <set>
#include <string>
#define sz size()
using namespace std;
typedef long long int64;

int k,n,m,nn,n_2;
int w[1 << 18],fen[1 << 18],ans[1 << 19],lose[1 << 19],c[1 << 18][3];
int64 b[1 << 18][3],t[1 << 18],a[1 << 18];
bool lost[1 << 18];

int f(int x) {
	return x-(x & (x-1));
}

void update(int x, int y) {
	for (int i=x; i<=n_2; i+=f(i)) {
		fen[i]+=y;
	}
}

int find(int64 x) {
	int l=1,r=nn;
	while (l<r) {
		int mid=(l+r) >> 1;
		if (a[mid]==x) return mid; else
			if (a[mid]>x) r=mid-1; else l=mid+1;
	}
	return (x==a[l] ? l : 0);
}

int count_play(int x) {
	int ret=0;
	for (int i=x; i!=0; i-=f(i)) {
		ret+=fen[i];
	}
	return ret;
}

bool less_b(int i1, int i2) {
	int t1=b[i1][1] ^ b[i1][2], t2=b[i2][1] ^ b[i2][2], kol1=0, kol2=0;
	for (;t1; t1>>=1, kol1++);
	for (;t2; t2>>=1, kol2++);
	return (kol1<kol2) || (kol1==kol2 && b[i1][2]<b[i2][2]);
}

int plays_tour(int64 x, int64 y, int i) {
	return (x >> i) == (y >> i);
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d%d%d",&k,&n,&m);
	int kol=0;
	int64 x,y;
	for (int i=0; i<n; i++) {
		scanf("%I64d%I64d",&x,&y);
		x--;
		y--;
		b[i][1]=x;
		b[i][2]=y;
		t[++kol]=x;
		t[++kol]=y;
	}
	memset(a,-1,sizeof(a));
	memset(fen,0,sizeof(fen));
	sort(t+1,t+kol+1);
	if (kol==0) nn=0; else {
		nn=1;
		a[1]=t[1];
	}
	for (int i=2; i<=kol; i++) {
		if (t[i]!=t[i-1]) a[++nn]=t[i];
	}
	n_2=1;
	memset(lost,false,sizeof(lost));
	memset(ans,0,sizeof(ans));
	while (n_2<nn) n_2<<=1;
	for (int i=0; i<n; i++) w[i]=i;
	sort(w,w+n,less_b);
	int e=0;
	for (int i=1; i<=2; i++)
		for (int j=0; j<n; j++) c[j][i]=find(b[j][i]);
	for (int i=0; i<k; i++) {
		kol=0;
		for (int j=1; j<=nn; j++) { 
			if (!lost[j]) {
				int l=1,r=nn,i1,i2;
				while (l!=r) {
					int mid=((l+r) >> 1);
					if (a[mid]<(((a[j] >> i) ^ 1)) << i) l=mid+1; else r=mid;
				}
				i1=l;
				l=1;
				r=nn;
				while (l!=r) {
					int mid=((l+r) >> 1) + 1;
					if (a[mid]<(((a[j] >> i) ^ 1)+1) << i) l=mid; else r=mid-1;
				}
				i2=l;
				int cnt=0;
				for (; e<n && j==c[w[e]][2] && c[w[e]][1]>=i1 && c[w[e]][1]<=i2; e++) {
					if (!lost[c[w[e]][1]]) {
						cnt++;
					}
				}
				int dontplay=count_play(i2)-count_play(i1-1);
				if (((int64)(1) << (i))-dontplay==cnt) {
					lose[kol++]=j;
				}
			} 
		}
		for (int j=0; j<kol; j++) {
			lost[lose[j]]=true;
			update(lose[j],1);
			ans[lose[j]]=i+1;
		}
	}
	for (int i=0; i<m; i++) {
		scanf("%I64d",&x);
		x--;
		int p=find(x);
		if (p==0) printf("%d ",k); else
			if (ans[p]==0) printf("%d ",k); else printf("%d ",ans[p]);
	}
	return 0;
}
