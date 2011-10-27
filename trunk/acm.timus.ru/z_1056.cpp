#include <iostream>
#include <string>
#include <math.h>
#include <queue>
#include <vector>
using namespace std;
string s;

int head[20000], next[30000], cnodes = 0, v[30000], n, dep[20000], mind = 1 << 30;
bool u[20000];
vector <int> ans;
void add_edge(int x, int y) {
	next[++cnodes] = head[x];
	head[x] = cnodes;
	v[cnodes] = y;
}

int bfs(int x) {
	queue <pair<int, int> > w;
	memset(u, 0, sizeof(u));
	w.push(make_pair(x,0));
	int ret = 0;
	while (!w.empty()) {
		int xx = w.front().first, yy = w.front().second;
		w.pop();
		ret = max(ret, yy);
		int e = head[xx];
		while (e) {
			if (!u[v[e]]) {
				w.push(make_pair(v[e], yy+1));
				u[v[e]] = true;
			}
			e = next[e];
		}
	}
	return ret;
}
int main() {
	scanf("%d", &n);
	for (int i=2; i<=n; i++) {
		int x;
		scanf("%d", &x);
		add_edge(i,x);
		add_edge(x,i);
	}
	for (int i=1; i<=n; i++) {
		dep[i] = bfs(i);
		mind = min(mind, dep[i]);
	}
	for (int i=1; i<=n; i++) {
		if (dep[i] == mind) ans.push_back(i);
	}
	/*for (int i=0; i<(int)ans.size(); i++) {
		printf("%d ", ans[i]);
	}*/
	printf("1 2");
    return 0;
}
