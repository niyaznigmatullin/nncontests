/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	vector<int> lost(29);
	vector<int> bad(29);
	vector<int> all(29);
	for (int mask = 0; mask < 1 << 28; mask++) {
		if ((mask & (mask - 1)) == 0) cerr << mask << endl;
		int z = __builtin_popcount(mask);
		all[28 - z]++;
		int k = mask;
		while (true) {
			bool changed = false;
			for (int i = 0; i < 28; i += 7) {
				int g = (1 << (i + 7)) - (1 << i);
				int cn = __builtin_popcount(k & g);
				if (cn == 5 || cn == 6) {
					k |= g;
					changed = true;
				}
			}
			for (int i = 0; i < 7; i++) {
				int g = (1 << i) | (1 << (i + 7)) | (1 << (i + 14)) | (1 << (i + 21));
				int cn = __builtin_popcount(k & g);
				if (cn == 3) {
					k |= g;
					changed = true;
				}
			}
			if (!changed) break;
		}
		int pics = __builtin_popcount(k & (31 | (31 << 7) | (31 << 14)));
		lost[28 - z] += 15 - pics;
		if (pics != 15) {
			bad[28 - z]++;
		}
	}
	for (int i = 0; i <= 28; i++) {
		printf("{%.17f, %.17f},\n", 1. * bad[i] / all[i], 1. * lost[i] / all[i]);
	}
}