#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#define sz size()
using namespace std;
typedef long long int64;
int a[100001],b[100001],c[100001],d[100001],w[100001],n;
int head[2000010];

bool less_a(int i1, int i2) {
	return (a[i1]<a[i2] || (a[i1]==a[i2] && i1<i2) );
}

int64 mergesort(int l, int r) {
	if (l==r) return 0;
	int m=(l+r)>>1;
	int64 ret=mergesort(l,m)+mergesort(m+1,r);
	int i=l, j=m+1, k=0;
	for (; i<=m && j<=r; k++) {
		if (d[i]>d[j]) {
			ret+=m-i+1;
			w[k]=d[j++];
		} else {
			w[k]=d[i++];
		}
	}
	for (;i<=m;k++) {
		w[k]=d[i++];
	}
	for (;j<=r;k++) {
		w[k]=d[j++];
	}
	for (int i=0; i<k; i++) {
		d[i+l]=w[i];
	}
	return ret;
}
int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d",&n);
	for (int i=0; i<n; i++) {
		scanf("%d",&a[i]);
		a[i]+=1000000;
	}
	for (int i=0; i<n; i++) {
		scanf("%d",&b[i]);
		b[i]+=1000000;
	}
	for (int i=0; i<n; i++) c[i]=i;
	sort(c,c+n,less_a);
	head[a[c[0]]]=0;
	for (int i=1; i<n; i++) {
		if (a[c[i]]!=a[c[i-1]]) head[a[c[i]]]=i;
	}
	for (int i=0; i<n; i++) if (a[c[head[b[i]]]]==b[i]) {
		d[i]=c[head[b[i]]];
		head[b[i]]++;
	} else {
		cout << -1;
		return 0;
	}
	cout << mergesort(0,n-1);
	return 0;
}
