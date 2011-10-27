#include <iostream>
#include <stdio.h>
#include <queue>
using namespace std;

typedef int arr[100001];
int n,cnodes=0;
arr v,a,b,ans,p,next,head;
bool u[100001];


void add_edge(int x, int y) {
    cnodes++;
    int node=cnodes;
    next[node]=head[x];
    head[x]=node;
    v[node]=y;
}

int main() {
    scanf("%d",&n);
	for (int i=0; i<n; i++) scanf("%d",&a[i]);
	for (int i=0; i<n-1; i++) {
        add_edge(a[i],a[i+1]);
	}
	int s=a[0],f=a[n-1];
	queue <int> w;
	int kol=0,vv=-1;
/*	if (s==f) {
	    vv=s;
	    s=a[1];
	}*/
	w.push(s);
	while (true) {
	    int x=w.front();
	    if (x==f) break;
	    w.pop();
	    int node=head[x];
	    while (node) {
            if (!u[v[node]]) {
                w.push(v[node]);
                p[v[node]]=x;
                u[v[node]]=true;
            }
            node=next[node];
	    }
	}
	for (int i=f; i!=0; i=p[i]) ans[kol++]=i;
	if (vv!=-1) printf("%d ",vv);
	for (int i=kol-1; i>=0; i--) printf("%d ",ans[i]);
	return 0;
}
