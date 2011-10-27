#include <iostream>
#include <stdio.h>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool u[101],a[101][101],p[101];
int n;
bool dfs(int x) {
	u[x]=true;
	p[x]=true;
	bool ok=true;
	for (int i=1; i<=n; i++) if (a[x][i]){
		if (u[i] && p[i])
			return false;
		if (!u[i]) ok&=dfs(i);
	}
	return ok;
}

int main() {
	int m,x,y;
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> m;
	memset(a,false,sizeof(a));
	memset(u,false,sizeof(u));
	for (int i=0; i<m; i++) {
		cin >> x >> y;
		a[x][y]=true;
	}
	bool ok=true;
	for (int i=1; i<=n; i++) {
		memset(p,false,sizeof(p));
		if (!u[i]) ok&=dfs(i);
	}
	if (ok) cout << "Yes"; else cout << "No";
	return 0;
}
