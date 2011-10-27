#include <iostream>
#include <stdio.h>
#include <algorithm>

#include <vector>
#include <string>
#include <queue>
#define sz size()
using namespace std;

int dx[8]={1,1,2,2,-1,-1,-2,-2};
int	dy[8]={2,-2,1,-1,2,-2,1,-1};

queue<pair <int,int> > w;
pair <int,int> p[51][51];

int n,i1,i2,j1,j2;
char a[51][51];
bool u[51][51];

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	cin >> n;
	for (int i=0; i<n; i++)
		for (int j=0; j<n; j++) {
			cin >> a[i][j];
			if (a[i][j]=='@') {
				i1=i;
				j1=j;
			}
		}
	for (int i=0; i<n; i++)
		for (int j=0; j<n; j++) if (a[i][j]=='@' && (i!=i1 || j!=j1)) {
			i2=i;
			j2=j;
		}
	for (int i=0; i<n; i++)
		for (int j=0; j<n; j++) p[i][j]=make_pair(-1,-1);
	w.push(make_pair(i1,j1));
	u[i1][j1]=true;
	while (!w.empty()) {
		int xx=w.front().first,yy=w.front().second;
		w.pop();
		if (xx==i2 && yy==j2) {
			break;
		}
		for (int i=0; i<8; i++) if (xx+dx[i]>=0 && yy+dy[i]>=0 && xx+dx[i]<n && yy+dy[i]<n && !u[xx+dx[i]][yy+dy[i]] && a[xx+dx[i]][yy+dy[i]]!='#') {
			u[xx+dx[i]][yy+dy[i]]=true;
			p[xx+dx[i]][yy+dy[i]]=make_pair(xx,yy);
			w.push(make_pair(xx+dx[i],yy+dy[i]));
		}
	}
	if (!u[i2][j2]) {
		cout << "Impossible";
		return 0;
	}
	for (int i=i2, j=j2; i!=-1 && j!=-1;) {
		a[i][j]='@';
		int ii=i,jj=j;
		i=p[ii][jj].first;
		j=p[ii][jj].second;
	}
	for (int i=0; i<n; i++) {
		for (int j=0; j<n; j++) cout << a[i][j];
		cout << "\n";
	}
	return 0;
}
