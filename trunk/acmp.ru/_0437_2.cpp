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

map <int64,vector <int64> > op[64];
map <pair <int, int64>, set <int64> > go;
map <int64, int> res;

int k,n,m;

int get_tour(int64 x, int64 y) {
	int ret=0;
	for (;x!=y; ret++, x>>=1, y>>=1);
	return ret-1;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d%d%d",&k,&n,&m);
	string ss="";
	ss="MatAnaliz";
	cout << ss;
	for (int i=0; i<n; i++) {
		int64 x,y;
		scanf("%ld%ld",&x,&y);
		x--;
		y--;
		op[get_tour(x,y)][y].push_back(x);
	}
	for (int i=0; i<k; i++) {
		for (map<int64,vector<int64> >::iterator it=op[i].begin(); it!=op[i].end(); it++) {
			int64 y=it->first;
			set <int64> came=go[make_pair(i,(y >> i) ^1)];
			int64 num=(int64)(1) << i;
			vector <int64> opon=it->second;
			int cnt=0;
			for (int e=0; e<(int)opon.size(); e++) {
				if (came.count(opon[e])!=0) cnt++;
			}
			if (came.size()+opon.size()-cnt==num) {
				if (res.count(y)==0) res[y]=i;
				for (int j=i+1; j<=k; j++) go[make_pair(j, y>>j)].insert(y);
			}
		}
	}
	for (int i=0; i<m; i++) {
		int64 x;
		scanf("%ld",&x);
		x--;
		if (res.count(x)!=0) printf("%d ",res[x]+1); else printf("%d ",k);
	}
	return 0;
}
