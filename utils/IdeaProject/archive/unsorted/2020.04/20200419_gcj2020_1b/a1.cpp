/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

string getans(pair<int, int> x, pair<int, int> y, bool flipx, bool flipy) {
	string res;
	for (int bit = 0; bit <= 30; bit++) {
		if ((x.first >> bit) & 1) {
			if (((x.second >> bit) & 1) == flipx) {
				res += "W";
			} else {
				res += "E";
			}
		} else if ((y.first >> bit) & 1) {
			if (((y.second >> bit) & 1) == flipy) {
				res += "S";
			} else {
				res += "N";
			}
		}
	}
	return res;
}

unordered_map<int, int> getall(int x) {
	vector<pair<int, int>> blocks;
	int bit = 0;
	while (x > 0) {
		while ((x & 1) == 0) {
			bit++;
			x >>= 1;
		}
		int from = bit;
		while ((x & 1) == 1) {
			bit++;
			x >>= 1;
		}
		int to = bit;
		blocks.push_back({from, to});
	}
	int bs = (int) blocks.size();
	unordered_map<int, int> ret;
	for (int mask = 0; mask < 1 << bs; mask++) {
		bool bad = false;
		int m = 0;
		int plus = 0;
		for (int i = 0; i < bs; i++) {
			auto &cb = blocks[i];
			if (cb.second - cb.first == 1 && ((mask >> i) & 1) == 1) {
				bad = true;
				break;
			}
			if (((mask >> i) & 1) == 1) {
				m |= 1 << cb.first;
				m |= 1 << cb.second;
				plus |= 1 << cb.second;
			} else {
				for (int b = cb.first; b < cb.second; b++) {
					m |= 1 << b;
					plus |= 1 << b;
				}
			}
		}
		if (bad) continue;
		ret[m] = plus;
	}
	return ret;
}

void check(bool flipx, bool flipy, int x, int y, string res) {
	if (flipx) x = -x;
	if (flipy) y = -y;
	int cx = 0;
	int cy = 0;
	int cur = 0;
	for (char c : res) {
		if (c == 'N') cy += 1 << cur;
		if (c == 'S') cy -= 1 << cur;
		if (c == 'W') cx -= 1 << cur;
		if (c == 'E') cx += 1 << cur;
		cur++;
	}
	assert(x == cx && y == cy);
}

void solve(int test) {
	int x, y;
	cin >> x >> y;
	bool flipx = false;
	bool flipy = false;
	if (y < 0) {
		y = -y;
		flipy = true;
	}
	if (x < 0) {
		x = -x;
		flipx = true;
	}
	auto mx = getall(x);
	auto my = getall(y);
	int bits = 0;
	while ((1 << bits) <= max(x, y)) {
		bits++;
	}
	cout << "Case #" << test << ": ";
	for (int b = bits; b <= bits + 1; b++) {
		for (auto cx : mx) {
			int left = ((1 << b) - 1) ^ cx.first;
			if (my.find(left) != my.end()) {
				string res = getans(cx, *my.find(left), flipx, flipy);
				cout << res << '\n';
				check(flipx, flipy, x, y, res);
				return;
			}
		}
	}
	cout << "IMPOSSIBLE\n";
}

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int t;
	cin >> t;
	for (int i = 1; i <= t; i++) {
		solve(i);
	}	
}