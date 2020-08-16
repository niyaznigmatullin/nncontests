/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;

int const INF = 1 << 30;

		int minCost(vector<vector<int>> const &a) {
			int n = (int) a.size();
			vector<int> row(n), col(n), pair(n), min(n), from(n);
			vector<int> was(n);
			int allMin = INF;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					allMin = std::min(allMin, a[i][j]);
				}
			}
			for (int i = 0; i < n; i++) {
				row[i] = allMin;
			}
			fill(pair.begin(), pair.end(), -1);
			for (int i = 0; i < n; i++) {
				fill(min.begin(), min.end(), INF);
				fill(was.begin(), was.end(), 0);
				fill(from.begin(), from.end(), -1);
				int cur = i;
				int curPair = -1;
				do {
					if (curPair != -1) {
						was[curPair] = true;
						cur = pair[curPair];
					}
					int d = INF;
					int minPair = -1;
					for (int j = 0; j < n; j++) {
						if (was[j]) {
							continue;
						}
						int val = a[cur][j] - row[cur] - col[j];
						if (val < min[j]) {
							min[j] = val;
							from[j] = curPair;
						}
						if (d > min[j]) {
							d = min[j];
							minPair = j;
						}
					}
					row[i] += d;
					for (int j = 0; j < n; j++) {
						if (was[j]) {
							col[j] -= d;
							row[pair[j]] += d;
						} else {
							min[j] -= d;
						}
					}
					curPair = minPair;
				} while (pair[curPair] >= 0);
				while (from[curPair] >= 0) {
					int prev = from[curPair];
					pair[curPair] = pair[prev];
					curPair = prev;
				}
				pair[curPair] = i;
			}
			int ret = 0;
			for (int i = 0; i < n; i++) {
				ret += a[pair[i]][i];
			}
			return ret;
		}


int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);	
	int n;
	cin >> n;
	vector<vector<int>> x(2 * n, vector<int>(3));
	for (int i = 0; i < 2 * n; i++) {
		for (int j = 0; j < 3; j++) cin >> x[i][j];
	}
	vector<vector<int>> a(n, vector<int>(n));
	for (int i = 0; i < n; i++) {
		for (int j = n; j < 2 * n; j++) {
			a[i][j - n] = 0;
			for (int k = 0; k < 3; k++) {
				a[i][j - n] += abs(x[i][k] - x[j][k]);
			}
		}
	}
	cout << minCost(a) << endl;
}
