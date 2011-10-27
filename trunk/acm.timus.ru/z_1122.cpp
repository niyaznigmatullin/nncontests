#include <iostream>
#include <string>
#include <stdio.h>
#include <vector>
#include <queue>

using namespace std;

vector <vector <int> > a(4, vector <int> (4,0));
char c[3][3];
bool u[1 << 17];
queue <int> w,e;
vector <int> edge[1 << 16];
int to_numb(vector <vector <int> > a) {
	int ret = 0;
	for (int i=0; i<4; i++)
		for (int j=0; j<4; j++) {
			ret = (ret * 2) + a[i][j];
		}
	return ret;
}

vector <vector <int> > to_matrix(int x) {
	vector <vector <int> > a(4,vector <int> (4,0));
	for (int i=3; i>=0; i--)
		for (int j=3; j>=0; j--) {
			a[i][j] = x & 1;
			x >>= 1;
		}
	return a;
}

int main() {
    for (int i=0; i<4; i++)
		for (int j=0; j<4; j++) {
			char ch;
			cin >> ch;
			if (ch == 'W') {
				a[i][j] = 0;
			} else a[i][j] = 1;
		}
	for (int i=0; i<3; i++)
		for (int j=0; j<3; j++) {
			char ch;
			cin >> ch;
			c[i][j] = ch;
		}
	memset(u,0,sizeof(u));
	int r = to_numb(a);
	u[r] = true;
	for (int i0=0; i0<(1 << 16); i0++) {
		a = to_matrix(i0);
		for (int i=0; i<4; i++)
			for (int j=0; j<4; j++) {
				vector <vector <int> > b(a);
				for (int ii=0; ii<3; ii++)
					for (int jj=0; jj<3; jj++) {
						int i1 = i+ii-1, j1 = j+jj-1;
						if (c[ii][jj] == '1' && i1>=0 && j1>=0 && i1<4 && j1<4) {							
							if (b[i1][j1] == 1) b[i1][j1] = 0; else b[i1][j1] = 1;
						}
					}
				int y = to_numb(b);
				edge[i0].push_back(y);
			}
	}
	w.push(r);
	e.push(0);
	while (!w.empty()) {
		int xx = w.front(), yy = e.front();
		w.pop();
		e.pop();
		if (xx == 0 || xx == (1 << 16) - 1) {
			cout << yy;
			return 0;
		}
		for (int i=0; i<(int)edge[xx].size(); i++) {
			if (!u[edge[xx][i]]) {
				w.push(edge[xx][i]);
				e.push(yy);
				u[edge[xx][i]] = true;
			}
		}
	}
	cout << "Impossible";
    return 0;
}
