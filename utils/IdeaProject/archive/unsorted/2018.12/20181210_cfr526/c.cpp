#include <bits/stdc++.h>

using namespace std;

int newNode() {
	tv[fr] = 0;
	return fr++;
}

int build(int l, int r) {
	if (l + 1 == r) {
		return newNode();
	}
	int mid = (l + r) >> 1;
	int v = newNode();
	tl[v] = build(0, mid);
	tr[v] = build(mid, r);
	tv[v] = tv[tl[v]] + tv[tr[v]];
	return v;
}

int n;

void dfs(int v, int tr) {
	tr = add(tr, 0, n, p[v], 1);
	st[v] = tr;
	pp[0][v] = pv[v];
	for (int i = 1; i < K; i++) {
		pp[i][v] = pp[i - 1][pp[i - 1][v]];
	}
	for (int to : edges[v]) {
		dfs(to, tr);
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> p[i];
	}
	for (int i = 1; i < n; i++) {
		cin >> pv[i];
		pv[i]--;
		edges[pv[i]].push_back(i);
	}
	int tr = build(0, n);
	dfs(0, tr);
}
