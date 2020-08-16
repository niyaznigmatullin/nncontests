/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

vector<long long> a;

void add(int x, long long y) {
    for (int i = x; i < (int) a.size(); i |= i + 1) {
        a[i] += y;
    }
}

long long getSum(int x) {
    if (x >= (int) a.size()) x = (int) a.size() - 1;
    long long ret = 0;
    for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
        ret += a[i];
    }
    return ret;
}

int getKth(long long k) {
    int l = 0;
    int r = (int) a.size();
    while (l < r - 1) {
        int mid = (l + r) >> 1;
        if (a[mid - 1] >= k) {
            r = mid;
        } else {
            k -= a[mid - 1];
            l = mid;
        }
    }
    return l;
}


int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n;
	cin >> n;
	int sz = 1;
	while (sz < n) sz *= 2;
	a.resize(sz);
	int q;
	cin >> q;
	for (int i = 0; i < q; i++) {
		string op;
		cin >> op;
		if (op == "+") {
			int id, val;
			cin >> id >> val;
			add(id, val);
		} else {
			long long w;
			cin >> w;
			w++;
			if (getSum(sz - 1) < w) {
				cout << n - 1 << '\n';
			} else {
				cout << getKth(w) - 1 << "\n";
			}
		}
	}
}