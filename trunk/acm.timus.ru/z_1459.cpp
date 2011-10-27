#include <iostream>
#include <string>
#include <stdio.h>
#include <queue>
using namespace std;

int n, p[1000010], d[1000010];
bool u[1000010];
queue <int> w;
int main() {
    cin >> n;
	if (n == 1) {
		cout << 1;
		return 0;
	}
	if (n == 2) {
		cout << 2;
		return 0;
	}
	p[1] = -1;
	p[2] = -1;
	d[1] = 1;
	d[2] = 2;
	memset(u,0,sizeof(u));
	u[1] = true;
	u[2] = true;
	w.push(1);
	w.push(2);
	while (!w.empty()) {
		int xx = w.front();
		w.pop();
		int y =(xx*10+1)%n; 
		if (!u[y]) {
			w.push(y);
			u[y] = true;
			p[y] = xx;
			d[y] = 1;
		}
		y = (xx*10+2)%n; 
		if (!u[y]) {
			w.push(y);
			u[y] = true;
			p[y] = xx;
			d[y] = 2;
		}
	}
	if (!u[0]) {
		printf("Impossible");
		return 0;
	}
	string ans="";
	for (int i=0; i!=-1;) {
		ans = (char)(d[i]+'0') + ans;
		i = p[i];
	}
	if (ans.size()>30) printf("Impossible"); else cout << ans;
    return 0;
}
