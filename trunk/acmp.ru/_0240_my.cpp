#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <math.h>
#include <vector>
#include <string>
#define sz size()
using namespace std;

string ss;
char c[20][20][20], b[20][20][20];
int n;


vector <int> ff(int x, int y, int z) {
	vector <int> ret(4,0);
	ret[3] = z;
	if (z==0) {
		ret[0]=y;
		ret[1]=0;
		ret[2]=n - x - 1;
	} else if (z==1) {
		ret[0]=0;
		ret[1]=n - y - 1;
		ret[2]=n - x - 1;
	} else if (z==2) {
		ret[0]=n - y - 1;
		ret[1]=n - 1;
		ret[2]=n - x - 1;
	} else if (z==3) {
		ret[0] = n - 1;
		ret[1] = y;
		ret[2] = n - x - 1;
	} else if (z==4) {
		ret[0] = y;
		ret[1] = n - x - 1;
		ret[2] = n - 1;
	} else {
		ret[0] = y;
		ret[1] = x;
		ret[2] = 0;
	}
	return ret;
}

bool process(char c, vector <int> a) {
	int i = a[0], j = a[1], k = a[2];
	bool ok = true;
	if (a[3]==0) {
		while (j<n && (b[i][j][k] != c && b[i][j][k] != 'e' || c == '.')) {			
			ok &= b[i][j][k] == 'n';
			b[i][j][k] = 'n';
			j++;
		}
		if (j<n && b[i][j][k] != c) {
			b[i][j][k] = c;
			return true;
		}
	} else if (a[3]==1) {
		while (i<n && (b[i][j][k] != c && b[i][j][k] != 'e'|| c == '.')) {
			ok &= b[i][j][k] == 'n';
			b[i][j][k] = 'n';
			i++;
		}
		if (i<n && b[i][j][k] != c) {
			b[i][j][k] = c;
			return true;
		}
	} else if (a[3]==2) {
		while (j>=0 && (b[i][j][k] != c && b[i][j][k] != 'e'|| c == '.')) {
			ok &= b[i][j][k] == 'n';
			b[i][j][k] = 'n';
			j--;
		}
		if (j>=0 && b[i][j][k] != c) {
			b[i][j][k] = c;
			return true;
		}
	} else if (a[3]==3) {
		while (i>=0 && (b[i][j][k] != c && b[i][j][k] != 'e'|| c == '.')) {
			ok &= b[i][j][k] == 'n';
			b[i][j][k] = 'n';
			i--;
		}
		if (i>=0 && b[i][j][k] != c) {
			b[i][j][k] = c;
			return true;
		}
	} else if (a[3] == 4) {
		while (k>=0 && (b[i][j][k] != c && b[i][j][k] != 'e'|| c == '.')) {
			ok &= b[i][j][k] == 'n';
			b[i][j][k] = 'n';
			k--;
		}
		if (k>=0 && b[i][j][k] != c) {
			b[i][j][k] = c;
			return true;
		}
	} else {
		while (k<n && (b[i][j][k] != c && b[i][j][k] != 'e'|| c == '.')) {
			ok &= b[i][j][k] == 'n';
			b[i][j][k] = 'n';
			k++;
		}
		if (k<n && b[i][j][k] != c) {
			b[i][j][k] = c;
			return true;
		}
	}
	return !ok;
}

int main() {
	freopen("input.txt","r",stdin);
	freopen("output.txt","w",stdout);
	scanf("%d", &n);
	for (int i=0; i<n; i++)
		for (int j=0; j<6; j++) {
			cin >> ss;
			for (int t=0; t<n; t++) {
				c[j][i][t] = ss[t];
			}
		}
	for (int i=0; i<n; i++)
		for (int j=0; j<n; j++) 
			for (int k=0; k<n; k++) b[i][j][k] = 'e';
	while (1) {
		bool ok = false;
		for (int t=0; t<6; t++) {
			for (int i=0; i<n; i++) {
				for (int j=0; j<n; j++) {
					ok |= process(c[t][i][j], ff(i,j,t));
				}
			}
		}
		if (!ok) break;
	}
	int ans = 0;
	for (int i=0; i<n; i++)
		for (int j=0; j<n; j++)
			for (int k=0; k<n; k++) {
				if (b[i][j][k] != 'n') ans++;
			}
	printf("%d", ans);
	return 0;
}
