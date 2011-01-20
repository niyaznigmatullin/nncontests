#include <iostream>
#include <stdio.h>
#include <math.h>
#include <vector>
using namespace std;
struct TPoint{
    float x,y;
};
float eps=1e-9;
TPoint p[5010];
float d[5010],dis,w[5010];
int c[5010],n,k,r,kol,e,v;
vector <vector <int> > ans;
bool u[5010];

bool lesse(float x, float y) { return x-y<eps; }
float min(float x, float y) {
    if (lesse(x,y)) return x; else return y;
}
int main() {
    freopen("stars.in","r",stdin);
    freopen("stars.out","w",stdout);
    cin >> n >> k;
    memset(u,false,sizeof(u));
    for (int i=0; i<n; i++) {
        cin >> p[i].x >> p[i].y;
    }
    for (int i=0; i<n; i++) {
        w[i]=1e20;
        for (int j=0; j<n; j++) if (i!=j) {
            w[i]=min(sqrt((p[i].x-p[j].x)*(p[i].x-p[j].x)+(p[i].y-p[j].y)*(p[i].y-p[j].y)),w[i]);
        }
    }
	kol=-1;
	while (r!=n) {
		kol++;
		ans.push_back(vector <int> (0));
		e=-1;
		for (int i=0; i<n; i++) if (!u[i]) { e=i; break; }
		r++;
		u[e]=true;
		ans[kol].push_back(e);
		for (int i=0; i<(int)ans[kol].size(); i++) {
			v=ans[kol][i];
			dis=1e20;
			for (int j=0; j<n; j++) {
				d[j]=sqrt((p[j].x-p[v].x)*(p[j].x-p[v].x)+(p[j].y-p[v].y)*(p[j].y-p[v].y));
			}
			for (int j=0; j<n;j++) if (!u[j]) if (lesse(d[j],w[j]*k) || lesse(d[j],w[v]*k)) {
				u[j]=true;
				ans[kol].push_back(j);
				r++;
			}
			if (r==n) break;
		}
	}
	cout << ans.size() << "\n";
	for (int i=0; i<(int)ans.size(); i++) {
		cout << ans[i].size();
		for (int j=0; j<(int)ans[i].size(); j++) cout << " " << ans[i][j]+1;
		cout << "\n";
	}
	return 0;
}
