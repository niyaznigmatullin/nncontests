#include <iostream>
#include <stdio.h>
#include <queue>
#include <vector>
using namespace std;

queue <pair<int,int> > w;
pair <int,int> p[302][302];
vector <pair<int,int> > ans3;
int u[302][302],n,m,a[302][302],x2,x1,z1,z2,ans,ans2;

int main() {
    freopen("input.txt","r",stdin);
    freopen("output.txt","w",stdout);
    cin >> n >> m;
    for (int i=1; i<=n; i++)
    for (int j=1; j<=m; j++) cin >> a[i][j];
    cin >> x1 >> z1 >> x2 >> z2;
    memset(u,-1,sizeof(u));
    u[x1][z1]=0;
    w.push(make_pair(x1,z1));
    int i;
    while (!w.empty()) {
        int xx=w.front().first,yy=w.front().second;
        w.pop();
        if (xx==x2 && yy==z2) break;
        i=xx;
        while (i<n && a[i+1][yy]==0) i=i+1;
        if (u[i][yy]==-1) {
            w.push(make_pair(i,yy));
            p[i][yy]=make_pair(xx,yy);
            u[i][yy]=u[xx][yy]+1;
        }
        i=xx;
        for (;i>1 && a[i-1][yy]==0;i--);
        if (u[i][yy]==-1) {
            w.push(make_pair(i,yy));
            p[i][yy]=make_pair(xx,yy);
            u[i][yy]=u[xx][yy]+1;
        }
        i=yy;
        for (;i>1 && a[xx][i-1]==0; i--);
        if (u[xx][i]==-1) {
            w.push(make_pair(xx,i));
            p[xx][i]=make_pair(xx,yy);
            u[xx][i]=u[xx][yy]+1;
        }
        i=yy;
        for (;i<m && a[xx][i+1]==0; i++);
        if (u[xx][i]==-1) {
            w.push(make_pair(xx,i));
            p[xx][i]=make_pair(xx,yy);
            u[xx][i]=u[xx][yy]+1;
        }
    }
    if (u[x2][z2]==-1) {
        cout << "No Solution";
        return 0;
    } else {
        ans=ans2=0;
        for (int i=x2, j=z2; !(i==x1 && j==z1);) {
            ans+=abs(p[i][j].first-i)+abs(p[i][j].second-j);
            ans2++;
            ans3.push_back(make_pair(i,j));
            pair <int,int> e;
            e=p[i][j];
            i=e.first;
            j=e.second;
        }
        ans3.push_back(make_pair(x1,z1));
    }
    cout << ans << " " << ans2 << "\n";
    for (int i=ans3.size()-1; i>=0; i--) cout << ans3[i].first << " " << ans3[i].second << "\n";
	return 0;
}
