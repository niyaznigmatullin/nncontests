#include <iostream>
#include <stdio.h>
#include <vector>

using namespace std;

int n;
bool u[100][100];
pair <int, int> p[100][100];

int dx[] = { 1, 2, 1, 2,-1,-2,-1,-2};
int dy[] = { 2, 1,-2,-1, 2, 1,-2,-1};

bool rec(int x, int y, int k) {
	if (k == n * n - 1) {
		return true;
	}
	u[x][y] = false;
	int togo = -1, how = 10, kol[20];
	memset(kol, -1, sizeof(kol));
	for (int i=7; i>=0; i--) if (u[x+dx[i]][y+dy[i]]) {
		/*if (rec(x+dx[i],y+dy[i], k+1)) {
			p[x][y].first = x + dx[i];
			p[x][y].second = y + dy[i];
			return true;
		}*/
		kol[i] = 0;
		int xx = x + dx[i], yy = x + dy[i];
		for (int j=0; j<8; j++) {
			if (u[xx+dx[j]][yy+dy[j]]) kol[i]++;
		}
		if (kol[i]<how) {
			togo = i;
			how = kol[i];
		}
	}
	vector <int> yy;
	for (int i=0; i<8; i++) {
		if (kol[i] == how) yy.push_back(i);
	}
	for (int i=0; i<yy.size(); i++) {
		togo = yy[i];
		if (rec(x+dx[togo],y+dy[togo], k+1)) {
			p[x][y].first = x + dx[togo];
			p[x][y].second = y + dy[togo];
			u[x][y] = true;
			return true;
		}
	}
	u[x][y] = true;
	return false;
}

int main() {
	cin >> n;
	for (int i=0; i<n; i++) 
		for (int j=0; j<n; j++) u[i+10][j+10] = true;
	if (!rec(10,10,0)) {
		cout << "IMPOSSIBLE";
		return 0;
	}
	for (int i = 10, j = 10; i!=0 && j!=0;) {
		cout << (char)(i-10+'a') << j-9 << "\n";
		int xx = i, yy = j;
		i = p[xx][yy].first;
		j = p[xx][yy].second;
	}
    return 0;
}
