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
int n,m,k;

int ans[2000],pu[2000],p[2000],pos[2000];
bool mark[2000];

bool less_h(int x, int y) {
	return ans[x]<ans[y];
}

class heap{
public:
	int h[100000],kolh;
	void swap_h(int x, int y) {
		int p=pos[h[x]];
		pos[h[x]]=pos[h[y]];
		pos[h[y]]=p;
		p=h[x];
		h[x]=h[y];
		h[y]=p;		
	}
	int minh() {
		return h[1];
	}
	int min_h(int x, int y) {
		return (less_h(h[x],h[y]) ? x : y);
	}
	void heapify_up(int x) {
		for (int i=x; i>1 && less_h(h[i],h[i>>1]); i>>=1) {
			swap_h(i,i>>1);
		}
	}
	void heapify_down(int x) {
		int i=x;
		for (; 2*i+1<=kolh && ( less_h(h[2*i+1],h[i]) || less_h(h[2*i],h[i])); ) {
			int t=min_h(2*i+1,2*i);
			swap_h(t,i);
			i=t;
		}
		if (2*i==kolh && less_h(h[2*i],h[i])) swap_h(2*i,i);
	}
	void insert(int x) {		
		h[++kolh]=x;
		pos[x]=kolh;
		heapify_up(kolh);
	}
	void erase(int x) {
		swap_h(x,kolh--);
		heapify_down(x);
		pos[h[kolh+1]]=0;
	}
};

heap w;
void print(int x) {
	if (x==0) return;
	print(pu[x]);
	cout << p[x];
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	k=(m-n%m)%m;
	if (k==0) {
		cout << 0;
		return 0;
	}
	ans[0]=0;
	w.insert(0);
	mark[0]=true;
	while (w.kolh!=0) {
		int xx=w.minh();
		w.erase(1);
		mark[xx]=true;
		int v=(xx*10)%m;
		for (int i=0; i<10; i++) {
			if ( (ans[xx]+i<ans[v] || pos[v]==0) && !mark[v]) {								
				ans[v]=ans[xx]+i;
				if (pos[v]>0) {
					w.heapify_up(pos[v]);
				} else w.insert(v);
				p[v]=i;
				pu[v]=xx;
			}
			if (v==m) v=0; else v++;
		}
	}
	print(k);
	return 0;
}
