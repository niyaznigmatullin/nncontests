#include <iostream>
#include <stdio.h>
#include <queue>
using namespace std;

int p[100],flow[100],n,m,cc[30],r[30],q=0,INF=((1<<30)-1)*2+1;
bool a[100][100];
long long ans[100][100],f[100][100],c[100][100];
int main() {
//	freopen("input.txt","r",stdin);
//	freopen("output.txt","w",stdout);
	cin >> n >> m;
	int x;
	for (int i=0; i<n; i++) cin >> r[i];
	for (int i=0; i<m; i++) cin >> cc[i];
	for (int i=0; i<n; i++) {
		for (int j=0; j<m; j++) {
			cin >> x;
			if (x==-1) {
				a[i+1][j+n+1]=true;
				c[i+1][j+n+1]=INF;
				c[j+n+1][i+1]=0;
				a[j+n+1][i+1]=true;
				ans[i][j]=-1;
			} else {
				r[i]-=x;
				cc[j]-=x;
				q+=x;
				ans[i][j]=x;
			}
		}
	}
	for (int i=0; i<n; i++) if (r[i]<0) {
		cout << -1;
		return 0;
	}
	for (int i=0; i<m; i++) if (cc[i]<0) {
		cout << -1;
		return 0;
	}
	for (int i=0; i<n; i++) {
		a[0][i+1]=true;
		c[0][i+1]=r[i];
	}
	for (int i=0; i<m; i++) {
		a[i+n+1][n+m+1]=true;
		c[i+n+1][n+m+1]=cc[i];
	}
	for (int i=0; i<n+m+2; i++)
	for (int j=0; j<n+m+2; j++) f[i][j]=0;
	for (;;) {
		queue <int> w;
		w.push(0);
		for (int i=0; i<n+m+2; i++) flow[i]=0;
		p[n+m+1]=-1;
		flow[0]=INF;
		for (;!w.empty();) {
			int x=w.front();
			w.pop();
			for (int i=0; i<n+m+2; i++) if (!flow[i] && a[x][i] && c[x][i]-f[x][i]>0) {
				int y=i;
				w.push(y);
				p[y]=x;
				if (c[x][y]-f[x][y]<flow[x]) flow[y]=c[x][y]-f[x][y]; else flow[y]=flow[x];
				if (y==n+m+1) goto lb1;
			}
		}
		lb1:
		if (p[n+m+1]==-1) break;
		int u=n+m+1;
		while (u) {
			f[p[u]][u]+=flow[n+m+1];
			f[u][p[u]]-=flow[n+m+1];
			u=p[u];
		}
		q+=flow[n+m+1];
	}
	for (int i=0; i<n; i++) {
		for (int j=0; j<m; j++) {
			if (ans[i][j]==-1) {
				cout << f[i+1][j+n+1] << " ";
			} else cout << ans[i][j] << " ";
		}
		cout << "\n";
	}
	return 0;
}
