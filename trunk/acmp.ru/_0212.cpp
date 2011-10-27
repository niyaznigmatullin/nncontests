#include <iostream>
#include <stdio.h>
#include <algorithm>
using namespace std;
bool u[200],a[200][200];
int dp[200][200],n,p,ans;

void dfs(int x) {
	u[x]=true;
	for (int i=1; i<=n; i++) dp[x][i]=200;
	dp[x][1]=0;
	for (int i=1; i<=n; i++) if (!u[i] && a[x][i]) {
		dfs(i);
		for (int j=n-1; j>0; j--) if (dp[x][j]<200) {
			for (int k=1; k<=n; k++) if (dp[i][k]+dp[x][j]<dp[x][k+j]) dp[x][k+j]=dp[i][k]+dp[x][j];
			dp[x][j]++;
		}
	}
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n >> p;
	int x,y;
	memset(a,false,sizeof(a));
	memset(u,false,sizeof(a));
	for (int i=0; i<n-1; i++) {
		cin >> x >> y;
		a[x][y]=true;
		a[y][x]=true;
	}
	dfs(1);
	ans=dp[1][p];
	for (int i=2; i<=n; i++) {
		if (dp[i][p]+1<ans) ans=dp[i][p]+1;
	}
	cout << ans;
	return 0;
}
