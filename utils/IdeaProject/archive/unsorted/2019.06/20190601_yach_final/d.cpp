/**
 * Niyaz Nigmatullin
 */

#include <bits/stdc++.h>

using namespace std;


string to_string(string s) {
  return '"' + s + '"';
}

string to_string(const char* s) {
  return to_string((string) s);
}

string to_string(bool b) {
  return (b ? "true" : "false");
}

template <typename A, typename B>
string to_string(pair<A, B> p) {
  return "(" + to_string(p.first) + ", " + to_string(p.second) + ")";
}

template <typename A>
string to_string(A v) {
  bool first = true;
  string res = "{";
  for (const auto &x : v) {
    if (!first) {
      res += ", ";
    }
    first = false;
    res += to_string(x);
  }
  res += "}";
  return res;
}

void debug_out() { cerr << endl; }

template <typename Head, typename... Tail>
void debug_out(Head H, Tail... T) {
  cerr << " " << to_string(H);
  debug_out(T...);
}

#ifndef LOCAL
#define debug(...) cerr << "[" << #__VA_ARGS__ << "]:", debug_out(__VA_ARGS__)
#else
#define debug(...) 42
#endif

int main() {
	ios_base::sync_with_stdio(false), cin.tie(0);
	int n, e1, e2;
	cin >> n >> e1 >> e2;
	--e1;
	--e2;
	vector<vector<unsigned int>> a(7, vector<unsigned int> (n));
	for (int i = 0; i < 7; i++) {
		if (i == e1 || i == e2) continue;
		for (int j = 0; j < n; j++) {
			cin >> a[i][j];
		}
	}
	int fr = 0;
	vector<vector<int>> id(7, vector<int>(4, -1));
	for (int i = 0; i < 7; i++) {
		for (int j = 0; j < 4; j++) {
			if (i == e1 || i == e2) {
				id[i][j] = fr++;
			} else {
				id[i][j] = -1;
			}
		}
	}
	// {
	// vector<vector<string>> ss(7, vector<string>(4));
	// for (int j = 0; j < 4; j++) {
	// 	int row = 4 - j;
	// 	int col = j;
	// 	ss[row][col] = "S";
	// }
	// for (int i = 0; i < 4; i++) {
	// 	for (int j = 0; j < 4; j++) {
	// 		int row = (4 - j + i + 1) % 5;
	// 		int col = j;
	// 		ss[row][col] = to_string(i);
	// 	}
	// }
	// for (int i = 0; i < 7; i++) {
	// 	debug(i, ss[i]);
	// }
	// }
	vector<vector<unsigned int>> ans(2);
	for (int start = 0; start < n; start += 4) {
		vector<vector<unsigned int>> g(8, vector<unsigned int>(9));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (id[j][i] == -1) {
					g[i][8] ^= a[j][start + i];
				} else {
					g[i][id[j][i]] = 1;
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int row = 4 - j;
				int col = j;
				if (id[row][col] == -1) {
					g[i + 4][8] ^= a[row][col + start];
				} else {
					g[i + 4][id[row][col]] = 1;
				}
			}
			for (int j = 0; j < 4; j++) {
				int row = (4 - j + i + 1) % 5;
				int col = j;
				if (id[row][col] == -1) {
					g[i + 4][8] ^= a[row][col + start];
				} else {
					g[i + 4][id[row][col]] = 1;
				}
			}
			if (id[6][i] == -1) {
				g[i + 4][8] ^= a[6][i + start];
			} else {
				g[i + 4][id[6][i]] = 1;
			}
		}
		// for (int i = 0; i < 8; i++) {
		// 	debug(i, g[i]);
		// }
		for (int i = 0; i < 8; i++) {
			for (int j = i; j < 8; j++) {
				if (g[j][i] != 0) {
					if (i != j) {
						g[i].swap(g[j]);
					}
					break;
				}
			}
			assert(g[i][i] != 0);
			for (int j = i + 1; j < 8; j++) {
				if (g[j][i] == 0) continue;
				for (int k = 0; k <= 8; k++) {
					g[j][k] ^= g[i][k];
				}
			}
			// for (int e = 0; e < 8; e++) {
			// 	debug(e, g[e]);
			// }
		}
		for (int i = 7; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (g[j][i] == 0) continue;
				for (int k = 0; k <= 8; k++) {
					g[j][k] ^= g[i][k];
				}
			}
		}
		for (int i = 0; i < 8; i++) {
			ans[i / 4].push_back(g[i][8]);
		}
	}
	for (auto &r : ans) {
		for (int i = 0; i < (int) r.size(); i++) {
			if (i > 0) cout << ' ';
			cout << r[i];
		}
		cout << '\n';
	}
}
